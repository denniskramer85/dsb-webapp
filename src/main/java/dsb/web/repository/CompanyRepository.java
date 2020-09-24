package dsb.web.repository;

import dsb.web.domain.Company;
import dsb.web.domain.Customer;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CompanyRepository extends CrudRepository<Company, Integer> {

    Company findCompanyByKVKno(String KNKno);

    boolean existsByKVKno(String kvKno);

    List<Company> findAllByName(String companyName);

}


