package dsb.web.service.validators.signup;

import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class NotEmptyFieldValidator implements ConstraintValidator<NotEmptyFieldConstraint, String> {

    @Override
    public boolean isValid(String string, ConstraintValidatorContext constraintValidatorContext) {

        return actualCheck(string);
    }

    public boolean actualCheck(String string) {

        if (string == null || string.trim().equals("")) {
            return false;
        } else {
            return true;
        }

    }
}
