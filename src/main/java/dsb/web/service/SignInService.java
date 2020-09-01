package dsb.web.service;

import dsb.web.domain.Customer;
import dsb.web.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.Optional;

@Service
@SessionAttributes("loginCustomer")
public class SignInService {
    private CustomerRepository customerRepository;

    @Autowired
    public SignInService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    // Check if username and password correspond to valid account
    public Customer checkCredentials(String username, String password) {
        Optional<Customer> customerOptional = customerRepository.findOneByUsernameAndPassword(username, password);
        Customer loginCustomer = null;
        if (customerOptional.isPresent()) {
            loginCustomer = customerOptional.get();
        }
        return loginCustomer;
    }
}
