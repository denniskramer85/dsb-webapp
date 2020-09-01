package dsb.web.controller;

import dsb.web.controller.beans.CustomerBean;
import dsb.web.service.SignupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@SessionAttributes("customerBean2")
public class SignUpController {

    private SignupService signupService;

    @Autowired
    public SignUpController(SignupService signupService) {
        this.signupService = signupService;
    }


    @GetMapping("sign-up")
    public String handlerSignUp (Model model) {
        model.addAttribute("customerBean", new CustomerBean());
        return "sign-up/sign-up";
    }

    @PostMapping("customerCompleted")
    public String handlerCustomerCompleted (@ModelAttribute CustomerBean cb, Model model) {
        model.addAttribute("customerBean2", cb);
        return "signUpConfirm";
    }

    @GetMapping("customerConfirmed")
    public String handlerCustomerConfirmed(Model model) {
        CustomerBean cb = (CustomerBean) model.getAttribute("customerBean2");
        signupService.saveCustomerAndAddress(cb);



        return "index";
    }







}
