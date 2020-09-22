package dsb.web.repository;

import dsb.web.domain.Company;
import org.springframework.data.repository.CrudRepository;

public interface CompanyRepository extends CrudRepository<Company, Integer> {

    Company findCompanyByKVKno(String KNKno);

    Company existsByKVKno(String kvKno);
}
