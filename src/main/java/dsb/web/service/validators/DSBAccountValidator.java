package dsb.web.service.validators;

import dsb.web.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class DSBAccountValidator implements ConstraintValidator<DSBAccountConstraint, String> {
    AccountRepository accountRepository;

    @Autowired
    public DSBAccountValidator(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public boolean isValid(String accountNo, ConstraintValidatorContext constraintValidatorContext) {

        if (accountNo == null || accountNo.trim().equals("")) return true;

        if (accountRepository.existsByAccountNo(accountNo)) {
            return true;
        }

        return false;
    }
}
