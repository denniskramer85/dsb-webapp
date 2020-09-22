package dsb.web.controller;

import dsb.web.controller.beans.LoginBean;
import dsb.web.domain.Customer;
import dsb.web.domain.Employee;
import dsb.web.repository.CustomerRepository;
import dsb.web.service.SignInService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import org.w3c.dom.Attr;

import javax.servlet.http.HttpSession;
import java.util.Optional;
import java.util.function.Consumer;

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

    @GetMapping("sign-out")
    public String signOutHandler(SessionStatus sessionStatus, Model model) {
        // Check if logged in client is employee
        Employee employee = (Employee) model.getAttribute(AttributeMapping.LOGGED_IN_EMPLOYEE);
        if (employee != null) {
            // Return employee sign-in page
            sessionStatus.setComplete();
            return "employee_sign-in";
        }
        // Else return consumer landing page
        sessionStatus.setComplete();
        return "index.html";
    }
}
