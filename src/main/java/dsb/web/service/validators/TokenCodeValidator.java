

package dsb.web.service.validators;

import dsb.web.domain.Customer;
import dsb.web.repository.CustomerRepository;
import org.hibernate.query.criteria.internal.expression.function.AggregationFunction;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class TokenCodeValidator implements ConstraintValidator<UsernameOccupiedConstraint, String> {
    private static final int MAX_CODE = 99999;
    private static final int MIN_CODE = 0;


    @Override
    public boolean isValid(String string, ConstraintValidatorContext constraintValidatorContext) {

        if (string == null || string.trim().equals("")) return true;

        try{
            int numString = Integer.parseInt(string);
            if (numString < MIN_CODE || numString > MAX_CODE)
                return false;
        }
        catch (Exception e){
            return true;
        }
        return false;
    }
}
