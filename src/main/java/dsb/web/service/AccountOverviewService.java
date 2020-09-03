package dsb.web.service;

import dsb.web.domain.ConsumerAccount;
import dsb.web.domain.Customer;
import dsb.web.domain.SMEAccount;
import dsb.web.repository.ConsumerAccountRepository;
import dsb.web.repository.SMEAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class AccountOverviewService {
    private ConsumerAccountRepository consumerAccountRepository;
    private SMEAccountRepository smeAccountRepository;

    @Autowired
    public AccountOverviewService(ConsumerAccountRepository consumerAccountRepository, SMEAccountRepository smeAccountRepository) {
        this.consumerAccountRepository = consumerAccountRepository;
        this.smeAccountRepository = smeAccountRepository;
    }

    public AccountOverviewService() {
    }

    public List<ConsumerAccount> getConsumerAccountsForCustomer(Customer customer) {
        List<ConsumerAccount> consumerAccountList = consumerAccountRepository.findAllByHolders(customer);
        return consumerAccountList;
    }

    public List<SMEAccount> getSMEAccountsForCustomer(Customer customer) {
        List<SMEAccount> smeAccountList = smeAccountRepository.findAllByHolders(customer);
        return smeAccountList;
    }
}
