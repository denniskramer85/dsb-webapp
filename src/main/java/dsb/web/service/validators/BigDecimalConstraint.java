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
@Constraint(validatedBy = { BigDecimalValidator.class })
public @interface BigDecimalConstraint {

    String message() default "Voer een bedrag in euro's en centen in, gescheiden voor een komma";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };
}
