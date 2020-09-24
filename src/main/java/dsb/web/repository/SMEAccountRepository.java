package dsb.web.repository;

import dsb.web.domain.*;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;

@Repository
public interface SMEAccountRepository extends PagingAndSortingRepository<SMEAccount, Integer> {

    List<SMEAccount> findAllByCompany_Sector(Sector sector);

    List<SMEAccount> findAllByHolders(Customer customer);

    List<SMEAccount> findTop10ByOrderByBalanceDesc(); // Find group by Average.


}


