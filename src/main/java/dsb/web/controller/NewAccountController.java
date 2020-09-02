package dsb.web.controller;

import dsb.web.domain.Account;
import dsb.web.domain.ConsumerAccount;
import dsb.web.domain.Customer;
import dsb.web.domain.SMEAccount;
import dsb.web.service.NewAccountService;
import dsb.web.service.SignupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SessionAttributes("loggedInCustomer")
@Controller
public class NewAccountController {
    private NewAccountService newAccountService;

    @Autowired
    public NewAccountController(NewAccountService newAccountService) {
        this.newAccountService = newAccountService;
    }

    @GetMapping("new-account")
    public String newAccountSetup(Model model){
        model.addAttribute("types",newAccountService.ACCOUNT_TYPES);
        return "new-account";

    }

    @PostMapping("new-account")
    public String newAccountType(
            @RequestParam(name = "account_type") int accountType,
            Model model) {
        System.out.println(accountType);
/*        if (accountType == 0){
        }
        //if choice == 1 == SME account
        else if (accountType == 1){
        }
        else {
            return "new-account";
        }*/
        return "";
    }

    @GetMapping("confirm-new-account")
    public String confirmNewAccount(
            @RequestParam Account account,
            Model model){
        return "confirm-new-account";
    }

}
