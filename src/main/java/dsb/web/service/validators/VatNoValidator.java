package dsb.web.service.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class VatNoValidator implements ConstraintValidator<VatNoConstraint, String> {


    @Override
    public boolean isValid(String string, ConstraintValidatorContext constraintValidatorContext) {

        if (string == null || string.trim().equals("")) return true;


        String noSpaces = string.replaceAll("\\s+", "");
        return noSpaces.matches("NL[A-Z0-9]{9,9}B[A-Z0-9]{2,2}");

    }


}
