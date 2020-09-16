package dsb.web.service.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Constraint(validatedBy = FieldsValueDifferentValidator.class)
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface FieldsValueDifferent {
    String message() default "U kunt geen geld naar dezelfde rekening overboeken";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };

    String first();
    String second();
}
