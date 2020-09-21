package dsb.web.service;

import dsb.web.controller.beans.TransferBean;
import dsb.web.domain.Account;
import dsb.web.domain.Customer;
import dsb.web.domain.Transaction;
import dsb.web.repository.AccountRepository;
import dsb.web.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.Valid;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.time.LocalDateTime;

@Service
public class TransactionService {
    private TransactionRepository transactionRepository;
    private AccountRepository accountRepository;
    private AccountOverviewService accountOverviewService;
    private Account debitAccount;
    private Account creditAccount;
    @PersistenceContext
    EntityManager entityManager;

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


        // Adjust balance for debit account
        debitAccount.setBalance(debitBalanceBefore.subtract(transferAmount).setScale(2, RoundingMode.HALF_UP).doubleValue());

        // Adjust balance for credit account
        creditAccount.setBalance(creditBalanceBefore.add(transferAmount).setScale(2, RoundingMode.HALF_UP).doubleValue());

        // Update new balances in database
        accountRepository.save(debitAccount);
        accountRepository.save(creditAccount);

        updateBalance(creditAccount);
    }

    private void updateBalance(Account account) {
        // Get updated account balance with native SQL query
        Query query = entityManager.createNativeQuery("SELECT (SELECT SUM(transaction_amount) FROM transaction WHERE debit_account_accountid = ?) - (\n" +
                "SELECT SUM(transaction_amount) FROM transaction WHERE credit_account_accountid = ?);");
        query.setParameter(1, account.getAccountID());
        query.setParameter(2, account.getAccountID());

        Double newBalance = (Double) query.getSingleResult();

        System.out.println("Balance of " + account.getAccountNo() + " = " + newBalance);


    }
}
