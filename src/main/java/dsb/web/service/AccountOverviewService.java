package dsb.web.service;

import dsb.web.domain.Account;
import dsb.web.domain.ConsumerAccount;
import dsb.web.domain.Customer;
import dsb.web.repository.ConsumerAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

@Service
public class AccountOverviewService {
    private ConsumerAccountRepository consumerAccountRepository;

    @Autowired
    public AccountOverviewService(ConsumerAccountRepository consumerAccountRepository) {
        this.consumerAccountRepository = consumerAccountRepository;
    }

    public AccountOverviewService() {
    }

    public List<ConsumerAccount> getConsumerAccountsForCustomer(Customer loggedInCustomer) {
        List<ConsumerAccount> consumerAccountList = consumerAccountRepository.findAllByHolders(loggedInCustomer);
        return consumerAccountList;
    }

    public List<ConsumerAccount> getTestConsumerAccounts() {
        Optional<ConsumerAccount> consumerAccount1 = consumerAccountRepository.findById(110);
        Optional<ConsumerAccount> consumerAccount2 = consumerAccountRepository.findById(111);
        Optional<ConsumerAccount> consumerAccount3 = consumerAccountRepository.findById(112);
        List<ConsumerAccount> testConsumerAccounts = new ArrayList<>();
        testConsumerAccounts.add(consumerAccount1.get());
        testConsumerAccounts.add(consumerAccount2.get());
        testConsumerAccounts.add(consumerAccount3.get());
        return testConsumerAccounts;
    }
}
