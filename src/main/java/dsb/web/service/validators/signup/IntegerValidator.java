package dsb.web.service.validators.signup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class IntegerValidator implements ConstraintValidator<IntegerConstraint, String> {

    @Autowired
    NotEmptyFieldValidator notEmptyFieldValidator;

    @Override
    public boolean isValid(String string, ConstraintValidatorContext constraintValidatorContext) {

        //already covered by more basic validations
        if (!notEmptyFieldValidator.actualCheck(string)) return true;

        return actualCheck(string);
    }

    public boolean actualCheck(String string) {
        try {
            Integer.parseInt(string);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }
}
