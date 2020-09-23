package dsb.web.repository;

import dsb.web.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

        Optional<User> findOneByUsername(String username);
}
