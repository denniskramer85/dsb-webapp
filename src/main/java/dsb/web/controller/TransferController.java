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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@SessionAttributes({"loggedInCustomer", "selectedAccountSession", "transferBeanSession"})
public class TransferController {

    private AccountPageService accountPageService;
    private TransferService transferService;

    //TODO weg als dummygegevs niet meer nodig
    private CustomerRepository customerRepository;
    private AccountRepository accountRepository;


    @Autowired
    public TransferController(AccountPageService accountPageService, CustomerRepository customerRepository,
                              AccountRepository accountRepository, TransferService transferService) {
        this.accountPageService = accountPageService;
        this.customerRepository = customerRepository;
        this.accountRepository = accountRepository;
        this.transferService = transferService;

    }

    @GetMapping("transfer")
    public String startTransferPageHandler(Model model) {

        //TODO weg: dit is dummydata vw snelle link (data cust en acc uit db halen en in sessie hangen)
        Optional<Customer> customerOptional = customerRepository.findOneByUsername("dennis");
        Customer customer = customerOptional.get();
        model.addAttribute("loggedInCustomer", customer);
        Optional<Account> accountOptional = accountRepository.findByAccountID(110);
        Account account = accountOptional.get();
        model.addAttribute("selectedAccountSession", account);

        //TODO aanzetten: dit is de normale flow
        //get account data
        //Account account = (Account) model.getAttribute("selectedAccountSession");

        //*********************************************************************************//


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
                                      Errors errors, Model model) {

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

    @PostMapping("transferConfirm")
    public String transferConfirmHandler(@ModelAttribute LoginBean loginBean, Model model) {

        //determine flow depending on whether validation succeeds
        return transferService.determineFlow(loginBean, model);
    }
}




