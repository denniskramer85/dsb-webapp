package dsb.web.service.validators;

import dsb.web.controller.AccountOverviewController;
import dsb.web.service.service_helpers.BigDecimalHelper;
import org.apache.commons.lang3.StringUtils;
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
        // To prevent double errors, let through null values
        if (transferAmount == null || transferAmount == "") {
            return true;
        }

        // Trim input
        transferAmount = transferAmount.trim();

        // Check if any nonnumeric characters are present, allow one decimal comma
        if (!StringUtils.isNumeric(transferAmount.replaceFirst(",", ""))) {
            return false;
        }

        // Check if string can be converted to BigDecimal
        if (BigDecimalHelper.parse(transferAmount) != null) {
            return true;
        }

        return false;
    }
}