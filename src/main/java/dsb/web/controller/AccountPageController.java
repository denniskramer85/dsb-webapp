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



    @GetMapping("accountPage")
    public String startAccountPage (Model model) {
        Account account = (Account) model.getAttribute("selectedAccountSession");
        System.out.println(account);
        //make bean for printing account data
        model.addAttribute("printAccountDataBean", accountPageService.makePrintAccountDataBean(account));
        return "account_page";
    }

}
