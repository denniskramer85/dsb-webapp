package dsb.web.controller;

import dsb.web.controller.beans.CustomerBean;
import dsb.web.domain.Customer;
import dsb.web.repository.TransactionRepository;
import dsb.web.service.SignupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@Controller
@SessionAttributes({"customerBean2", "loggedInCustomer"})
public class SignUpController {

    private SignupService signupService;
    private TransactionRepository transactionRepository;

    @Autowired
    public SignUpController(SignupService signupService, TransactionRepository transactionRepository) {
        this.signupService = signupService;
        this.transactionRepository = transactionRepository;
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

        //return "sign-up";
        return "sign-up";
    }



    @PostMapping("customerCompleted")
    public String handlerCustomerCompleted (@Valid @ModelAttribute CustomerBean cb, Errors errors, Model model) {

        /**validate for errors - is fo return**/
        if(errors.hasErrors()) {
            model.addAttribute(cb);
            return "sign-up";
        }

        /**namestylers for proper format of initals/surname**/
        cb.setInitials(signupService.initialsStyler(cb.getInitials()));
        cb.setSurname(signupService.surnameStyler(cb.getSurname()));

        model.addAttribute("customerBean2", cb);
        return "signUpConfirm";
    }

    @GetMapping("customerConfirmed")
    public String handlerCustomerConfirmed(Model model) {
        CustomerBean cb2 = (CustomerBean) model.getAttribute("customerBean2");

        Customer customer = signupService.createAndSaveCustomer(cb2);

        /** add domain Customer to session/model **/
        model.addAttribute("loggedInCustomer", customer);

        return "account_overview"; //TODO link to rekeningoverzicht
    }





}
