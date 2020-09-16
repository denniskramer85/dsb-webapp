package dsb.web.controller;

import dsb.web.domain.SMEAccount;
import dsb.web.domain.TokenPaymentMachine;
import dsb.web.service.RequestPaymentMachineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes(AttributeMapping.SELECTED_ACCOUNT)
public class RequestPaymentMachineController {

    @Autowired
    private RequestPaymentMachineService requestPaymentMachineService;


    public RequestPaymentMachineController(RequestPaymentMachineService requestPaymentMachineService) {
        super();
        this.requestPaymentMachineService = requestPaymentMachineService;

    }

    @GetMapping("request-paymentmachine")
    public String requestPaymentMachine(Model model, @ModelAttribute(AttributeMapping.SELECTED_ACCOUNT) SMEAccount smeAccount) {
       TokenPaymentMachine token = requestPaymentMachineService.createAndSaveToken(smeAccount);
        return "confirm_paymentmachine_request";
    }
}
