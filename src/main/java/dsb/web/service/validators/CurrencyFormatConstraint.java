package dsb.web.service.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ FIELD })
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = { CurrencyFormatValidator.class })
public @interface CurrencyFormatConstraint {

    String message() default "Voer enkel getallen in, gescheiden door één komma";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };
}
