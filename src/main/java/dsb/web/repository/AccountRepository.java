package dsb.web.repository;

import dsb.web.domain.Account;
import dsb.web.domain.Customer;
import dsb.web.domain.Transaction;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends CrudRepository<Account, Integer> {

    List<Account> findAll();
    List<Account> findAllByHolders(Customer customer);
    Account findAccountByAccountNo(String accountNo);
    Boolean existsByAccountNo(String accountNo);

}
