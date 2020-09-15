package dsb.web.service;

import dsb.web.domain.Account;
import dsb.web.domain.AccountHolderToken;
import dsb.web.domain.Customer;
import dsb.web.repository.AccountHolderTokenRepository;
import dsb.web.repository.AccountRepository;
import dsb.web.repository.ConsumerAccountRepository;
import dsb.web.repository.SMEAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddAccountHolderService {
    private ConsumerAccountRepository consumerAccountRepository;
    private SMEAccountRepository smeAccountRepository;
    private AccountRepository accountRepository;
    private AccountHolderTokenRepository accountHolderTokenRepository;

    @Autowired
    public AddAccountHolderService(ConsumerAccountRepository consumerAccountRepository, SMEAccountRepository smeAccountRepository, AccountRepository accountRepository, AccountHolderTokenRepository accountHolderTokenRepository) {
        this.accountHolderTokenRepository = accountHolderTokenRepository;
        this.consumerAccountRepository = consumerAccountRepository;
        this.smeAccountRepository = smeAccountRepository;
        this.accountRepository = accountRepository;
    }

    public AccountHolderToken createAddAccountHolderToken(Customer userToAdd, Account accountToAddTo, int token) {
        return accountHolderTokenRepository.save(new AccountHolderToken(userToAdd,accountToAddTo,token));
    }
}
