package dsb.web.repository;


import dsb.web.domain.TokenPaymentMachine;
import dsb.web.service.RequestPaymentMachineService;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;


@Repository
    public interface TokenPaymentMachineRepository extends CrudRepository<TokenPaymentMachine, Integer> {

            List<TokenPaymentMachine> findAll();

            List<TokenPaymentMachine> findTokenPaymentMachineByTokenID(int tokenID);

    }

