package dsb.web.service.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class VatNoValidator implements ConstraintValidator<VatNoConstraint, String> {



    @Override
    public boolean isValid(String string, ConstraintValidatorContext constraintValidatorContext) {

        if ((string == null || string.trim().equals("")) && (VatNoValidation(string))) return true;

        VatNoValidation(string.substring(2,10));
        String noSpaces = string.replaceAll("\\s+", "");
        return noSpaces.matches("NL[A-Z0-9]{9,9}B[A-Z0-9]{2,2}");

    }



    public boolean VatNoValidation(String btwNo) {

        int total = 0;
        for (int i = 9; i > 2; i--) {
            String cijferStr = btwNo.substring(9 - i, 10 - i);
            int cijfer = Integer.parseInt(cijferStr);
            int vermenigvuldig = i * cijfer;
            total += (vermenigvuldig - 9);

        }
        return total != 0 && total % 11 == 0;

    }
}
