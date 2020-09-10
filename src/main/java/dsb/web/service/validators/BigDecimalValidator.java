package dsb.web.service.validators;

import dsb.web.controller.AccountOverviewController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

public class BigDecimalValidator implements ConstraintValidator<BigDecimalConstraint, String> {
    private Logger logger = LoggerFactory.getLogger(AccountOverviewController.class);

    public BigDecimalValidator() {
    }

    @Override
    public boolean isValid(String transferAmount, ConstraintValidatorContext constraintValidatorContext) {

        // Try to parse transferAmount String to Bigdecimal based on Dutch/German currenct formatting
        NumberFormat nf = NumberFormat.getInstance(Locale.GERMAN);
        if (nf instanceof DecimalFormat) {
            DecimalFormat df = (DecimalFormat) nf;
            df.setParseBigDecimal(true);
            BigDecimal parsed = null;
            try {
                parsed = (BigDecimal) df.parse(transferAmount.trim());
                return true;
            } catch (ParseException parseError) {
                logger.debug("Cannot parse transferAmount");;
                return false;
            }
        }
        return false;
    }
}
