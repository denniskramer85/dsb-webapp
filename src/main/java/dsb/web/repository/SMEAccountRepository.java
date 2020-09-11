package dsb.web.repository;

import dsb.web.domain.Account;
import dsb.web.domain.Customer;
import dsb.web.domain.SMEAccount;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;

@Repository
public interface SMEAccountRepository extends PagingAndSortingRepository<SMEAccount, Integer> {
    List<SMEAccount> findAllByHolders(Customer customer);
    List<SMEAccount> findAll();
    List<SMEAccount> findTop10ByOrderByBalanceDesc(); // zoek op group bij en everage.

}


    //    @Query(value = "SELECT * FROM dsb.account WHERE balance = ?1 ORDER BY balance DESC LIMIT 10;",
//            nativeQuery = true)

//  replace crudrepository for jpa repo.