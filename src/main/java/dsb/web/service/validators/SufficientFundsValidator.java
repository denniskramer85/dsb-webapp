package dsb.web.service.validators;

import dsb.web.controller.beans.TransferBean;
import dsb.web.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.math.BigDecimal;

public class SufficientFundsValidator implements ConstraintValidator<SufficientFundsConstraint, BigDecimal> {
    AccountRepository accountRepository;

    @Autowired
    public SufficientFundsValidator(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public boolean isValid(BigDecimal transactionAmount, ConstraintValidatorContext constraintValidatorContext) {
        // TODO: Implementeer check op saldo uit Database
        if (transactionAmount.doubleValue() > 100.0 ) {
            return false;
        }
        return true;
    }
}
