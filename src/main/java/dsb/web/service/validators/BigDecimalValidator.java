package dsb.web.service.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

public class BigDecimalValidator implements ConstraintValidator<BigDecimalConstraint, String> {

    public BigDecimalValidator() {
    }

    @Override
    public boolean isValid(String transferAmount, ConstraintValidatorContext constraintValidatorContext) {
        String convertedAmount = transferAmount.replaceAll(",", ".");

        // Try to convert currency String to BigDecimal, generate true or false based on outcome
        try {
            BigDecimal bigDecimalAmount = new BigDecimal(convertedAmount);
            return true;
        } catch (NumberFormatException exception) {
            System.out.println("Mislukt!");
            return false;
        }
    }
}
