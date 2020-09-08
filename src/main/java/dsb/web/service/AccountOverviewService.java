package dsb.web.service;

import dsb.web.domain.Account;
import dsb.web.domain.ConsumerAccount;
import dsb.web.domain.Customer;
import dsb.web.domain.SMEAccount;
import dsb.web.repository.AccountRepository;
import dsb.web.repository.ConsumerAccountRepository;
import dsb.web.repository.SMEAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class AccountOverviewService {
    private ConsumerAccountRepository consumerAccountRepository;
    private SMEAccountRepository smeAccountRepository;
    private AccountRepository accountRepository;

    @Autowired
    public AccountOverviewService(ConsumerAccountRepository consumerAccountRepository, SMEAccountRepository smeAccountRepository, AccountRepository accountRepository) {
        this.consumerAccountRepository = consumerAccountRepository;
        this.smeAccountRepository = smeAccountRepository;
        this.accountRepository = accountRepository;
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

    public Account getAccountByID(int accountID) {
        Account retrievedAccount = null;
        Optional<Account> optionalAccount = accountRepository.findById(accountID);
        if (optionalAccount.isPresent()) {
            retrievedAccount = optionalAccount.get();
        }
        return retrievedAccount;
    }

    public boolean accessPermitted(Account account, Customer customer) {
        List<Account> accountsOfCustomer = accountRepository.findAllByHolders(customer);
        for (Account accountOfCustomer: accountsOfCustomer) {
            if (accountOfCustomer.equals(account)) {
                return true;
            }
        }
        return false;
    }
}
