

package dsb.web.service.validators.signup;

import dsb.web.domain.User;
import dsb.web.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class UsernameOccupiedValidator implements ConstraintValidator<UsernameOccupiedConstraint, String> {

    UserRepository userRepository;
    MinSizeValidator minSizeValidator;
    private static final int MIN_SIZE = 6;

    @Autowired
    public UsernameOccupiedValidator(UserRepository userRepository,
                                     MinSizeValidator minSizeValidator) {
        this.userRepository = userRepository;
        this.minSizeValidator = minSizeValidator;
    }

    @Override
    public boolean isValid(String string, ConstraintValidatorContext constraintValidatorContext) {

        //already covered by more basic validations
        if (!minSizeValidator.actualCheck(string, MIN_SIZE)) return true;

        return actualCheck(string);
    }

    private boolean actualCheck(String string) {
        List<User> user = userRepository.findAllByUsername(string);
        return user.size() == 0;
    }
}
