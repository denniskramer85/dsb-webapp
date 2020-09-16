package dsb.web.repository;

import dsb.web.domain.Account;
import dsb.web.domain.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends CrudRepository<Account, Integer> {

    List<Account> findAll();
    List<Account> findAllByHolders(Customer customer);
    Account findAccountByAccountNo(String accountNo);
    Boolean existsByAccountNo(String accountNo);
    Optional<Account> findByAccountID (int accountID);

}
