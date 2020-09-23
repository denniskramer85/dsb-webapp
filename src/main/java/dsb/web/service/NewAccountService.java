package dsb.web.service;

import dsb.web.controller.beans.CompanyBean;
import dsb.web.domain.*;
import dsb.web.repository.AccountRepository;
import dsb.web.repository.SMEAccountRepository;
import dsb.web.repository.CompanyRepository;
import dsb.web.repository.ConsumerAccountRepository;
import dsb.web.repository.*;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;
import dsb.web.domain.Employee;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

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

    public Account saveNewConsumerAccount(Customer accountHolder) {
        Iban iban = ibanService.getUniqueIban();
        return consumerAccountRepository.save(new ConsumerAccount(iban.toString(), BALANCE, new ArrayList<>(Arrays.asList(accountHolder))));
    }

    public Account saveNewSMEAccount(Customer accountHolder, Company company) {
        Iban iban = ibanService.getUniqueIban();
        return accountRepositorySme.save(new SMEAccount(
                iban.toString(),
                BALANCE,
                new ArrayList<>(Arrays.asList(accountHolder)),
                company));
    }

    public Account createAndSaveNewAccountFromBean(CompanyBean cb) {
        Account account;
        if (cb.getName() != null) {                                                  // if consumer account
            account = saveNewConsumerAccount(cb.getCurrentCustomer());
        } else if (companyRepository.existsByKVKno(cb.getKVKno())) {                // if company exists
            account = saveNewSMEAccount(cb.getCurrentCustomer(), companyRepository.findCompanyByKVKno(cb.getKVKno()));
        } else {                                                                    // if new company
            account = saveNewSMEAccount(cb.getCurrentCustomer(), createAndSaveCompanyFromBean(cb));
        }
        transactionService.doInitialTransaction(account);
        return account;
    }

    public Company createAndSaveCompanyFromBean(CompanyBean cb) {
        Employee accountManager = employeeRepository.findById(45).get();
        Company comp = new Company(cb.getName(), cb.getKVKno(), cb.getBTWno(), accountManager, cb.getSector());
        return companyRepository.save(comp);
    }

    Integer kvkStringToNum(String kvknrStr) {
        try {
            Integer kvkNr = Integer.valueOf(kvknrStr);
            return kvkNr;
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public ModelAndView companyExists(CompanyBean cb, ModelMap model) {
        Company company = companyRepository.findCompanyByKVKno(cb.getKVKno());
        if (company != null) {                                                                                          // if kvkNo occurs in DB:
            if (!isCustomerAuthorizedForCompany(company, cb, model) && errorInCompanyFields(company, cb, model)){
                return new ModelAndView("redirect:/company-details", model);
            }
        } else {
            model.addAttribute("companyBean", cb);  // if kvkNo doesnt occur in DB
            model.addAttribute("company", null);
        }
        return new ModelAndView("redirect:/confirm-new-account", model);
    }

    private boolean errorInCompanyFields(Company c, CompanyBean cb, ModelMap model) {
        if (c.getName().equals(cb.getName()) && c.getBTWno().equals(cb.getBTWno()) && c.getSector().equals(cb.getSector())) {
            System.out.println("checkCompanyFields - false");
            return false;
        } else {
            System.out.println("checkCompanyFields - true");
            model.addAttribute("fieldsError", "Het opgegeven KVK nummer is bij ons reeds bekend, maar niet in combinatie met de overige gegevens. Probeer het nogmaals.");
            return true;
        }
    }

    public boolean isCustomerAuthorizedForCompany(Company company, CompanyBean cb, ModelMap model) {
        List<SMEAccount> accounts = accountRepositorySme.findAllByHolders(cb.getCurrentCustomer());
        System.out.println(accounts);
        if (accounts.size() > 0) {
            for (SMEAccount account : accounts) {
                if (account.getCompany().getKVKno().equals(cb.getKVKno())) {
                    model.addAttribute("company", company);
                    System.out.println("isCustomerAuthorizedForCompany - true");
                    return true;
                }
            }
        }
        model.addAttribute("companyError", "Je bent helaas niet bevoegd om een rekening te openen voor dit KVK nummer");
        System.out.println("isCustomerAuthorizedForCompany - false");
        return false;
    }
}
