package dsb.web.service;

import dsb.web.domain.Account;
import dsb.web.domain.ConsumerAccount;
import dsb.web.domain.Customer;
import dsb.web.repository.ConsumerAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.List;

@Service
@SessionAttributes("loggedInCustomer")
public class AccountOverviewService {
    private ConsumerAccountRepository consumerAccountRepository;

    @Autowired
    public AccountOverviewService(ConsumerAccountRepository consumerAccountRepository) {
        this.consumerAccountRepository = consumerAccountRepository;
    }

    public AccountOverviewService() {
    }

    public List<ConsumerAccount> getConsumerAccountsForCustomer(@ModelAttribute("loggedInCustomer") Customer loggedInCustomer) {
        List<ConsumerAccount> consumerAccountList = consumerAccountRepository.findAllByHolders(loggedInCustomer);
        return consumerAccountList;
    }
}
