package dsb.web.service.validators.signup;

import dsb.web.domain.Customer;
import dsb.web.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import java.util.List;

import static java.lang.Integer.parseInt;

public class SSNOccupiedValidator implements ConstraintValidator<SSNOccupiedConstraint, String> {

    NotEmptyFieldValidator notEmptyFieldValidator;
    IntegerValidator integerValidator;
    SSNFormalValidator ssnFormalValidator;
    CustomerRepository customerRepository;

    @Autowired
    public SSNOccupiedValidator(NotEmptyFieldValidator notEmptyFieldValidator,
                                IntegerValidator integerValidator,
                                SSNFormalValidator ssnFormalValidator,
                                CustomerRepository customerRepository) {
        this.notEmptyFieldValidator = notEmptyFieldValidator;
        this.integerValidator = integerValidator;
        this.ssnFormalValidator = ssnFormalValidator;
        this.customerRepository = customerRepository;
    }

    @Override
    public boolean isValid(String string, ConstraintValidatorContext constraintValidatorContext) {

        //already covered by more basic validations
        if (!notEmptyFieldValidator.actualCheck(string)) return true;
        if (!integerValidator.actualCheck(string)) return true;
        if (!ssnFormalValidator.actualCheck(parseInt(string))) return true;

        //actual check
        return actualCheck(parseInt(string));
    }

    private boolean actualCheck(int socialSecurityNo) {

        List<Customer> customers = customerRepository.findAllBySocialSecurityNo(socialSecurityNo);
        return customers.size() == 0;

    }


}