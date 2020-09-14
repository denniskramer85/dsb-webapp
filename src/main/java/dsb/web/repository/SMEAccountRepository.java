package dsb.web.repository;

import dsb.web.domain.Account;
import dsb.web.domain.Customer;
import dsb.web.domain.SMEAccount;
import dsb.web.domain.Transaction;
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
    List<SMEAccount> findAllByHolders(Customer customer);
    List<SMEAccount> findAll();
    List<SMEAccount> findTop10ByOrderByBalanceDesc(); // zoek op group bij en everage.


    @Query(value =
            " SELECT L.name avg(balance) AS Average " +
            " FROM account L JOIN smeaccount D " +
            " ON L.accountid = D.accountid " +
            " JOIN company C ON D.company_company_id = C.company_id" +
            " JOIN sector S WHERE C.sector_sector_id = S.sector_id " +
            " group by sector_id ORDER BY Average DESC "
            , nativeQuery = true)
    List<SMEAccount> findAverageBalanceBySector();
//            "JOIN dsb.smeaccount AS sme_ac " +
//                    "   ON ac.accountid = sme_ac.accountid " +
////            "JOIN dsb.company AS com " +
////            "   ON sme.accountid = com.accountid"
//                    , nativeQuery = true)

    /*"SELECT * FROM dsb.account AS ac " +
            "JOIN dsb.smeaccount AS sme_ac " +
            "   ON ac.accountid = sme_ac.accountid " +

            "ORDER BY balance desc; "
            , nativeQuery = true)
     */

    /*  " SELECT name, avg(balance) " +
            " FROM account L " +
            " JOIN smeaccount D " +
            " ON L.accountid = D.accountid " +
            " JOIN company C " +
            " ON D.company_company_id = C.company_id" +
            " JOIN sector S" +
            " WHERE C.sector_sector_id = S.sector_id " +
            " group by sector_id" +
            " ORDER BY balance DESC "

     */

}


