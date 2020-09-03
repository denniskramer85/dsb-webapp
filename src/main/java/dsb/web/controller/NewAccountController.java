package dsb.web.controller;

import dsb.web.controller.beans.LoginBean;
import dsb.web.controller.beans.companyBean;
import dsb.web.domain.*;
import dsb.web.repository.AccountRepository;
import dsb.web.repository.BussinessRepository;
import dsb.web.service.NewAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@SessionAttributes("loggedInCustomer")
@Controller
public class NewAccountController {
    private NewAccountService newAccountService;
    private AccountRepository accountRepository;
    private BussinessRepository bussinessRepository;

    @Autowired
    public NewAccountController(NewAccountService newAccountService, AccountRepository accountRepository, BussinessRepository bussinessRepository) {
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
        model.addAttribute("accountType", accountType);
        if (accountType == 0) { // if Radio 'partiuculier' was selected
            return "confirm-new-account";
        } else if (accountType == 1) { // if Radio 'zakelijk' was selected
            return "company-details";
        }
        return "index";
    }

    @GetMapping("company-details")
    public String companyDetails (Model model) {
        model.addAttribute("company", new Company());
        return "company-details";
    }

    @PostMapping("company-details-completed")
    public String companyDetailsCompleted(
            @ModelAttribute companyBean companyBean,
            @RequestParam(name = "input-name") String name,
            @RequestParam(name = "input-KVKno") String KNKno,
            @RequestParam(name = "input-BTWno") String BTWno,
            Model model){

        //Check geldigheid KVK-nummer
        //Check geldigheid BTW-nummer
        model.addAttribute("loginBean", new LoginBean());
        companyBean.setAccountType(1);
        companyBean.setcompany(new Company(name,KNKno,BTWno));
        model.addAttribute("companyBean", companyBean);
        return "confirm-new-account";
    }


    @GetMapping("confirm-new-account")
    public String confirmNewAccount(
            @ModelAttribute companyBean companyBean,
            Model model){
        return "confirm-new-account";
    }

    @PostMapping("confirm-new-account")
    public String confirmNewAccountPost(){

        return "index";
    }
}
