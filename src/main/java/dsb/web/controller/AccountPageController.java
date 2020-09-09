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
@SessionAttributes("selectedAccount")
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
    public String startAccountPage (/*@ModelAttribute("selectedAccount") Account accountX, */Model model) {

        Account account = (Account) model.getAttribute("selectedAccount");
        System.out.println("account is: " + account);
        //TODO hier nog null


        //TODO dit moet beter, leiver echte account en dan per pag de printgegevens maken
        //je hebt hier selected account, heeft miel er al in vorige pagina ingehangen: selectedAccount
        //hier is ACCOUNT dus vanuit een session ebschikbaar, net als nu - even omzetten
        //model.addAttribute("selectedAccountSession", accountPageService.makePrintAccountDataBean(account));

        model.addAttribute("printAccountDataBean", accountPageService.makePrintAccountDataBean(account));


        return "account_page";
    }

}
