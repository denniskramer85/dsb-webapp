package dsb.web.service.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class SocSecNoValidator implements ConstraintValidator<SocSecNoConstraint, String> {


    @Override
    public boolean isValid(String string, ConstraintValidatorContext constraintValidatorContext) {

        //two checks alreay covered by other annotations (@NotBlank & @IntegerValidator)
        if (string == null || string.trim().equals("")) return true;
        try {
            Integer.parseInt(string);
        } catch(NumberFormatException e){
            return true;
        }

        //actual check
        return SocSecNoValidator(Integer.parseInt(string));
    }

    private boolean SocSecNoValidator(int socialSecurityNo) {
        if (socialSecurityNo <= 9999999 || socialSecurityNo > 999999999) {
            return false;
        }
        int sum = -1 * socialSecurityNo % 10;
        for (int i = 2; socialSecurityNo > 0; i++) {
            int val = (socialSecurityNo /= 10) % 10;
            sum += i * val;
        }
        return sum != 0 && sum % 11 == 0;
    }


}