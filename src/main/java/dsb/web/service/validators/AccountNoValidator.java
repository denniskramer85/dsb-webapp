package dsb.web.service.validators;

import dsb.web.service.IbanService;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class AccountNoValidator implements ConstraintValidator<AccountNoConstraint, String> {

    public AccountNoValidator() {
    }

    @Override
    public boolean isValid(String accountNo, ConstraintValidatorContext constraintValidatorContext) {

        if (accountNo == null || accountNo.trim().equals("")) return true;

        if (IbanService.verifyIban(accountNo)) {
            return true;
        }
        return false;
    }
}
