package dsb.web.controller;

import dsb.web.domain.Account;
import dsb.web.service.AccountPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("selectedAccountSession")
public class AccountPageController {

    private AccountPageService accountPageService;


    @Autowired
    public AccountPageController(AccountPageService accountPageService) {
        this.accountPageService = accountPageService;
    }


    @GetMapping("accountPage")
    public String startAccountPage (Model model) {

        Account account = (Account) model.getAttribute("selectedAccountSession");

        //make bean for printing account data
        model.addAttribute("printAccountDataBean", accountPageService.makePrintAccountDataBean(account));

        return "account_page";
    }
}
