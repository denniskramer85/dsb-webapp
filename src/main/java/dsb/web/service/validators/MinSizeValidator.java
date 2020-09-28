package dsb.web.service.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class MinSizeValidator implements ConstraintValidator<MinSizeConstraint, String> {

    @Autowired
    NotEmptyFieldValidator notEmptyFieldValidator;
    private int minSize;

    @Override
    public void initialize (MinSizeConstraint minSizeConstraint) {
        this.minSize = minSizeConstraint.minSize();
    }

    @Override
    public boolean isValid(String string, ConstraintValidatorContext constraintValidatorContext) {

        return actualCheck(string, minSize);
    }

    public boolean actualCheck(String string, int minSizeMP) {

        if (!notEmptyFieldValidator.actualCheck(string)) return false;

        if (string.trim().length() < minSizeMP) {
            return false;
        } else {
            return true;
        }
    }
}
