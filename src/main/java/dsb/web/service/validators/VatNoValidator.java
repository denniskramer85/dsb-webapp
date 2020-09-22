package dsb.web.service.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class VatNoValidator implements ConstraintValidator<VatNoConstraint, String> {
    @Override
    public void initialize(VatNoConstraint constraintAnnotation) {

    }

    @Override
    public boolean isValid(String string, ConstraintValidatorContext constraintValidatorContext) {

        if (string == null || string.trim().equals("")) return true;
        try {
            Integer.parseInt(string);
        } catch(NumberFormatException e){
            return true;
        }

        //actual check
        return VatNoValidator(Integer.parseInt(string));

    }

    private boolean VatNoValidator(int vatNo) {
        if (vatNo <= 9999999 || vatNo > 999999999) {
            return false;
        }
        int sum = -1 * vatNo % 10;
        for (int i = 2; vatNo > 0; i++) {
            int val = (vatNo /= 10) % 10;
            sum += i * val;
        }
        return sum != 0 && sum % 11 == 0;
    }



}
