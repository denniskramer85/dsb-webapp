package dsb.web.controller;

import dsb.web.controller.beans.CustomerBean;
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

        CustomerBean cb2 = (CustomerBean) model.getAttribute("customerBean2");
        model.addAttribute("customerBean", cb2);

        return "sign-up";
    }



    @PostMapping("customerCompleted")
    public String handlerCustomerCompleted (@Valid @ModelAttribute CustomerBean customerBean,
                                            Errors errors, Model model) {

        //validate for errors - if so return
        if(errors.hasErrors()) {
            model.addAttribute(customerBean);
            return "sign-up";
        }

        //style name data to proper format (data level)
        customerBean.nameStyler();

        //create printable name and address data + add to model
        signupService.printNameAndAddress(customerBean, model);

        model.addAttribute("customerBean2", customerBean);
        return "signUpConfirm";
    }

    @GetMapping("customerConfirmed")
    public String handlerCustomerConfirmed(Model model) {
        CustomerBean cb2 = (CustomerBean) model.getAttribute("customerBean2");

        //create real customer (incl. address) from bean + save in DB
        Customer customer = signupService.createAndSaveCustomer(cb2);

        //add customer to session
        model.addAttribute("loggedInCustomer", customer);

        return "account_overview";
    }

}
