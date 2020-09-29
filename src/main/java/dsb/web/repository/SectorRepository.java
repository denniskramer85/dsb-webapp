package dsb.web.repository;

import dsb.web.domain.Employee;
import dsb.web.domain.Sector;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SectorRepository extends CrudRepository<Sector, Integer> {
    List<Sector> findAll();
}