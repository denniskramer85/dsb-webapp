package dsb.web.controller;

import dsb.web.domain.Customer;
import dsb.web.repository.CustomerRepository;
import dsb.web.service.SignInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class SignInController {
    private SignInService signInService;
    private CustomerRepository customerRepository;

    @Autowired
    public SignInController(SignInService signInService, CustomerRepository customerRepository) {
        this.signInService = signInService;
        this.customerRepository = customerRepository;
    }

    @GetMapping("sign-in")
    public String signIn() {
        return "sign-in";
    }

    @PostMapping("sign-in")
    public String signInHandler(
            @RequestParam(name = "input_username") String username,
            @RequestParam(name = "input_password") String password,
            Model model) {
        Customer loginCustomer = signInService.checkCredentials(username, password);
        if (loginCustomer != null) {
            model.addAttribute("username", loginCustomer.getUsername());
            return "account_overview";
        } else {
            model.addAttribute("username", username);
            model.addAttribute("password", password);
            model.addAttribute("loginFailed", "true");
            return "sign-in";
        }
    }
}
