package dsb.web.service;

import dsb.web.controller.AttributeMapping;
import dsb.web.controller.beans.LoginBean;
import dsb.web.controller.beans.TransferBean;
import dsb.web.domain.Account;
import dsb.web.domain.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpServletRequest;

@Service
@SessionAttributes({"selectedAccountSession", "transferBeanSession"})
public class TransferService {

    private SignInService signInService;
    private TransactionService transactionService;

    @Autowired
    public TransferService(SignInService signInService, TransactionService transactionService) {
        this.signInService = signInService;
        this.transactionService = transactionService;
    }

    //determine flow [first or repeated iteration?] and contents of tb, model and requests
    public void determineFlowAndContents(TransferBean tb, Model model, HttpServletRequest request) {
        if (request.getAttribute("transferBean") == null) {
            // TODO / Miel: setDebitAccount weggehaald uit de TransferBean, kan geen objecten meekrijgen

            Account account = (Account) model.getAttribute("selectedAccountSession");
            tb.setAccountBalance(account.getBalance());
            tb.setDebitAccountNo(account.getAccountNo());



            model.addAttribute("transferBeanSession", tb);
            model.addAttribute("transferBean", tb);
            model.addAttribute("errorMessage", false);

        } else {
            model.addAttribute("transferBean", request.getAttribute("transferBean"));
            model.addAttribute("errorMessage", true);
        }

        model.addAttribute("loginBean", new LoginBean());
    }


    public String handleFlowAndContentsThruValidation(LoginBean loginBean, Model model, HttpServletRequest request) {
        Customer loginCustomer = signInService.checkCredentials(loginBean.getUsername(),
                loginBean.getPassword());

        TransferBean tb_session = (TransferBean) model.getAttribute("transferBeanSession");
        Customer loggedInCustomer = (Customer) model.getAttribute(AttributeMapping.LOGGED_IN_CUSTOMER);

        //determine if validation is correct
        if (loginCustomer == null) {


            request.setAttribute("transferBean", tb_session);
            return "forward:transferPost";

        } else {

            request.setAttribute("transferBean", null);

            // Execute transaction, return to index if passed
            if (transactionService.doTransaction(tb_session, loggedInCustomer)) {
                return "redirect:account_overview";
            } else {
                return "forward:transferPost";
            }



        }
    }
}
