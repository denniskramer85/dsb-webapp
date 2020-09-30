package dsb.web.controller.rest;


import dsb.web.controller.AttributeMapping;
import dsb.web.domain.Customer;
import dsb.web.repository.CustomerRepository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@SessionAttributes(AttributeMapping.LOGGED_IN_CUSTOMER)
@RestController
@RequestMapping(value = "/transfer-confirm-password")
public class TransferConfirmPasswordController {

    private CustomerRepository customerRepository;

    public TransferConfirmPasswordController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @GetMapping(value = "/{password}")
    public Boolean validatePassword(@PathVariable("password") String password, Model model) {

        Customer customer = customerRepository.findCustomerByUsername(((Customer) model.getAttribute(AttributeMapping.LOGGED_IN_CUSTOMER)).getUsername());

        if (customer.getPassword().equals(password)) {
            return Boolean.FALSE;
        } else return Boolean.TRUE;
    }
}