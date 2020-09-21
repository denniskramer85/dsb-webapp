package dsb.web.service;

import dsb.web.controller.beans.TransferBean;
import dsb.web.domain.*;
import dsb.web.repository.AccountRepository;
import dsb.web.repository.TransactionRepository;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.Valid;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class TransactionService {
    private TransactionRepository transactionRepository;
    private AccountRepository accountRepository;
    private AccountOverviewService accountOverviewService;
    private Account debitAccount;
    private Account creditAccount;
    @PersistenceContext
    EntityManager entityManager;

    private final static int DSB_ACCOUNT = 99999999;
    private final static double SEED_CAPITAL_CONSUMER = 1000.0;
    private final static double SEED_CAPITAL_SME = 10000.0;
    private final static String SEED_CAPITAL_MESSAGE = "DSB Startkapitaal";

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
            updateBalance(debitAccount);
            updateBalance(creditAccount);

            return true;
        }
        return false;
    }

    // Create a pre-filled transaction object from a TransferBean
    private Transaction transactionFactory(TransferBean transferBean) {
        Transaction stagedTransaction = new Transaction();

        stagedTransaction.setDebitAccount(debitAccount);
        stagedTransaction.setCreditAccount(creditAccount);
        stagedTransaction.setTransactionAmount(transferBean.getTransferAmount().doubleValue());
        stagedTransaction.setMessage(transferBean.getMessage());
        stagedTransaction.setTransactionTimestamp(LocalDateTime.now());

        return stagedTransaction;
    }

    // Calculate new balance for account based on full transaction history of account
    public void updateBalance(Account account) {
        // Get updated account balance with native SQL query
        Query query = entityManager.createNativeQuery("SELECT \n" +
                "IFNULL((SELECT SUM(transaction_amount) FROM dsb.transaction WHERE credit_account_accountid = ?), 0) - \n" +
                "IFNULL((SELECT SUM(transaction_amount) FROM dsb.transaction WHERE debit_account_accountid = ?), 0);");
        query.setParameter(1, account.getAccountID());
        query.setParameter(2, account.getAccountID());

        Double balance = (Double) query.getSingleResult();

        // Update balance in account attributes and persist to database
        account.setBalance(balance);
        accountRepository.save(account);
    }

    public void doInitialTransaction(Account account) {
        // Retrieve DSB master account
        Optional<Account> masterAccount = accountRepository.findById(DSB_ACCOUNT);

        // Build transaction
        Transaction initialTransaction = new Transaction();
        initialTransaction.setDebitAccount(masterAccount.get());
        initialTransaction.setCreditAccount(account);
        initialTransaction.setMessage(SEED_CAPITAL_MESSAGE);
        initialTransaction.setTransactionTimestamp(LocalDateTime.now());

        // Handle different seed capital amounts
        if (account instanceof ConsumerAccount) {
            initialTransaction.setTransactionAmount(SEED_CAPITAL_CONSUMER);
        } else if (account instanceof SMEAccount) {
            initialTransaction.setTransactionAmount(SEED_CAPITAL_SME);
        } else {
            return;
        }

        // Persist in database
        transactionRepository.save(initialTransaction);
        updateBalance(account);
    }

    /*    // Calculate new balance through BigDecimal conversion and arithmetics
    private void adjustBalances(Transaction transaction) {
        // Get BigDecimal values of account balances and transfer amount
        BigDecimal debitBalanceBefore = BigDecimal.valueOf(debitAccount.getBalance()).stripTrailingZeros();
        BigDecimal creditBalanceBefore = BigDecimal.valueOf(creditAccount.getBalance()).stripTrailingZeros();
        BigDecimal transferAmount = BigDecimal.valueOf(transaction.getTransactionAmount()).stripTrailingZeros();

        // Adjust balance for debit account
        debitAccount.setBalance(debitBalanceBefore.subtract(transferAmount).setScale(2, RoundingMode.HALF_UP).doubleValue());

        // Adjust balance for credit account
        creditAccount.setBalance(creditBalanceBefore.add(transferAmount).setScale(2, RoundingMode.HALF_UP).doubleValue());

        // Update new balances in database
        accountRepository.save(debitAccount);
        accountRepository.save(creditAccount);

        updateBalance(debitAccount);
    }*/
}
