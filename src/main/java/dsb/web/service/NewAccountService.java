package dsb.web.service;

import dsb.web.controller.beans.NewAccountBean;
import dsb.web.domain.*;
import dsb.web.repository.AccountRepository;
import dsb.web.repository.SMEAccountRepository;
import dsb.web.repository.CompanyRepository;
import dsb.web.repository.ConsumerAccountRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class NewAccountService {
    private AccountRepository accountRepository;
    private CompanyRepository companyRepository;
    private SMEAccountRepository accountRepositorySme;
    private ConsumerAccountRepository consumerAccountRepository;

    public NewAccountService(CompanyRepository companyRepository, SMEAccountRepository accountRepositorySme, ConsumerAccountRepository consumerAccountRepository, AccountRepository accountRepository) {
        this.companyRepository = companyRepository;
        this.accountRepositorySme  = accountRepositorySme;
        this.consumerAccountRepository = consumerAccountRepository;
        this.accountRepository = accountRepository;
    }

    public void saveNewAccount(NewAccountBean nab){
        Iban iban = IbanService.randIBAN();
        System.out.println(iban.toString());
        System.out.println(accountRepository.existsByAccountNo(iban.toString()));
        while (accountRepository.existsByAccountNo(iban.toString()))
            iban = IbanService.randIBAN();
        Company comp = null;
        Account acc;
        if (nab.getName() != null){
            Company company = new Company(nab.getName(),nab.getKVKno(), nab.getBTWno());
            comp = companyRepository.save(company);
            System.out.println("New company created");
            acc = accountRepositorySme.save(
                    new SMEAccount(
                            IbanService.randIBAN().toString(),
                            nab.getBalance(),
                            Arrays.asList(nab.getHolder()),
                            company));
        } else {
            acc = consumerAccountRepository.save(
                    new ConsumerAccount(
                            IbanService.randIBAN().toString(),
                            nab.getBalance(),
                            Arrays.asList(nab.getHolder())));
        }
        System.out.println("New account created");
    }

    public void checkAccountNumber(){
        //Check if number is 8 digits
        //Check if number appears in DB
    }
}
