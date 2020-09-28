package dsb.web.service.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class SSNFormalValidator implements ConstraintValidator<SSNFormalConstraint, String> {

    @Autowired
    NotEmptyFieldValidator notEmptyFieldValidator;
    @Autowired
    IntegerValidator integerValidator;

    @Override
    public boolean isValid(String string, ConstraintValidatorContext constraintValidatorContext) {

        //already covered by more basic validations
        if (!notEmptyFieldValidator.actualCheck(string)) return true;
        if (!integerValidator.actualCheck(string)) return true;

        //actual check
        return actualCheck(Integer.parseInt(string));
    }

    public boolean actualCheck(int socialSecurityNo) {
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