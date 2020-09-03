package dsb.web.repository;

import dsb.web.domain.Customer;
import dsb.web.domain.SMEAccount;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SMEAccountRepository extends CrudRepository<SMEAccount, Integer> {
    List<SMEAccount> findAllByHolders(Customer customer);
}
