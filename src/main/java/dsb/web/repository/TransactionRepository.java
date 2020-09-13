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



    @Query(value = "SELECT * FROM dsb.transaction WHERE credit_account_accountid = :accountID OR debit_account_accountid = :accountID ORDER BY transaction_timestamp DESC LIMIT :limit ;",
            nativeQuery = true)
    List<Transaction> findTopNTransactionByAccounts(@Param("accountID") int accountID, @Param("limit") int limit);

    List<Transaction> findAll();
}
