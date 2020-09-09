package dsb.web.service.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ FIELD })
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = { SufficientFundsValidator.class })
public @interface SufficientFundsConstraint {
    String message() default "Onvoldoende saldo voor deze transactie";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };
}
