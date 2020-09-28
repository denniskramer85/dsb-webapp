

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
    private static final int MIN_SIZE = 6;

    @Autowired
    public UsernameOccupiedValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean isValid(String string, ConstraintValidatorContext constraintValidatorContext) {

        if (string.length() < MIN_SIZE) return true;

        return actualCheck(string);
    }

    private boolean actualCheck(String string) {
        List<User> user = userRepository.findAllByUsername(string);
        return user.size() == 0;
    }
}
