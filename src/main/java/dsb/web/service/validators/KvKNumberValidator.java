package dsb.web.service.validators;

import dsb.web.domain.Company;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class KvKNumberValidator implements ConstraintValidator<KvKNumberConstraint, String> {


    @Override
    public boolean isValid(String kvkNumber, ConstraintValidatorContext constraintValidatorContext) {

        if (kvkNumber == null || kvkNumber.trim().equals("")) return true;

        try {
            Integer.parseInt(kvkNumber);
            if (kvkNumber.length() == 8 && kvkNumber.length() < 100000000)
                return true;
        } catch (NumberFormatException e) {

        }
        return false;
    }



}

