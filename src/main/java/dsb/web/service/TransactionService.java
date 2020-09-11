package dsb.web.service;

import dsb.web.controller.beans.TransferBean;
import dsb.web.domain.Account;
import dsb.web.domain.Customer;
import dsb.web.domain.Transaction;
import dsb.web.repository.AccountRepository;
import dsb.web.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.math.MathContext;
import java.time.LocalDateTime;

@Service
public class TransactionService {
    private TransactionRepository transactionRepository;
    private AccountRepository accountRepository;
    private AccountOverviewService accountOverviewService;
    private Account debitAccount;
    private Account creditAccount;

    @Autowired
    public TransactionService(TransactionRepository transactionRepository, AccountRepository accountRepository, AccountOverviewService accountOverviewService) {
        this.transactionRepository = transactionRepository;
        this.accountRepository = accountRepository;
        this.accountOverviewService = accountOverviewService;
    }

    public TransactionService() {
    }

    public boolean doTransaction(@Valid TransferBean transferBean, Customer loggedInCustomer) {
        debitAccount = accountRepository.findAccountByAccountNo(transferBean.getDebitAccountNo());
        creditAccount = accountRepository.findAccountByAccountNo(transferBean.getCreditAccountNo());

        if (accountOverviewService.accessPermitted(debitAccount, loggedInCustomer)) {
            Transaction stagedTransaction = transactionFactory(transferBean);

            // Save transaction object in database
            transactionRepository.save(stagedTransaction);

            // Adjust account balances for debit- and creditaccount
            adjustBalances(stagedTransaction);

            return true;
        }
        return false;
    }

    private Transaction transactionFactory(TransferBean transferBean) {
        Transaction stagedTransaction = new Transaction();

        stagedTransaction.setDebitAccount(debitAccount);
        stagedTransaction.setCreditAccount(creditAccount);
        stagedTransaction.setTransactionAmount(transferBean.getTransferAmount().doubleValue());
        stagedTransaction.setMessage(transferBean.getMessage());
        stagedTransaction.setTransactionTimestamp(LocalDateTime.now());

        return stagedTransaction;
    }

    // Calculate new balance through BigDecimal conversion and arithmetics
    private void adjustBalances(Transaction transaction) {
        // Get BigDecimal values of account balances and transfer amount
        BigDecimal debitBalanceBefore = BigDecimal.valueOf(debitAccount.getBalance()).stripTrailingZeros();
        BigDecimal creditBalanceBefore = BigDecimal.valueOf(creditAccount.getBalance()).stripTrailingZeros();
        BigDecimal transferAmount = BigDecimal.valueOf(transaction.getTransactionAmount()).stripTrailingZeros();

        MathContext mathContext = new MathContext(2);

        // Adjust balance for debit account
        debitAccount.setBalance(debitBalanceBefore.subtract(transferAmount, mathContext).doubleValue());

        // Adjust balance for credit account
        creditAccount.setBalance(creditBalanceBefore.add(transferAmount, mathContext).doubleValue());
    }
}
