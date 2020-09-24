package dsb.web.repository;

import dsb.web.domain.ConsumerAccount;
import dsb.web.domain.Customer;
import dsb.web.domain.SMEAccount;
import dsb.web.domain.Transaction;
import org.hibernate.mapping.Join;
import nonapi.io.github.classgraph.json.JSONUtils;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConsumerAccountRepository extends CrudRepository<ConsumerAccount, Integer> {
    List<ConsumerAccount> findAllByHolders(Customer customer);

/*Query om top10 lijst van particulieren te krijgen (met naam).

    @Query(value = "SELECT * FROM dsb.account AS ac " +
            "JOIN dsb.consumer_account AS c_ac " +
            "   ON ac.accountid = c_ac.accountid ORDER BY balance DESC;  ",
            nativeQuery = true)
    List<ConsumerAccount> findConsumerAccountByHighestBalance();*/


    List<ConsumerAccount> findTop10ByOrderByBalanceDesc();

}
