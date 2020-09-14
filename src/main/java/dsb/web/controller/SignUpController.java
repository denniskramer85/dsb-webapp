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

        /**validate for errors - if so return**/
        if(errors.hasErrors()) {
            model.addAttribute(customerBean);
            return "sign-up";
        }

        //TODO dit kan ook allemaal weg naar service?
        /**namestylers for proper format of initals/surname**/
        customerBean.setInitials(signupService.initialsStyler(customerBean.getInitials()));
        customerBean.setSurname(signupService.surnameStyler(customerBean.getSurname()));

        /**create printable format of name**/
        model.addAttribute("namePrint", signupService.createNamePrint(customerBean));

        /**create printable format of address**/
        model.addAttribute("addressPrint", signupService.createAddressPrint(customerBean));

        model.addAttribute("customerBean2", customerBean);
        return "signUpConfirm";
    }

    @GetMapping("customerConfirmed")
    public String handlerCustomerConfirmed(Model model) {
        CustomerBean cb2 = (CustomerBean) model.getAttribute("customerBean2");

        Customer customer = signupService.createAndSaveCustomer(cb2);

        //TODO naar service?
        /** add domain Customer to session/model **/
        model.addAttribute("loggedInCustomer", customer);

        return "account_overview";
    }





}
