package dsb.web.repository;

import dsb.web.domain.ConsumerAccount;
import dsb.web.domain.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConsumerAccountRepository extends CrudRepository<ConsumerAccount, Integer> {
    List<ConsumerAccount> findAllByHolders(Customer customer);
}
