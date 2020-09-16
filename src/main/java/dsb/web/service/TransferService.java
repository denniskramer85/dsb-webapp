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

@Service
@SessionAttributes({"selectedAccountSession", "transferBeanSession"})
public class TransferService {

    private AccountPageService accountPageService;
    private SignInService signInService;
    private TransactionService transactionService;



    @Autowired
    public TransferService(AccountPageService accountPageService,
                           SignInService signInService, TransactionService transactionService) {
        this.accountPageService = accountPageService;
        this.signInService = signInService;
        this.transactionService = transactionService;
    }

    public String validateTransferData (TransferBean transferBean, Model model) {
        Account account = (Account) model.getAttribute("selectedAccountSession");
        model.addAttribute("transferBean", transferBean);
        model.addAttribute("printAccountDataBean", accountPageService.makePrintAccountDataBean(account));
        return "transferPage";
    }

    public String determineFlow(LoginBean loginBean, Model model) {

        Customer loggedInCustomer = (Customer) model.getAttribute(AttributeMapping.LOGGED_IN_CUSTOMER);

        Customer loginCustomer = signInService.
                checkCredentials(loginBean.getUsername(), loginBean.getPassword());

        return checkPassword(model, loggedInCustomer, loginCustomer);
    }


    private String checkPassword(Model model, Customer loggedInCustomer, Customer loginCustomer) {

        TransferBean transferBean = (TransferBean) model.getAttribute("transferBeanSession");

        //determine if validation is correct
        if (loginCustomer == null) {
            return ifPasswordIncorrect(model, transferBean);
        } else {
            return ifPasswordCorrect(loggedInCustomer, transferBean);
        }
    }

    private String ifPasswordIncorrect(Model model, TransferBean transferBean) {
        model.addAttribute("transferBeanSession", transferBean);
        model.addAttribute("loginBean", new LoginBean());
        model.addAttribute("errorMessage", true);
        return "transferConfirmPage";
    }

    private String ifPasswordCorrect(Customer loggedInCustomer, TransferBean transferBean) {
        // Execute transaction, return to index if passed
        if (transactionService.doTransaction(transferBean, loggedInCustomer)) {
            System.out.println("Transaction succesfull");
            return "redirect:account_overview";
        } else {
            //TODO: Handle unauthorized transaction
            return "/";
        }
    }

}
