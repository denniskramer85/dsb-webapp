package dsb.web.service;

import dsb.web.domain.Account;
import dsb.web.repository.AccountRepository;
import dsb.web.repository.ConsumerAccountRepository;
import dsb.web.repository.SMEAccountRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApplicationStartupService {
    private AccountRepository accountRepository;
    private ConsumerAccountRepository consumerAccountRepository;
    private SMEAccountRepository smeAccountRepository;
    private TransactionService transactionService;

    public ApplicationStartupService(AccountRepository accountRepository, ConsumerAccountRepository consumerAccountRepository, SMEAccountRepository smeAccountRepository, TransactionService transactionService) {
        this.accountRepository = accountRepository;
        this.consumerAccountRepository = consumerAccountRepository;
        this.smeAccountRepository = smeAccountRepository;
        this.transactionService = transactionService;
    }

    public void setupBalances() {
        List<Account> accounts = accountRepository.findAll();
        for (Account account : accounts) {
            System.out.println(account);
        }
    }
}
