package dsb.web.service;

import dsb.web.domain.*;
import dsb.web.repository.AccountHolderTokenRepository;
import dsb.web.repository.AccountRepository;
import dsb.web.repository.SMEAccountRepository;
import dsb.web.repository.ConsumerAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public List<AccountHolderToken> getAccountHolderTokens(Customer customer){
/*        List<AccountHolderTokenBean> tokens = new ArrayList<>();
        for (AccountHolderToken token : accountHolderTokenRepository.findAccountHolderTokensByNewAccountHolder(customer)){
            tokens.add(new AccountHolderTokenBean(token.getAccount().getAccountNo(),token.getAccount().getHolders().toString(), token));
        }*/
        return accountHolderTokenRepository.findAccountHolderTokensByNewAccountHolder(customer);
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
