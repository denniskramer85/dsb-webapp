package dsb.web.controller;

import dsb.web.controller.beans.LoginBean;
import dsb.web.controller.beans.TransferBean;
import dsb.web.domain.Account;
import dsb.web.domain.Customer;
import dsb.web.repository.AccountRepository;
import dsb.web.repository.CustomerRepository;
import dsb.web.service.AccountPageService;
import dsb.web.service.SignInService;
import dsb.web.service.TransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Optional;

@Controller
@SessionAttributes({"loggedInCustomer", "selectedAccountSession", "transferBeanSession"})
public class transferController {

    private AccountPageService accountPageService;
    private TransferService transferService;

    //TODO weg
    private CustomerRepository customerRepository;
    private AccountRepository accountRepository;
    private TransferBean tbInClass;


    @Autowired
    public transferController(AccountPageService accountPageService, CustomerRepository customerRepository,
                              AccountRepository accountRepository, SignInService signInService, TransferService transferService) {
        this.accountPageService = accountPageService;
        this.customerRepository = customerRepository;
        this.accountRepository = accountRepository;
        this.transferService = transferService;
    }

    @GetMapping("transfer")
    public String startTransferPageHandler (Model model) {

        //TODO weg: ff dummydata cust en acc uit DB - en in sessie hangen
        Optional<Customer> customerOptional = customerRepository.findOneByUsername("dennis");
        Customer customer = customerOptional.get();
        model.addAttribute("loggedInCustomer", customer);
        Optional<Account> accountOptional = accountRepository.findByAccountID(110);
        Account account = accountOptional.get();
        model.addAttribute("selectedAccountSession", account);


        //TODO aanzetten:
        //Account account = (Account) model.getAttribute("selectedAccountSession");

        model.addAttribute("printAccountDataBean", accountPageService.makePrintAccountDataBean(account));

        // TODO / Miel: Balance en AccountNo meegeven aan de TransferBean om in hidden fields te kunnen zetten
        TransferBean transferBean = new TransferBean();
        transferBean.setAccountNo(account.getAccountNo());
        transferBean.setAccountBalance(account.getBalance());
        model.addAttribute("transferBean", transferBean);

        model.addAttribute("printAccountDataBean", accountPageService.makePrintAccountDataBean(account));

        return "transferPage";
    }


    @PostMapping("transferPost")
    public String transferDataHandler (@Valid @ModelAttribute TransferBean tb,
                                       Errors errors, Model model, HttpServletRequest request) {

        /**validate for errors - if so return**/
        if(errors.hasErrors()) {

            //TODO dit evt via flash/redirect uit vorige methode?
            Account account = (Account) model.getAttribute("selectedAccountSession");
            model.addAttribute("transferBean", tb);

            model.addAttribute("printAccountDataBean", accountPageService.makePrintAccountDataBean(account));


            return "transferPage";
        }

        model.addAttribute("transferBean", tb);

        model.addAttribute("loginBean", new LoginBean());

        //TODO / Miel: Dit heb ik even uitgezet
//        //determine flow and contents of tb, model and requests
//        transferService.determineFlowAndContents(tb, model, request);

        return "transferConfirmPage";
    }

    @PostMapping("transferConfirm")
    public String transferConfirmHandler (@ModelAttribute LoginBean loginBean,
                                          Model model, HttpServletRequest request) {

        return transferService.handleFlowAndContentsThruValidation(loginBean, model, request);

    }
}




