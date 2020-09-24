package dsb.web.service.validators;

import dsb.web.domain.Company;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class KvKNumberValidator implements ConstraintValidator<KvKNumberConstraint, String> {


    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return false;
    }
}

