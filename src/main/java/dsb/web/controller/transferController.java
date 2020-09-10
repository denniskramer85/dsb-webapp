package dsb.web.controller;

import dsb.web.controller.beans.LoginBean;
import dsb.web.controller.beans.PrintAccountDataBean;
import dsb.web.controller.beans.TransferBean;
import dsb.web.domain.Account;
import dsb.web.domain.Customer;
import dsb.web.repository.AccountRepository;
import dsb.web.repository.CustomerRepository;
import dsb.web.service.AccountPageService;
import dsb.web.service.SignInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Optional;

@Controller
@SessionAttributes({"loggedInCustomer", "selectedAccountSession", "transferBeanSession"})
public class transferController {

    private AccountPageService accountPageService;
    private SignInService signInService;

    //TODO weg
    private CustomerRepository customerRepository;
    private AccountRepository accountRepository;
    private TransferBean tbInClass;


    @Autowired
    public transferController(AccountPageService accountPageService, CustomerRepository customerRepository,
                              AccountRepository accountRepository, SignInService signInService) {
        this.accountPageService = accountPageService;
        this.signInService = signInService;
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

        TransferBean tb = new TransferBean();
        tb.setDebitAccount(account);
        model.addAttribute("transferBean", new TransferBean());

        model.addAttribute("printAccountDataBean", accountPageService.makePrintAccountDataBean(account));

        return "transferPage";
    }

    @PostMapping("transferPost")
    public String transferDataHandler (@Valid @ModelAttribute TransferBean tb,
                                       Errors errors, Model model, HttpServletRequest request) {

            //TODO DIT AANZETEN VOOR MIEL ZN TJEKS
//        /**validate for errors - if so return**/
//        if(errors.hasErrors()) {
//
//            //TODO dit evt via flash/redirect uit vorige methode?
//            Account account = (Account) model.getAttribute("selectedAccountSession");
//            tb.setDebitAccount(account);
//
//            model.addAttribute("transferBean", tb);
//
//            model.addAttribute("printAccountDataBean", accountPageService.makePrintAccountDataBean(account));
//
//
//            return "transferPage";
//        }

        //hier tb weer aanvullen met account en in sessie zetten
        tb.setDebitAccount((Account) model.getAttribute("selectedAccountSession"));
        model.addAttribute("transferBeanSession", tb);

        if (request.getAttribute("transferBean") == null) {

            //eerste flow
            model.addAttribute("transferBean", tb);
            model.addAttribute("loginBean", new LoginBean());
            return "transferConfirmPage";


        } else {
            //herhaalde flow

            model.addAttribute("transferBean", request.getAttribute("transferBean"));
            model.addAttribute("loginBean", new LoginBean());
            return "transferConfirmPage";
        }



    }

    @PostMapping("transferConfirm")
    public String transferConfirmHandler (@ModelAttribute LoginBean loginBean,
                                          Model model, HttpServletRequest request) {

        TransferBean tb_session = (TransferBean) model.getAttribute("transferBeanSession");
        //System.out.println("dit nu op goeie plek" + tb_session);

        request.setAttribute("transferBean", tb_session);


        return "forward:transferPost";




        //        //check of cust in db zit
//        Customer loginCustomer = signInService.checkCredentials(loginBean.getUsername(), loginBean.getPassword());
//
//        if (loginBean != null) {
//            //TODO doe iets in transferService
//
//            return "redirect:accountPage";
//        } else {
//
//
//            model.addAttribute(tbInClass);
//            model.addAttribute("loginBean", new LoginBean());
//
//            return "redirect:transferPost";
//
//
//        }


    }
}




