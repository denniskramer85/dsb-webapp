package dsb.web.repository;

import dsb.web.domain.Account;
import dsb.web.domain.AccountHolderToken;
import dsb.web.domain.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountHolderTokenRepository extends CrudRepository<AccountHolderToken, Integer> {

    List<AccountHolderToken> findAccountHolderTokensByNewAccountHolder(Customer newAccountHolder);



}
