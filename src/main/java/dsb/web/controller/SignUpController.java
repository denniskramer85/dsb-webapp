package dsb.web.controller;

import dsb.web.controller.beans.CustomerBean;
import dsb.web.service.SignupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SignUpController {

    private SignupService signupService;

    @Autowired
    public SignUpController(SignupService signupService) {
        this.signupService = signupService;
    }


    @GetMapping("sign-up")
    public String handlerSignUp (Model model) {
        model.addAttribute("customerBean", new CustomerBean());
        return "sign-up";
    }

    @PostMapping("customerCompleted")
    public String handlerCustomerCompleted (@ModelAttribute CustomerBean cb, Model model) {
        model.addAttribute("customerBean", cb);
        return "signUpConfirm";


//        signupService.saveCustomerAndAddress(cb);
//
//        return "index";
    }



}
