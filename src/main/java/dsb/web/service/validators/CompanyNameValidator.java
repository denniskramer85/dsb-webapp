package dsb.web.service.validators;

import dsb.web.domain.Company;
import dsb.web.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;
import java.util.regex.Pattern;

public class CompanyNameValidator implements ConstraintValidator<CompanyNameConstraint, String> {

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {


        if (s == null || s.trim().equals("")) return true;


        return s.matches("^[a-zA-Z0-9@&]*$");

    }


}
