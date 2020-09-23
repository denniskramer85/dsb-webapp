

package dsb.web.service.validators;

import dsb.web.domain.Customer;
import dsb.web.domain.User;
import dsb.web.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class UsernameOccupiedValidator implements ConstraintValidator<UsernameOccupiedConstraint, String> {

    UserRepository userRepository;

    @Autowired
    public UsernameOccupiedValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean isValid(String string, ConstraintValidatorContext constraintValidatorContext) {

        //already covered by @NotBlank
        if (string == null || string.trim().equals("")) return true;

        //actual check
        List<User> user = userRepository.findAllByUsername(string);

        return user.size() == 0;
    }
}
