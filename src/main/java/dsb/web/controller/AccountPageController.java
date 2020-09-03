package dsb.web.controller;

import dsb.web.domain.Transaction;
import dsb.web.service.AccountPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
//TODO sessions / wat komt er door?
public class AccountPageController {

    private AccountPageService accountPageService;

    @Autowired
    public AccountPageController(AccountPageService accountPageService) {
        this.accountPageService = accountPageService;
    }

    @GetMapping("accountPage")
    public String startAccountPage (Model model) {

        System.out.println("doetet");

        //TODO welke gegevens komen door uit de overzichtspagina?
        //model.addAttribute(x, x);
        //return templatenaam

        return "account_page.html";
    }

}
