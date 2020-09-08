

package dsb.web.service.validators;

        import org.springframework.beans.factory.annotation.Autowired;

        import javax.validation.ConstraintValidator;
        import javax.validation.ConstraintValidatorContext;

public class UsernameOccupiedValidator implements ConstraintValidator<UsernameOccupiedConstraint, String> {

//    CustomerRepository customerRepository;
//
//    @Autowired
//    public UsernameOccupiedValidator(CustomerRepository customerRepository) {
//        this.customerRepository = customerRepository;
//    }

    @Override
    public boolean isValid(String string, ConstraintValidatorContext constraintValidatorContext) {

        //TODO logic for check
//        List<Customer> customer = customerRepository.getByUsername();
//
//        if (customer.size() == 0) {
//            return true;
//        } else return false;

        return false;
    }
}
