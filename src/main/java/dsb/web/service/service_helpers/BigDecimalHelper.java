package dsb.web.service.service_helpers;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

public class BigDecimalHelper {

    // Try to parse a BigDecimal from a trimmed currency string with European/German locale
    // Return null if parsing not possible (e.g. currency string invalid)
    public static BigDecimal parse(String currencyString) {
        NumberFormat numberFormat = NumberFormat.getInstance(Locale.GERMAN);
        DecimalFormat decimalFormat = (DecimalFormat) numberFormat;
        decimalFormat.setParseBigDecimal(true);
        BigDecimal parsedBigDecimal;
        try {
            parsedBigDecimal = (BigDecimal) decimalFormat.parse(currencyString.trim());
            return parsedBigDecimal;
        } catch (ParseException parseError) {
            parsedBigDecimal = null;
            return parsedBigDecimal;
        }
    }
}
