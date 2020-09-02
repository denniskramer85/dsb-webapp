package dsb.web.repository;

import dsb.web.domain.Address;
import dsb.web.domain.Bussiness;
import org.springframework.data.repository.CrudRepository;

public interface BussinessRepository extends CrudRepository<Bussiness, Integer> {
}
