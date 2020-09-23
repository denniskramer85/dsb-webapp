package dsb.web.controller;

import dsb.web.controller.beans.ConfirmBean;
import dsb.web.controller.beans.SignUpBean;
import dsb.web.domain.Customer;
import dsb.web.service.SignupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.validation.Valid;


@Controller
@SessionAttributes({"signUpBeanSession", AttributeMapping.LOGGED_IN_CUSTOMER})
public class SignUpController {

    private SignupService signupService;

    @Autowired
    public SignUpController(SignupService signupService) {
        this.signupService = signupService;
    }

    @GetMapping("pre-sign-up")
    public String emptyCustomerDataInSession (Model model) {
        model.addAttribute("signUpBeanSession", new SignUpBean());
        return "redirect:sign-up";
    }


    @GetMapping("sign-up")
    public String handlerSignUp (Model model) {

        SignUpBean signUpBean2 = (SignUpBean) model.getAttribute("signUpBeanSession");
        model.addAttribute("signUpBean", signUpBean2);

        return "sign-up";
    }



    @PostMapping("customerCompleted")
    public String handlerCustomerCompleted (@Valid @ModelAttribute SignUpBean signUpBean,
                                            Errors errors, Model model) {

        //validate for errors - if so return
        if(errors.hasErrors()) {
            model.addAttribute(signUpBean);
            return "sign-up";
        }

        //style name data to proper format (data level)
        signUpBean.nameStyler();

        //create printable name and address data + add to model
        signupService.printNameAndAddress(signUpBean, model);

        model.addAttribute("signUpBeanSession", signUpBean);
        return "signUpConfirm";
    }

    @GetMapping("customerConfirmed")
    public String handlerCustomerConfirmed(Model model) {
        SignUpBean signUpBean2 = (SignUpBean) model.getAttribute("signUpBeanSession");

        //create real customer (incl. address) from bean + save in DB
        Customer customer = signupService.createAndSaveCustomer(signUpBean2);

        //add customer to session
        model.addAttribute(AttributeMapping.LOGGED_IN_CUSTOMER, customer);

        //go to generic confirm page
        ConfirmBean confirmBean = new ConfirmBean("Aanmelding geslaagd", "Gebruiker is succesvol opgeslagen!", "account_overview", "OK");
        model.addAttribute(confirmBean);
        return "confirm";

    }

}
