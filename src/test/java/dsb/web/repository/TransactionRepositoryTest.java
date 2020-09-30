package dsb.web.repository;

import dsb.web.domain.Account;
import dsb.web.domain.ConsumerAccount;
import dsb.web.domain.Transaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

/*
* Tests TransactionRepository for persistance functionality and custom query findAllTransactionsByAccountID
* */
@DataJpaTest(properties = "spring.jpa.database-platform=org.hibernate.dialect.H2Dialect")
public class TransactionRepositoryTest {
    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    AccountRepository accountRepository;

    Account account1;
    Account account2;
    Account account3;
    Transaction transaction1;
    Transaction transaction2;
    Transaction transaction3;
    List<Transaction> transactions;

    @BeforeEach
    void setup() {
        account1 = new ConsumerAccount("NL10DSBB0123456789", 3000.0, null);
        account2 = new ConsumerAccount("NL20DSBB0123456789", 200.0, null);
        account3 = new ConsumerAccount("NL30DSBB0123456789", 1000.0, null);

        accountRepository.save(account1);
        accountRepository.save(account2);
        accountRepository.save(account3);

        transaction1 = new Transaction(account1, account2, 100.0, "TestTransaction1", LocalDateTime.now());
        transaction2 = new Transaction(account1, account2, 200.0, "TestTransaction2", LocalDateTime.now());
        transaction3 = new Transaction(account1, account3, 300.00, "TestTransaction3", LocalDateTime.now());
    }

    @Test
    void injectedRepositoriesExist() {
        assertThat(transactionRepository).isNotNull();
        assertThat(accountRepository).isNotNull();
    }

    @Test
    void persistTransactions() {
        transactions = transactionRepository.findAll();
        assertThat(transactions).isEmpty();

        transactionRepository.save(transaction1);
        transactionRepository.save(transaction2);
        transactionRepository.save(transaction3);

        transactions = transactionRepository.findAll();
        assertThat(transactions).hasSize(3);
    }

    @Test
    void findTransactionsByAccountID() {
        transactions = transactionRepository.findAll();
        assertThat(transactions).isEmpty();

        transactionRepository.save(transaction1);
        transactionRepository.save(transaction2);
        transactionRepository.save(transaction3);

        transactions = transactionRepository.findAllTransactionsByAccountID(account2.getAccountID());

        assertThat(transactions).contains(transaction1);
        assertThat(transactions).contains(transaction2);
        assertThat(transactions).doesNotContain(transaction3);
        assertThat(transactions).hasSize(2);
    }
}
