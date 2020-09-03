package dsb.web.repository;

import dsb.web.domain.SMEAccount;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SMEAccountRepository extends CrudRepository<SMEAccount, Integer> {
}
