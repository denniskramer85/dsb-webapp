package dsb.web.controller;

import dsb.web.domain.Account;
import dsb.web.domain.Transaction;
import dsb.web.repository.AccountRepository;
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
    private AccountRepository accountRepository;


    @Autowired
    public AccountPageController(AccountPageService accountPageService, AccountRepository accountRepository) {
        this.accountPageService = accountPageService;
        this.accountRepository = accountRepository;
    }




    @GetMapping("accountPage")
    public String startAccountPage (Model model) {

        //account ophalen als dummy bean
        List <Account> listAccounts = accountRepository.findAll();
        Account dummyAccount = listAccounts.get(0);

        model.addAttribute("accountBean", dummyAccount);

        return "account_page2";
    }

}
