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
    private SMEAccountRepository smeAccountRepository;
    private ConsumerAccountRepository consumerAccountRepository;
    private UserRepository userRepository;
    private TransactionService transactionService;
    private IbanService ibanService;
    private SectorRepository sectorRepository;

    public NewAccountService(AccountRepository accountRepository, CompanyRepository companyRepository, SMEAccountRepository smeAccountRepository, ConsumerAccountRepository consumerAccountRepository, UserRepository userRepository, TransactionService transactionService, IbanService ibanService, SectorRepository sectorRepository) {
        this.accountRepository = accountRepository;
        this.companyRepository = companyRepository;
        this.smeAccountRepository = smeAccountRepository;
        this.consumerAccountRepository = consumerAccountRepository;
        this.userRepository = userRepository;
        this.transactionService = transactionService;
        this.ibanService = ibanService;
        this.sectorRepository = sectorRepository;
    }


    public Account saveNewConsumerAccount(Customer accountHolder) {
        Iban iban = ibanService.getUniqueIban();
        return consumerAccountRepository.save(new ConsumerAccount(iban.toString(), BALANCE, new ArrayList<>(Arrays.asList(accountHolder))));
    }

    public Account saveNewSMEAccount(Customer accountHolder, Company company) {
        Iban iban = ibanService.getUniqueIban();
        return smeAccountRepository.save(new SMEAccount(
                iban.toString(),
                BALANCE,
                new ArrayList<>(Arrays.asList(accountHolder)),
                company));
    }

    public Account createAndSaveNewAccountFromBean(CompanyBean cb) {
        Account account;
        if (cb.getName() == null) {                                                  // if consumer account
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
        Employee accountManager = (Employee) userRepository.findById(45).get();
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
            if (isCustomerAuthorizedForCompany(company, cb, model) && areCompanyFieldsValid(company, cb, model)){
                cb.setExisting(true);
                model.addAttribute("companyBean", cb);
                return new ModelAndView("redirect:/confirm-new-account", model);
            } else {
                model.addAttribute("sectors", sectorRepository.findAll());
                return new ModelAndView("company-details", model);
            }
        }
        cb.setExisting(false);
        model.addAttribute("companyBean", cb);
        return new ModelAndView("redirect:/confirm-new-account", model);
    }

    private boolean areCompanyFieldsValid(Company c, CompanyBean cb, ModelMap model) {
        if (c.getName().equals(cb.getName()) && c.getBTWno().equals(cb.getBTWno()) && c.getSector().equals(cb.getSector())) {
            System.out.println("checkCompanyFields - true");
            return true;
        } else {
            System.out.println("checkCompanyFields - false");
            model.addAttribute("fieldsError", "Het opgegeven KVK nummer is bij ons reeds bekend, maar niet in combinatie met de overige gegevens. Probeer het nogmaals.");
            return false;
        }
    }

    public boolean isCustomerAuthorizedForCompany(Company company, CompanyBean cb, ModelMap model) {
        System.out.println("Accounts for: " + cb.getCurrentCustomer());
        List<SMEAccount> smeAccountList = smeAccountRepository.findAllByHolders(cb.getCurrentCustomer());
        List<SMEAccount> accounts = smeAccountRepository.findAllByHolders(cb.getCurrentCustomer());

        for (SMEAccount acc : accounts)
            System.out.println(((Account) acc).getAccountNo() + acc.getHolders());
        if (accounts.size() > 0) {
            for (SMEAccount account : accounts) {
                if (account.getCompany().getKVKno().equals(cb.getKVKno())) {
                    model.addAttribute("company", company);
                    System.out.println("isCustomerAuthorizedForCompany - true");
                    return true;
                }
            }
        }
        model.addAttribute("companyError", "Je bent helaas niet bevoegd om een rekening te openen namens dit bedrijf");
        System.out.println("isCustomerAuthorizedForCompany - false");
        return false;
    }
}
