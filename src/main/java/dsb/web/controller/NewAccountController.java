package dsb.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class NewAccountController {

    @GetMapping("new-account")
    public String newAccountSetup(){
        return "new-account";
    }

    @PostMapping("new-account")
    public String newAccountType(
            @RequestParam(name = "account_type") String accountType,
            Model model){
        System.out.println(accountType);
        //model.addAttribute(accountType);
        return "";
    }
}
