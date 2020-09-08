

package dsb.web.service.validators;

import dsb.web.domain.Customer;
import dsb.web.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class UsernameOccupiedValidator implements ConstraintValidator<UsernameOccupiedConstraint, String> {

    CustomerRepository customerRepository;

    @Autowired
    public UsernameOccupiedValidator(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public boolean isValid(String string, ConstraintValidatorContext constraintValidatorContext) {

        if (string == null || string.trim().equals("")) return true;

        List<Customer> customer = customerRepository.findAllByUsername(string);

        if (customer.size() == 0) {
            return true;
        } else return false;
    }
}
