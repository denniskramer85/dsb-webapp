package dsb.web.controller;

import dsb.web.controller.beans.LoginBean;
import dsb.web.controller.beans.TransferBean;
import dsb.web.domain.Account;
import dsb.web.domain.Customer;
import dsb.web.repository.AccountRepository;
import dsb.web.repository.CustomerRepository;
import dsb.web.service.AccountPageService;
import dsb.web.service.TransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;
import java.util.Optional;

@Controller
@SessionAttributes({AttributeMapping.LOGGED_IN_CUSTOMER, "selectedAccountSession", "transferBeanSession"})
public class TransferController {

    private AccountPageService accountPageService;
    private TransferService transferService;


    @Autowired
    public TransferController(AccountPageService accountPageService, TransferService transferService) {
        this.accountPageService = accountPageService;
        this.transferService = transferService;

    }

    @GetMapping("transfer")
    public String startTransferPageHandler(Model model) {

        //get account data
        Account account = (Account) model.getAttribute("selectedAccountSession");

        //add printable account data (string) to model (only for display purpose)
        model.addAttribute("printAccountDataBean",
                accountPageService.makePrintAccountDataBean(account));

        //add needed real account data to transferBean + model
        TransferBean transferBean = new TransferBean();
        transferBean.setDebitAccountNo(account.getAccountNo());
        transferBean.setAccountBalance(account.getBalance());

        model.addAttribute("transferBean", transferBean);

        return "transferPage";
    }


    @PostMapping("transferPost")
    public String transferDataHandler(@Valid @ModelAttribute TransferBean transferBean,
                                      Errors errors, Model model) throws ParseException {

        //validate for input errors - if so: return to transferPage
        if (errors.hasErrors()) {
            return transferService.validateTransferData(transferBean, model);
        }

        //put transferBean and loginBean in session (and model)
        model.addAttribute("transferBeanSession", transferBean);
        model.addAttribute("loginBean", new LoginBean());
        model.addAttribute("errorMessage", false);

        return "transferConfirmPage";
    }

    @GetMapping("transferEdit")
    public String transferEditHandler(Model model) {

        //gather account and transfer data for returning to previous screen (transfer page)
        Account account = (Account) model.getAttribute("selectedAccountSession");
        model.addAttribute("printAccountDataBean",
                accountPageService.makePrintAccountDataBean(account));

        TransferBean transferBean = (TransferBean) model.getAttribute("transferBeanSession");
        model.addAttribute("transferBean", transferBean);

        return "transferPage";
    }


    @PostMapping("transferConfirm")
    public String transferConfirmHandler(@ModelAttribute LoginBean loginBean, Model model) {

        //add username to loginbean
        Customer customer = (Customer) model.getAttribute(AttributeMapping.LOGGED_IN_CUSTOMER);
        loginBean.setUsername(customer.getUsername());

        //determine flow depending on whether validation succeeds
        return transferService.determineFlow(loginBean, model);
    }

}




