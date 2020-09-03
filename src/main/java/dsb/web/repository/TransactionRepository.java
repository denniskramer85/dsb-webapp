package dsb.web.repository;

import dsb.web.domain.Transaction;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TransactionRepository extends CrudRepository<Transaction, Integer> {


    //complexe query met variabele ; en sorterne op tijd en max 10


    //TODO nog een limit eraan
    @Query(value = "SELECT * FROM dsb.transaction WHERE transaction_account_credit_accountid = ?1 OR transaction_account_debet_accountid = ?1 ORDER BY transaction_timestamp ASC;",
            nativeQuery = true)
    List<Transaction> findTransactionByAccounts (int accountID);

    List<Transaction> findAll();
}
