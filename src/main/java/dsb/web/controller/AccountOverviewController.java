package dsb.web.controller;

import dsb.web.domain.ConsumerAccount;
import dsb.web.domain.Customer;
import dsb.web.service.AccountOverviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.List;

@Controller
@SessionAttributes("loggedInCustomer")
public class AccountOverviewController {
    private AccountOverviewService accountOverviewService;

    @Autowired
    public AccountOverviewController(AccountOverviewService accountOverviewService) {
        this.accountOverviewService = accountOverviewService;
    }

    public AccountOverviewController() {
    }

    @GetMapping("account_overview")
    public String accountOverview(@ModelAttribute("loggedinCustomer") Customer loggedInCustomer, Model model) {
        List<ConsumerAccount> consumerAccountList = accountOverviewService.getConsumerAccountsForCustomer(loggedInCustomer);
        model.addAttribute(consumerAccountList);
        System.out.println(consumerAccountList);
        return "account_overview";
    }
}
