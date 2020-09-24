package dsb.web.service;

import dsb.web.controller.beans.AccountHolderTokenBean;
import dsb.web.domain.*;
import dsb.web.repository.AccountHolderTokenRepository;
import dsb.web.repository.AccountRepository;
import dsb.web.repository.SMEAccountRepository;
import dsb.web.repository.ConsumerAccountRepository;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class AccountOverviewService {
    private ConsumerAccountRepository consumerAccountRepository;
    private SMEAccountRepository accountRepositorySme;
    private AccountRepository accountRepository;
    private AccountHolderTokenRepository accountHolderTokenRepository;

    @Autowired
    public AccountOverviewService(ConsumerAccountRepository consumerAccountRepository, SMEAccountRepository accountRepositorySme, AccountRepository accountRepository,AccountHolderTokenRepository accountHolderTokenRepository) {
        this.accountHolderTokenRepository = accountHolderTokenRepository;
        this.consumerAccountRepository = consumerAccountRepository;
        this.accountRepositorySme = accountRepositorySme;
        this.accountRepository = accountRepository;
    }

    public AccountOverviewService() {
    }

    public List<AccountHolderTokenBean> getAccountHolderTokens(Customer customer){
        /*Creates accountTokenBean list of all available tokens */
        List<AccountHolderToken> tokens = accountHolderTokenRepository.findAccountHolderTokensByNewAccountHolder(customer);
        List<AccountHolderTokenBean> list = new ArrayList<>();
        for (AccountHolderToken t : tokens)
            list.add(new AccountHolderTokenBean(t.getAccount().getAccountNo(),t.getAccount().getHoldersString(3),Integer.toString(t.getAccountHolderTokenId())));
        return list;
    }

    public List<ConsumerAccount> getConsumerAccountsForCustomer(Customer customer) {
        List<ConsumerAccount> consumerAccountList = consumerAccountRepository.findAllByHolders(customer);
        return consumerAccountList;
    }

    public List<SMEAccount> getSMEAccountsForCustomer(Customer customer) {
        List<SMEAccount> smeAccountList = accountRepositorySme.findAllByHolders(customer);
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
