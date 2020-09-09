package dsb.web.service;

import dsb.web.controller.beans.NewAccountBean;
import dsb.web.domain.Company;
import dsb.web.domain.ConsumerAccount;
import dsb.web.domain.SMEAccount;
import dsb.web.repository.CompanyRepository;
import dsb.web.repository.ConsumerAccountRepository;
import dsb.web.repository.SMEAccountRepository;
import org.iban4j.CountryCode;
import org.iban4j.Iban;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class NewAccountService {
    private CompanyRepository companyRepository;
    private SMEAccountRepository smeAccountRepository;
    private ConsumerAccountRepository consumerAccountRepository;

    public NewAccountService(CompanyRepository companyRepository, SMEAccountRepository smeAccountRepository, ConsumerAccountRepository consumerAccountRepository) {
        this.companyRepository = companyRepository;
        this.smeAccountRepository = smeAccountRepository;
        this.consumerAccountRepository = consumerAccountRepository;
    }

    public final List<String> ACCOUNT_TYPES = Arrays.asList("particulier","zakelijk");





    public void saveNewAccount(NewAccountBean nab){
        if (nab.getName() != null){
            Company company = new Company(nab.getName(),nab.getKVKno(), nab.getBTWno());
            companyRepository.save(company);
            smeAccountRepository.save(new SMEAccount(nab.getAccountNo(), nab.getBalance(), Arrays.asList(nab.getHolder()),company));
        } else {
            consumerAccountRepository.save(new ConsumerAccount(nab.getAccountNo(), nab.getBalance(), Arrays.asList(nab.getHolder())));
        }
    }
}
