package dsb.web.controller.rest;


import dsb.web.domain.User;
import dsb.web.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/username-occupied-check")
public class UsernameOccupiedController {

    UserRepository userRepository;

    @Autowired
    public UsernameOccupiedController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping(value = "/{username}")
    public Boolean usernameExists(@PathVariable("username") String username) {

        List<User> user = userRepository.findAllByUsername(username);

        if (user.size() == 0) {
            return Boolean.FALSE;
        } else return Boolean.TRUE;
    }


}
