package dsb.web.service.validators;

import dsb.web.domain.Company;
import dsb.web.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class CompanyNameValidator implements ConstraintValidator<CompanyNameConstraint, String> {

    CompanyRepository companyRepository;

    @Autowired
    public CompanyNameValidator(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {


        List<Company> company = companyRepository.findAllByName(s);
        return company.size() == 0;

    }


}
