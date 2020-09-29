package dsb.web.service.validators.signup;

import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class FieldsValueMatchValidator implements ConstraintValidator<FieldsValueMatch, Object> {

    private String field;
    private String fieldMatch;
    @Autowired
    NotEmptyFieldValidator notEmptyFieldValidator;

    public void initialize(FieldsValueMatch constraintAnnotation) {
        this.field = constraintAnnotation.field();
        this.fieldMatch = constraintAnnotation.fieldMatch();
    }

    public boolean isValid(Object value, ConstraintValidatorContext context) {

        //setup
        Object fieldValue = new BeanWrapperImpl(value).getPropertyValue(field);
        Object fieldMatchValue = new BeanWrapperImpl(value).getPropertyValue(fieldMatch);

        //already covered by more basic validations
        if (!notEmptyFieldValidator.actualCheck((String)fieldValue)) return true;
        if (!notEmptyFieldValidator.actualCheck((String)fieldMatchValue)) return true;

        return actualCheck(fieldValue, fieldMatchValue);


    }

    private boolean actualCheck(Object fieldValue, Object fieldMatchValue) {
        return fieldValue.equals(fieldMatchValue);
    }
}