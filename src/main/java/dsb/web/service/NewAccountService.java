package dsb.web.service;

import dsb.web.controller.beans.CompanyBean;
import dsb.web.domain.*;
import dsb.web.repository.AccountRepository;
import dsb.web.repository.CompanyRepository;
import dsb.web.repository.ConsumerAccountRepository;
import dsb.web.repository.SMEAccountRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class NewAccountService {
    public static final int BAllANCE = 25;

    private AccountRepository accountRepository;
    private CompanyRepository companyRepository;
    private SMEAccountRepository smeAccountRepository;
    private ConsumerAccountRepository consumerAccountRepository;

    public NewAccountService(CompanyRepository companyRepository, SMEAccountRepository smeAccountRepository, ConsumerAccountRepository consumerAccountRepository, AccountRepository accountRepository) {
        this.companyRepository = companyRepository;
        this.smeAccountRepository = smeAccountRepository;
        this.consumerAccountRepository = consumerAccountRepository;
        this.accountRepository = accountRepository;
    }

    public void saveNewAccount(CompanyBean companyBean){
        Iban iban = IbanService.randIBAN();
        while (accountRepository.existsByAccountNo(iban.toString()))
            iban = IbanService.randIBAN();
        Company comp = null;
        Account acc;
        if (companyBean.getName() != null){
            Company company = new Company(companyBean.getName(),companyBean.getKVKno(), companyBean.getBTWno(), companyBean.getSector());
            comp = companyRepository.save(company);
            System.out.println("New company created");
            acc = smeAccountRepository.save(
                    new SMEAccount(
                            IbanService.randIBAN().toString(),
                            BAllANCE,
                            Arrays.asList(companyBean.getCurrentCustomer()),
                            company));
        } else {
            acc = consumerAccountRepository.save(
                    new ConsumerAccount(
                            IbanService.randIBAN().toString(),
                            BAllANCE,
                            Arrays.asList(companyBean.getCurrentCustomer())));
        }
        System.out.println("New account created");
    }

    public void checkAccountNumber(){
        //Check if number is 8 digits
        //Check if number appears in DB
    }
}
