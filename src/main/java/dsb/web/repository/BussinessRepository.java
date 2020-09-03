package dsb.web.repository;

import dsb.web.domain.Company;
import org.springframework.data.repository.CrudRepository;

public interface BussinessRepository extends CrudRepository<Company, Integer> {
}
