package dsb.web.repository;

import dsb.web.domain.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Integer> {

    Optional<Customer> findOneByUsername(String username);

    List<Customer> findAllByUsername(String username);

    List<Customer> findAll();

}
