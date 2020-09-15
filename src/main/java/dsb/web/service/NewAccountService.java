package dsb.web.service;

import dsb.web.controller.beans.CompanyBean;
import dsb.web.domain.*;
import dsb.web.repository.*;
import org.springframework.stereotype.Service;
import dsb.web.domain.Employee;

import java.util.*;

@Service
public class NewAccountService {
    public static final int BAllANCE = 25;

    private AccountRepository accountRepository;
    private CompanyRepository companyRepository;
    private SMEAccountRepository smeAccountRepository;
    private ConsumerAccountRepository consumerAccountRepository;
    private EmployeeRepository employeeRepository;

    public NewAccountService(
            CompanyRepository companyRepository,
            SMEAccountRepository smeAccountRepository,
            ConsumerAccountRepository consumerAccountRepository,
            AccountRepository accountRepository,
            EmployeeRepository employeeRepository) {
        this.companyRepository = companyRepository;
        this.smeAccountRepository = smeAccountRepository;
        this.consumerAccountRepository = consumerAccountRepository;
        this.accountRepository = accountRepository;
        this.employeeRepository = employeeRepository;
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
            account = smeAccountRepository.save(
                    new SMEAccount(
                            IbanService.randIBAN().toString(),
                            BAllANCE,
                            Arrays.asList(companyBean.getCurrentCustomer()),
                            company));
        } else {
            account = consumerAccountRepository.save(
                    new ConsumerAccount(
                            IbanService.randIBAN().toString(),
                            BAllANCE,
                            Arrays.asList(companyBean.getCurrentCustomer())));
        }
        System.out.println("New account created");
    }
}
