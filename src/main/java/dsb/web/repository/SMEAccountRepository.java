package dsb.web.repository;

import dsb.web.domain.*;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface SMEAccountRepository extends PagingAndSortingRepository<SMEAccount, Integer> {

    List<SMEAccount> findAllByCompany_Sector(Sector sector);

    List<SMEAccount> findAllByHolders(Customer customer);

    List<SMEAccount> findTop10ByOrderByBalanceDesc();

}


