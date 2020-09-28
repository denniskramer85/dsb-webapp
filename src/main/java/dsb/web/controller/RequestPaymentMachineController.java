package dsb.web.controller;

import dsb.web.controller.beans.ConfirmBean;
import dsb.web.controller.beans.LoginBean;
import dsb.web.domain.Customer;
import dsb.web.domain.SMEAccount;
import dsb.web.domain.TokenPaymentMachine;
import dsb.web.repository.CustomerRepository;
import dsb.web.service.RequestPaymentMachineService;
import dsb.web.service.SignInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;


@Controller
@SessionAttributes({AttributeMapping.SELECTED_ACCOUNT, AttributeMapping.LOGGED_IN_CUSTOMER})
public class RequestPaymentMachineController {

    @Autowired
    private RequestPaymentMachineService requestPaymentMachineService;
    private SignInService signInService;
    private CustomerRepository customerRepository;


    public RequestPaymentMachineController(RequestPaymentMachineService requestPaymentMachineService, SignInService signInService, CustomerRepository customerRepository) {
        this.requestPaymentMachineService = requestPaymentMachineService;
        this.signInService = signInService;
        this.customerRepository = customerRepository;
    }

    @GetMapping("request-paymentmachine")
    public String requestPaymentMachineHandler(Model model) {
        model.addAttribute("loginBean", new LoginBean());
        return "confirm_paymentmachine_request";
    }



    @PostMapping("add-paymentmachine")
    public String confirmPaymentMachineHandler(@ModelAttribute("loginBean") LoginBean loginBean,
                                              @ModelAttribute(AttributeMapping.LOGGED_IN_CUSTOMER) Customer loggedInCustomer,
                                              @ModelAttribute(AttributeMapping.SELECTED_ACCOUNT) SMEAccount smeAccount
                                                , Model model){

        Customer passwordCorrect = (Customer) signInService.checkCredentials(loggedInCustomer.getUsername(), loginBean.getPassword());

        if (passwordCorrect != null) {
            //happy flow
            TokenPaymentMachine token = requestPaymentMachineService.createAndSaveToken(smeAccount);
            ConfirmBean confirmBean = new ConfirmBean("Aanvraag geslaagd", "De pinautomaat is succesvol aangevraagd. U ontvangt deze binnen 5 werkdagen per post.", "accountPage", "Terug naar rekening");
            model.addAttribute(confirmBean);
            return "confirm";



        }
        return requestPaymentMachineHandler(model);


    }
}


