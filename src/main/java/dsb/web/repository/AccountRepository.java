package dsb.web.repository;

import dsb.web.domain.Account;
import dsb.web.domain.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends CrudRepository<Account, Integer> {

    List<Account> findAll();
    List<Account> findAllByHolders(Customer customer);

}
