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

    @Override
    public void initialize (OnlyLettersConstraint onlyLettersConstraint) {
        this.specific = onlyLettersConstraint.specific();
    }


    @Override
    public boolean isValid(String string, ConstraintValidatorContext constraintValidatorContext) {

        System.out.println(specific);

        return checkOnlyLetters(string);



    }

    public boolean checkOnlyLetters(String string) {

        return string.matches("^[a-zA-Z -]*$");

    }



}
