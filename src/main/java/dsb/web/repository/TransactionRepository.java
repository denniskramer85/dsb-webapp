package dsb.web.repository;

import dsb.web.domain.Transaction;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TransactionRepository extends CrudRepository<Transaction, Integer> {


    //complexe query met variabele ; en sorterne op tijd en max 10


    @Query(value = "SELECT * FROM dsb.transaction WHERE credit_account_accountid = ?1 OR debit_account_accountid = ?1 ORDER BY transaction_timestamp DESC LIMIT 10;",
            nativeQuery = true)
    List<Transaction> findTransactionByAccounts (int accountID);

    List<Transaction> findAll();




}
