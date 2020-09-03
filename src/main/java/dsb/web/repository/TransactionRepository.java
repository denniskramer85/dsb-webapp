package dsb.web.repository;

import dsb.web.domain.Transaction;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TransactionRepository extends CrudRepository<Transaction, Integer> {


    //complexe query met variabele ; en sorterne op tijd en max 10
    //SELECT * FROM dsb.transaction_dummy WHERE transaction_account_credit_accountid = 24 OR transaction_account_debet_accountid = 24 ORDER BY transaction_timestamp ASC;

    Optional<Transaction> findTransactionByTransactionAccountCreditAndTransactionAccountDebet (int credit, int debet);

}
