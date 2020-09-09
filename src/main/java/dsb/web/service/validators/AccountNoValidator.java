package dsb.web.service.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class AccountNoValidator implements ConstraintValidator<AccountNoConstraint, String> {

    public AccountNoValidator() {
    }

    @Override
    public boolean isValid(String accountNo, ConstraintValidatorContext constraintValidatorContext) {

        if (accountNo == null || accountNo.trim().equals("")) return true;

        //TODO: Implementeer IBAN-check van Daan
        if (accountNo.length() > 6 ) {
            return true;
        }
        return false;
    }
}
