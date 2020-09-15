package dsb.web.repository;

import dsb.web.domain.AccountHolderToken;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountHolderTokenRepository extends CrudRepository<AccountHolderToken, Integer> {


}
