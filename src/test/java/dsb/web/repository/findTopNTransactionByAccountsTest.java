package dsb.web.repository;

import dsb.web.domain.SMEAccount;
import dsb.web.domain.Transaction;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

//TEST DOES NOT FUNCTION - left here for educational purposes
@Disabled
@DataJpaTest(properties = "spring.jpa.database-platform=org.hibernate.dialect.H2Dialect")
class findTopNTransactionByAccountsTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private TransactionRepository transactionRepository;


    public findTopNTransactionByAccountsTest() {
        super();
    }


    @Test
    void findTopNTransactionByAccounts() {
        LocalDateTime ldt = LocalDateTime.of(2017, Month.FEBRUARY,3,6,30,40,50000);

        SMEAccount acc1 = new SMEAccount();
        acc1.setAccountNo("NL21DSBB0123456784");
        SMEAccount acc2 = new SMEAccount();
        acc2.setAccountNo("123");
        SMEAccount acc3 = new SMEAccount();
        acc3.setAccountNo("456");

        entityManager.persist(acc1);
        entityManager.persist(acc2);
        entityManager.persist(acc3);
        entityManager.flush();

        Transaction t1 = new Transaction  (100, acc1, acc2, 100, "Verjaardag", ldt);
        Transaction t2 = new Transaction  (101, acc3, acc1, 100, "Verjaardag", ldt);
        Transaction t3 = new Transaction  (102, acc2, acc3, 100, "Verjaardag", ldt);
        Transaction t4 = new Transaction  (103, acc2, acc1, 100, "Verjaardag", ldt);
        Transaction t5 = new Transaction  (104, acc1, acc3, 100, "Verjaardag", ldt);
        Transaction t6 = new Transaction  (105, acc3, acc2, 100, "Verjaardag", ldt);

        transactionRepository.save(t1);
        transactionRepository.save(t2);
        transactionRepository.save(t3);
        transactionRepository.save(t4);
        transactionRepository.save(t5);
        transactionRepository.save(t6);

        //yields error
        List<Transaction> result = transactionRepository.findTopNTransactionByAccounts(1, 2);
        System.out.println("RESULTAAT");
        for (Transaction t : result) {
            System.out.println(t);
        }



    }
}

