package dsb.web.service.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class IntegerValidator implements ConstraintValidator<IntegerConstraint, String> {

    @Override
    public boolean isValid(String string, ConstraintValidatorContext constraintValidatorContext) {

        //already provided by @NotBlank
        if (string == null || string.trim().equals("")) return true;

        //actual check
        try {
            Integer.parseInt(string);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }
}
