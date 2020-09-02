package dsb.web.controller;

import dsb.web.controller.beans.CustomerBean;
import dsb.web.domain.Customer;
import dsb.web.service.SignupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@SessionAttributes({"customerBean2", "loggedInCustomer"})
public class SignUpController {

    private SignupService signupService;

    @Autowired
    public SignUpController(SignupService signupService) {
        this.signupService = signupService;
    }



    @GetMapping("pre-sign-up")
    public String emptyCustomerDataInSession (Model model) {
        model.addAttribute("customerBean2", new CustomerBean());
        return "redirect:sign-up";
    }


    @GetMapping("sign-up")
    public String handlerSignUp (Model model) {

        //session uitlezen
        CustomerBean cb2 = (CustomerBean) model.getAttribute("customerBean2");
        model.addAttribute("customerBean", cb2);

        return "sign-up";
    }



    @PostMapping("customerCompleted")
    public String handlerCustomerCompleted (@ModelAttribute CustomerBean cb, Model model) {
        model.addAttribute("customerBean2", cb);

        //TODO check inbouwen hier
        //servertjek op volledig, nummers, BSN
        //signupService.serverCheck(cb);

        return "signUpConfirm";
    }

    @GetMapping("customerConfirmed")
    public String handlerCustomerConfirmed(Model model) {
        CustomerBean cb2 = (CustomerBean) model.getAttribute("customerBean2");

        Customer customer = signupService.createAndSaveCustomer(cb2);

        /** add domain Customer to session/model **/
        model.addAttribute("loggedInCustomer", customer);

        return "index"; //TODO link to rekeningoverzicht
    }

    // Tijdelijke handler voor postcodetest
    @GetMapping("postcode")
    public String postcodeTestHandler() {
        return "postcode";
    }



}
