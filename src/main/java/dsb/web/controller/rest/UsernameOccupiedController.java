package dsb.web.controller.rest;


import dsb.web.controller.beans.SignUpBean;
import dsb.web.controller.beans.TransferNameBean;
import dsb.web.domain.Account;
import dsb.web.domain.Customer;
import dsb.web.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/username-occupied-check")
public class UsernameOccupiedController {

    CustomerRepository customerRepository;

    @Autowired
    public UsernameOccupiedController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping(value = "/{username}")
    public Boolean usernameExists(@PathVariable("username") String username) {

        List<Customer> customer = customerRepository.findAllByUsername(username);

        if (customer.size() == 0) {
            return Boolean.FALSE;
        } else return Boolean.TRUE;
    }


}
