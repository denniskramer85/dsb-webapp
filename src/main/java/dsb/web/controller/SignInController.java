package dsb.web.controller;

import dsb.web.controller.beans.LoginBean;
import dsb.web.domain.Customer;
import dsb.web.repository.CustomerRepository;
import dsb.web.service.SignInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
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
    public String signIn(Model model) {
        model.addAttribute("loginBean", new LoginBean());
        return "sign-in";
    }

    @PostMapping("sign-in")
    public String signInHandler(
            @ModelAttribute LoginBean loginBean,
            Model model) {
        Customer loginCustomer = signInService.checkCredentials(loginBean.getUsername(), loginBean.getPassword());
        if (loginCustomer != null) {
            model.addAttribute("username", loginCustomer.getUsername());
            return "account_overview";
        } else {
            model.addAttribute("username", loginBean.getUsername());
            model.addAttribute("password", loginBean.getPassword());
            model.addAttribute("loginFailed", "true");
            return "sign-in";
        }
    }
}
