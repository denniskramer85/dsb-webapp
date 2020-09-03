package dsb.web.controller;

import dsb.web.service.AccountPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
        //TODO welke gegevens komen door uit de overzichtspagina?
        //model.addAttribute(x, x);
        //return templatenaam
        return null;
    }

}
