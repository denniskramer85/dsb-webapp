package dsb.web.service;

import dsb.web.controller.beans.LoginBean;
import dsb.web.controller.beans.TransferBean;
import dsb.web.domain.Account;
import dsb.web.domain.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpServletRequest;

@Service
@SessionAttributes({"selectedAccountSession", "transferBeanSession"})
public class TransferService {

    private SignInService signInService;
    private AccountPageService accountPageService;


    @Autowired
    public TransferService(SignInService signInService, AccountPageService accountPageService) {
        this.signInService = signInService;
        this.accountPageService = accountPageService;

    }


    public String validateTransferData (TransferBean transferBean, Model model) {
        Account account = (Account) model.getAttribute("selectedAccountSession");
        model.addAttribute("transferBean", transferBean);
        model.addAttribute("printAccountDataBean", accountPageService.makePrintAccountDataBean(account));
        return "transferPage";
    }






    //determine flow [first or repeated iteration?] and contents of tb, model and requests
    public void determineFlowAndContents(TransferBean tb, Model model, HttpServletRequest request) {
        if (request.getAttribute("transferBean") == null) {

            Account account = (Account) model.getAttribute("selectedAccountSession");
            tb.setAccountBalance(account.getBalance());
            tb.setAccountNo(account.getAccountNo());

            model.addAttribute("transferBeanSession", tb);
            model.addAttribute("transferBean", tb);
            model.addAttribute("errorMessage", false);

        } else {
            model.addAttribute("transferBean", request.getAttribute("transferBean"));
            model.addAttribute("errorMessage", true);
        }

        model.addAttribute("loginBean", new LoginBean());
    }







//    public String handleFlowAndContentsThruValidation(LoginBean loginBean, Model model, HttpServletRequest request) {
//        Customer loginCustomer = signInService.checkCredentials(loginBean.getUsername(),
//                loginBean.getPassword());
//
//        //determine if validation is correct
//        if (loginCustomer == null) {
//
//            TransferBean tb_session = (TransferBean) model.getAttribute("transferBeanSession");
//            request.setAttribute("transferBean", tb_session);
//
//            return "forward:transferPost";
//
//        } else {
//
//            request.setAttribute("transferBean", null);
//
//            //TODO doe iets in transfer-/ en of transactionService
//            System.out.println("transactie geslaagd");
//
//            return "redirect:/";
//
//        }
//    }
}
