package dsb.web.service.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class VatNoValidator implements ConstraintValidator<VatNoConstraint, String> {


    @Override
    public boolean isValid(String string, ConstraintValidatorContext constraintValidatorContext) {

        if ((string == null || string.trim().equals(""))) return true;

        try {
            Integer.parseInt(string.substring(2, 11));
        } catch (Exception e) {
            return false;
        }
        //actual check
        return VatNoValidation(string);
    }

    private boolean checkFormat(String string) {
        String noSpaces = string.replaceAll("\\s+", "");
        return noSpaces.matches("[N]{1}[L]{1}[0-9]{9}[B]{1}[0-9]{2}");
    }

    private boolean VatNoValidation(String btwNo) {
        int btw2 = Integer.parseInt(btwNo.substring(2, 11));
        if (btw2 <= 0 || btw2 > 999999999 || !checkFormat(btwNo)) {
            return false;
        }

        int sum = -1 * btw2 % 10;
        for (int i = 2; btw2 > 0; i++) {
            int val = (btw2 /= 10) % 10;
            sum += i * val;
        }
        return sum != 0 && sum % 11 == 0;
    }


}


