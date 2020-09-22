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

    public NewAccountService(AccountRepository accountRepository, CompanyRepository companyRepository, SMEAccountRepository accountRepositorySme, ConsumerAccountRepository consumerAccountRepository, EmployeeRepository employeeRepository, TransactionService transactionService) {
        this.accountRepository = accountRepository;
        this.companyRepository = companyRepository;
        this.accountRepositorySme = accountRepositorySme;
        this.consumerAccountRepository = consumerAccountRepository;
        this.employeeRepository = employeeRepository;
        this.transactionService = transactionService;
    }

    public void saveNewAccount(CompanyBean companyBean){
        Iban iban = IbanService.randIBAN();
        while (accountRepository.existsByAccountNo(iban.toString()))
            iban = IbanService.randIBAN();
        Company company = null;
        Account account;
        if (companyBean.getName() != null){
            Employee accountManager = employeeRepository.findById(45).get();
            company = new Company(companyBean.getName(),companyBean.getKVKno(), companyBean.getBTWno(), accountManager, companyBean.getSector());
            company = companyRepository.save(company);
            System.out.println("New company created");
            account = accountRepositorySme.save(
            new SMEAccount(
                            IbanService.randIBAN().toString(),
                            BALANCE,
                            new ArrayList<> (Arrays.asList(companyBean.getCurrentCustomer())),
                            company));
        } else {
            account = consumerAccountRepository.save(
                    new ConsumerAccount(
                            IbanService.randIBAN().toString(),
                            BALANCE,
                            new ArrayList<> (Arrays.asList(companyBean.getCurrentCustomer()))));
        }
        transactionService.doInitialTransaction(account);
        System.out.println("New account created");
    }

    public boolean checkKVK(String kvknrStr){
        try {
            Integer.valueOf(kvknrStr);
            return true;
        } catch (NumberFormatException e){
            return false;
        }
    }
}
