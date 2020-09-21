package dsb.web.controller;

import dsb.web.controller.beans.LoginBean;
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
        model.addAttribute("loginBean", new LoginBean());
        return "confirm_paymentmachine_request";
    }

    @PostMapping("add-paymentmachine")
    public String confirmPaymentMachine(Model model, @ModelAttribute(AttributeMapping.SELECTED_ACCOUNT) SMEAccount smeAccount, @ModelAttribute ("loginBean") LoginBean loginBean) {
    loginBean.getPassword();
        System.out.println(loginBean);
        TokenPaymentMachine token = requestPaymentMachineService.createAndSaveToken(smeAccount);
        return "index";
    }
}
