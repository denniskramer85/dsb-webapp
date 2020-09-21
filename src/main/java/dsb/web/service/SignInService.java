package dsb.web.service;

import dsb.web.domain.Customer;
import dsb.web.domain.User;
import dsb.web.repository.CustomerRepository;
import dsb.web.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.Optional;

@Service
public class SignInService {
    private UserRepository userRepository;

    @Autowired
    public SignInService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Check if username and password correspond to valid account
    public User checkCredentials(String username, String password) {

        // Try to find customer based on trimmed username (Not case-sensitive)
        Optional<User> userOptional = userRepository.findOneByUsername(username.trim());
        User loginUser = null;
        if (userOptional.isPresent()) {
            loginUser = userOptional.get();
        } else {
            return loginUser;
        }

        // Trim password and check if passwords match (case sensitive)
        if (loginUser.getPassword().equals(password.trim())) {
            return loginUser;
        } else {
            return null;
        }
    }
}
