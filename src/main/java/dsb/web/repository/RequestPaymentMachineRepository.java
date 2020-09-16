package dsb.web.repository;


import dsb.web.domain.TokenPaymentMachine;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;



 @Repository
    public interface RequestPaymentMachineRepository extends CrudRepository<TokenPaymentMachine, Integer> {





    }

