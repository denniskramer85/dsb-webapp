package dsb.web.repository;

import dsb.web.domain.Account;
import dsb.web.domain.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {


    @Query(value = "SELECT * FROM dsb.transaction WHERE credit_account_accountid = :accountID OR debit_account_accountid = :accountID ORDER BY transaction_timestamp DESC LIMIT :limit ;",
            nativeQuery = true)
    List<Transaction> findTopNTransactionByAccounts(@Param("accountID") int accountID, @Param("limit") int limit);

    int countTransactionsByCreditAccount(Account account);
    int countTransactionsByDebitAccount(Account account);

    @Query("FROM Transaction T WHERE T.creditAccount.accountID = ?1 OR T.debitAccount.accountID = ?1")
    List<Transaction> findAllTransactionsByAccountID(int accountID);
}
