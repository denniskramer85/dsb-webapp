package dsb.web.controller;

import dsb.web.controller.beans.LoginBean;
import dsb.web.domain.Customer;
import dsb.web.repository.CustomerRepository;
import dsb.web.service.SignInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
@SessionAttributes(AttributeMapping.LOGGED_IN_CUSTOMER)
public class SignInController {
    private SignInService signInService;

    @Autowired
    public SignInController(SignInService signInService) {
        this.signInService = signInService;
    }

    @GetMapping("sign-in")
    public String signIn(Model model) {
        model.addAttribute("loginBean", new LoginBean());
        return "sign-in";
    }

    @PostMapping("sign-in")
    public ModelAndView signInHandler(
            @ModelAttribute LoginBean loginBean,
            Model model) {
        Customer loginCustomer = signInService.checkCredentials(loginBean.getUsername(), loginBean.getPassword());
        if (loginCustomer != null) {
            model.addAttribute(AttributeMapping.LOGGED_IN_CUSTOMER, loginCustomer);
            return new ModelAndView("redirect:/account_overview");
        } else {
            model.addAttribute("username", loginBean.getUsername());
            model.addAttribute("password", loginBean.getPassword());
            model.addAttribute("loginFailed", "true");
            return new ModelAndView("sign-in");
        }
    }
}
