package dsb.web.service;

import dsb.web.domain.Customer;
import dsb.web.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.Optional;

@Service
public class SignInService {
    private CustomerRepository customerRepository;

    @Autowired
    public SignInService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    // Check if username and password correspond to valid account
    public Customer checkCredentials(String username, String password) {

        // Try to find customer based on trimmed username (Not case-sensitive)
        Optional<Customer> customerOptional = customerRepository.findOneByUsername(username.trim());
        Customer loginCustomer = null;
        if (customerOptional.isPresent()) {
            loginCustomer = customerOptional.get();
        } else {
            return loginCustomer;
        }

        // Trim password and check if passwords match (case sensitive)
        if (loginCustomer.getPassword().equals(password.trim())) {
            return loginCustomer;
        } else {
            return null;
        }
    }
}
