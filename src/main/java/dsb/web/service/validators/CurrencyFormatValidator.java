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

public class CurrencyFormatValidator implements ConstraintValidator<CurrencyFormatConstraint, String> {
    private Logger logger = LoggerFactory.getLogger(AccountOverviewController.class);

    public CurrencyFormatValidator() {
    }

    @Override
    public boolean isValid(String transferAmount, ConstraintValidatorContext constraintValidatorContext) {
        // Check if any . characters are present
        if (transferAmount.contains(".")) {
            return false;
        }

        // Try to parse transferAmount String to Bigdecimal based on Dutch/German currency formatting,
        // ruling out any other faulty characters
        NumberFormat numberFormat = NumberFormat.getInstance(Locale.GERMAN);
        if (numberFormat instanceof DecimalFormat) {
            DecimalFormat decimalFormat = (DecimalFormat) numberFormat;
            decimalFormat.setParseBigDecimal(true);
            BigDecimal parsedAmount = null;
            try {
                parsedAmount = (BigDecimal) decimalFormat.parse(transferAmount.trim());
                return true;
            } catch (ParseException parseError) {
                logger.debug("Cannot parse transferAmount");;
                return false;
            }
        }
        return false;
    }
}
