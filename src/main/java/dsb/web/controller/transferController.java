package dsb.web.controller;

import dsb.web.controller.beans.LoginBean;
import dsb.web.controller.beans.PrintAccountDataBean;
import dsb.web.controller.beans.TransferBean;
import dsb.web.domain.Account;
import dsb.web.domain.Customer;
import dsb.web.repository.AccountRepository;
import dsb.web.repository.CustomerRepository;
import dsb.web.service.AccountPageService;
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
@SessionAttributes({"loggedInCustomer", "selectedAccountSession"})
public class transferController {

    private AccountPageService accountPageService;

    //TODO weg
    private CustomerRepository customerRepository;
    private AccountRepository accountRepository;
    //private PrintAccountDataBean printAccountDataBean;




    @Autowired
    public transferController(AccountPageService accountPageService,
                              CustomerRepository customerRepository, AccountRepository accountRepository) {
        this.accountPageService = accountPageService;
        this.customerRepository = customerRepository;
        this.accountRepository = accountRepository;
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

        model.addAttribute("transferBean", new TransferBean(account));


        return "transferPage";
    }

    @PostMapping("transfer")
    public String transferDataHandler (@Valid @ModelAttribute TransferBean tb, Errors errors, Model model) {

        /**validate for errors - if so return**/
        if(errors.hasErrors()) {

            //TODO dit evt via flash/redirect uit vorige methode?
            Account account = (Account) model.getAttribute("selectedAccountSession");
            tb.setDebitAccount(account);

            model.addAttribute("transferBean", tb);

            model.addAttribute("printAccountDataBean", accountPageService.makePrintAccountDataBean(account));


            return "transferPage";
        }

        model.addAttribute("transferBean", tb);

        model.addAttribute("loginBean", new LoginBean());



        return "transferConfirmPage";
    }

    @PostMapping("transferConfirm")
    public String transferConfirmHandler (@ModelAttribute LoginBean lb, Model model) {

        System.out.println(lb);


        return "index";
    }
}




