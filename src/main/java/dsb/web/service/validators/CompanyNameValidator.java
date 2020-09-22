package dsb.web.service.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CompanyNameValidator implements ConstraintValidator<CompanyNameConstraint, String> {


    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {


        return false;
    }


}
