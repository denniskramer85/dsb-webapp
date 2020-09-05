package dsb.web.controller;

import dsb.web.controller.beans.CustomerBean;
import dsb.web.domain.Customer;
import dsb.web.repository.TransactionRepository;
import dsb.web.service.SignupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


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
    public String handlerCustomerCompleted (@ModelAttribute CustomerBean cb, Model model) {


        //TODO
        //aanroepen algemene checkmethode in signupservice
        //returnt een lijst met booleans (goed/fout) (per field)
        signupService.allServerSideChecksBean(cb);




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
