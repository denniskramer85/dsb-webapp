

package dsb.web.service.validators;

import dsb.web.service.validators.signup.UsernameOccupiedConstraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

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
