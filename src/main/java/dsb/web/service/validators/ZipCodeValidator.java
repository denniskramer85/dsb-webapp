
package dsb.web.service.validators;

import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ZipCodeValidator implements ConstraintValidator<ZipCodeConstraint, String> {

    @Autowired
    NotEmptyFieldValidator notEmptyFieldValidator;

    @Override
    public boolean isValid(String string, ConstraintValidatorContext constraintValidatorContext) {

        //already covered by more basic validations
        if (!notEmptyFieldValidator.actualCheck(string)) return true;
        
        return actualCheck(string);
    }

    private boolean actualCheck(String string) {
        String noSpaces = string.replaceAll("\\s+", "");
        return noSpaces.matches("[1-9]{1}[0-9]{3}[a-zA-Z]{2}");
    }
}
