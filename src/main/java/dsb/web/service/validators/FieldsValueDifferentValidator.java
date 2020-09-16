package dsb.web.service.validators;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapperImpl;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.constraints.Null;

public class FieldsValueDifferentValidator implements ConstraintValidator<FieldsValueDifferent, Object> {
    private String firstFieldName;
    private String secondFieldName;

    @Override
    public void initialize(final FieldsValueDifferent constraintAnnotation)
    {
        firstFieldName = constraintAnnotation.first();
        secondFieldName = constraintAnnotation.second();
    }

    public boolean isValid(final Object value, final ConstraintValidatorContext context)
    {
        try {
            final Object firstObj = new BeanWrapperImpl(value).getPropertyValue(firstFieldName);
            final Object secondObj = new BeanWrapperImpl(value).getPropertyValue(secondFieldName);

            return !firstObj.equals(secondObj);
        } catch (NullPointerException nullPointerException) {
            return true;
        }

    }
}
