package dsb.web.service;

import dsb.web.controller.beans.NewAccountBean;
import dsb.web.domain.Company;
import dsb.web.domain.ConsumerAccount;
import dsb.web.domain.SMEAccount;
import dsb.web.repository.CompanyRepository;
import dsb.web.repository.ConsumerAccountRepository;
import dsb.web.repository.SMEAccountRepository;
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

    @Autowired


    private static final int IBAN_LENGTH = 18;
    private static final String IBAN_START = "NL88DSBB";
    public final List<String> ACCOUNT_TYPES = Arrays.asList("particulier","zakelijk");


    public String buildIBAN(){
        /*Generate valid IBAN in string format*/
        String result = IBAN_START;
        result += "000";
        Random rand = new Random();
        while (result.length() < IBAN_LENGTH){
            result += rand.nextInt();
        }
        return result;
    }


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
