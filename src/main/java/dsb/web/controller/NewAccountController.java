package dsb.web.controller;

import dsb.web.controller.beans.NewAccountBean;
import dsb.web.domain.Customer;
import dsb.web.repository.AccountRepository;
import dsb.web.repository.CompanyRepository;
import dsb.web.repository.CustomerRepository;
import dsb.web.service.NewAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@SessionAttributes({AttributeMapping.LOGGED_IN_CUSTOMER, "newAccountBean"})
@Controller
public class NewAccountController {
    private NewAccountService newAccountService;
    private AccountRepository accountRepository;
    private CompanyRepository bussinessRepository;
    private CustomerRepository customerRepository;

    public NewAccountBean newAccountBean(){
        return new NewAccountBean();
    }

    @Autowired
    public NewAccountController(NewAccountService newAccountService, AccountRepository accountRepository, CompanyRepository bussinessRepository) {
        this.newAccountService = newAccountService;
        this.accountRepository = accountRepository;
        this.bussinessRepository = bussinessRepository;
    }

    @GetMapping("new-account")
    public String newAccountSetup(){
        return "new-account";

    }

    @PostMapping("new-account")
    public String newAccountType(
            @RequestParam(name = "accountType") int accountType,
            Model model) {
        NewAccountBean newAccountBean = new NewAccountBean();
        newAccountBean.setBalance(100);
        newAccountBean.setAccountNo(newAccountService.buildIBAN());
        model.addAttribute("newAccountBean", newAccountBean);
        if (accountType == 0) {                         // if Radio 'partiuculier' was selected
            return "confirm-new-account";
        } else if (accountType == 1) {                  // if Radio 'zakelijk' was selected
            return "company-details";
        }
        return "index";
    }

    @GetMapping("confirm-new-account")
    public String confirmNewAccount(
            @ModelAttribute(name = "newAccountBean") NewAccountBean newAccountBean,
            Model model){
        return "confirm-new-account";
    }

    @GetMapping("company-details")
    public String companyDetails (
            @ModelAttribute(name = "newAccountBean") NewAccountBean newAccountBean,
            Model model) {
        model.addAttribute("newAccountBean", newAccountBean);
        return "company-details";
    }

    @PostMapping("company-details-completed")
    public String companyDetailsCompleted(
            @ModelAttribute NewAccountBean newAccountBean,
            Model model){
        //Check geldigheid KVK-nummer
        //Check geldigheid BTW-nummer
        model.addAttribute("newAccountBean", newAccountBean);
        return "confirm-new-account";
    }

    @PostMapping("account-confirmed")
    public String confirmNewAccountPost(
            @ModelAttribute NewAccountBean newAccountBean,
            @ModelAttribute(AttributeMapping.LOGGED_IN_CUSTOMER) Customer loggedInCustomer,
            Model model){
        newAccountBean.setHolder(loggedInCustomer);
        newAccountService.saveNewAccount(newAccountBean);
        model.addAttribute("newAccountBean", newAccountBean);
        return "index";
    }
}

