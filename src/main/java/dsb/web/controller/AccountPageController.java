package dsb.web.controller;

import dsb.web.domain.*;
import dsb.web.repository.AccountRepository;
import dsb.web.service.AccountPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("selectedAccountSession")
public class AccountPageController {

    private AccountPageService accountPageService;
    private AccountRepository accountRepository;


    @Autowired
    public AccountPageController(AccountPageService accountPageService, AccountRepository accountRepository) {
        this.accountPageService = accountPageService;
        this.accountRepository = accountRepository;
    }


    //TODO oplossen met miel
    @GetMapping("accountPage")
    public String startAccountPage (@ModelAttribute("selectedAccount") Account account, Model model) {

        //get selected Account from flash/redirect
        System.out.println("account is: " + account);

        //put account in session (for all later use)
        model.addAttribute("selectedAccountSession", account);

        //make bean voor printing account data
        model.addAttribute("printAccountDataBean", accountPageService.makePrintAccountDataBean(account));

        return "account_page";
    }

}
