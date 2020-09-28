package dsb.web.controller.rest;


import dsb.web.domain.Customer;
import dsb.web.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/ssn-occupied-check")
public class SSNOccupiedController {

    CustomerRepository customerRepository;

    @Autowired
    public SSNOccupiedController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping(value = "/{socSecNo}")
    public Boolean usernameExists(@PathVariable("socSecNo") int username) {


        List<Customer> user = customerRepository.findAllBySocialSecurityNo(username);

        if (user.size() == 0) {
            return Boolean.FALSE;
        } else return Boolean.TRUE;
    }


}
