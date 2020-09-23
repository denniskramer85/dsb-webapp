package dsb.web.service;

import dsb.web.controller.beans.CompanyBean;
import dsb.web.domain.*;
import dsb.web.repository.AccountRepository;
import dsb.web.repository.SMEAccountRepository;
import dsb.web.repository.CompanyRepository;
import dsb.web.repository.ConsumerAccountRepository;
import dsb.web.repository.*;
import org.springframework.stereotype.Service;
import dsb.web.domain.Employee;

import java.util.*;

@Service
public class NewAccountService {
    public static final int BALANCE = 0;

    private AccountRepository accountRepository;
    private CompanyRepository companyRepository;
    private SMEAccountRepository accountRepositorySme;
    private ConsumerAccountRepository consumerAccountRepository;
    private EmployeeRepository employeeRepository;
    private TransactionService transactionService;
    private IbanService ibanService;

    public NewAccountService(AccountRepository accountRepository, CompanyRepository companyRepository, SMEAccountRepository accountRepositorySme, ConsumerAccountRepository consumerAccountRepository, EmployeeRepository employeeRepository, TransactionService transactionService, IbanService ibanService) {
        this.accountRepository = accountRepository;
        this.companyRepository = companyRepository;
        this.accountRepositorySme = accountRepositorySme;
        this.consumerAccountRepository = consumerAccountRepository;
        this.employeeRepository = employeeRepository;
        this.transactionService = transactionService;
        this.ibanService = ibanService;
    }

    public Account saveNewConsumerAccount(Customer accountHolder){
        Iban iban = ibanService.getUniqueIban();
        return consumerAccountRepository.save(new ConsumerAccount(iban.toString(), BALANCE, new ArrayList<>(Arrays.asList(accountHolder))));
    }

    public Account saveNewSMEAccount(Customer accountHolder, Company company){
        Iban iban = ibanService.getUniqueIban();
        return accountRepositorySme.save(new SMEAccount(
                iban.toString(),
                BALANCE,
                new ArrayList<>(Arrays.asList(accountHolder)),
                company));
    }

    public Account saveNewAccountFromBean(CompanyBean cb){
        Account account;
        if (cb.getName() != null){                                                  // if consumer account
            account = saveNewConsumerAccount(cb.getCurrentCustomer());
        } else if (companyRepository.existsByKVKno(cb.getKVKno())) {                // if company exists
            account = saveNewSMEAccount(cb.getCurrentCustomer(), companyRepository.findCompanyByKVKno(cb.getKVKno()));
        } else {                                                                    // if new company
            account = saveNewSMEAccount(cb.getCurrentCustomer(),createAndSaveCompanyFromBean(cb));
        }
        transactionService.doInitialTransaction(account);
        return account;
    }

    public Company createAndSaveCompanyFromBean(CompanyBean cb){
        Employee accountManager = employeeRepository.findById(45).get();
        Company comp = new Company(cb.getName(),cb.getKVKno(), cb.getBTWno(), accountManager, cb.getSector());
        return companyRepository.save(comp);
    }

    Integer kvkStringToNum(String kvknrStr){
        try {
            Integer kvkNr = Integer.valueOf(kvknrStr);
            return kvkNr;
        } catch (NumberFormatException e){
            return null;
        }
    }


    public boolean isCustomerAuthorizedForCompany(String kvkNo, Customer loggedInCustomer) {
        List<Account> accounts = accountRepository.findAllByHolders(loggedInCustomer);
        if (accounts.size() > 0){
            for (Account account : accounts) {
                if (account instanceof SMEAccount && ((SMEAccount) account).getCompany().getKVKno() == kvkNo)
                    return true;
            }
        }
        return false;
    }
}
