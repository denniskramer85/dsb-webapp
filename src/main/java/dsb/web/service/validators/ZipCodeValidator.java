
package dsb.web.service.validators;

        import javax.validation.ConstraintValidator;
        import javax.validation.ConstraintValidatorContext;

public class ZipCodeValidator implements ConstraintValidator<ZipCodeConstraint, String> {


    @Override
    public boolean isValid(String string, ConstraintValidatorContext constraintValidatorContext) {

        if (string == null || string.equals("")) return true;


        String noSpaces = string.replaceAll("\\s+", "");
        return noSpaces.matches("[1-9]{1}[0-9]{3}[a-zA-Z]{2}");

    }
}
