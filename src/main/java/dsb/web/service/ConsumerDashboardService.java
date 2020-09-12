package dsb.web.service;

import dsb.web.domain.Account;
import dsb.web.domain.ConsumerAccount;
import dsb.web.domain.Customer;
import dsb.web.repository.ConsumerAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsumerDashboardService {

    private ConsumerAccountRepository consumerAccountRepository;


    @Autowired
    public ConsumerDashboardService(ConsumerAccountRepository consumerAccountRepository) {
        super();
        this.consumerAccountRepository = consumerAccountRepository;
    }


    public List<ConsumerAccount> findHighestAccounts() {
        List<ConsumerAccount> top10lijst = consumerAccountRepository.findConsumerAccountByHighestBalance();
        System.out.println(top10lijst);
        return top10lijst;
    }




    }
