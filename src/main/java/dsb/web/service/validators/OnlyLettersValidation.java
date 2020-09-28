package dsb.web.service.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class OnlyLettersValidation implements ConstraintValidator<OnlyLettersConstraint, String> {

    @Autowired
    NotEmptyFieldValidator notEmptyFieldValidator;
    private String specific;
        private static final int MIN_SIZE = 2;

    @Override
    public void initialize (OnlyLettersConstraint onlyLettersConstraint) {
        this.specific = onlyLettersConstraint.specific();
    }


    @Override
    public boolean isValid(String string, ConstraintValidatorContext constraintValidatorContext) {


        return actualTest(string, specific);

    }

    public boolean actualTest(String string, String specific) {
        String regex = "";

        if (specific.equals("surname")) {
            if (string.length() < MIN_SIZE) return true;
            regex = "^[a-zA-Z -]*$";
        }
        if (specific.equals("inserts")) regex = "^[a-zA-Z ]*$";
        if (specific.equals("initials")) regex = "^[a-zA-Z.]*$";

        return string.matches(regex);
    }



}
