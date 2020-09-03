package dsb.web.controller;

import dsb.web.domain.Account;
import dsb.web.domain.ConsumerAccount;
import dsb.web.domain.Customer;
import dsb.web.domain.SMEAccount;
import dsb.web.service.AccountOverviewService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@SessionAttributes(AttributeMapping.LOGGED_IN_CUSTOMER)
public class AccountOverviewController {
    private AccountOverviewService accountOverviewService;
    private Logger logger = LoggerFactory.getLogger(AccountOverviewController.class);

    @Autowired
    public AccountOverviewController(AccountOverviewService accountOverviewService) {
        this.accountOverviewService = accountOverviewService;
    }

    public AccountOverviewController() {
    }

    @GetMapping("account_overview")
    public String accountOverview(@ModelAttribute(AttributeMapping.LOGGED_IN_CUSTOMER) Customer loggedInCustomer, Model model) {
        List<ConsumerAccount> consumerAccountList = accountOverviewService.getConsumerAccountsForCustomer(loggedInCustomer);
        List<SMEAccount> smeAccountList = accountOverviewService.getSMEAccountsForCustomer(loggedInCustomer);
        logger.debug("ConsumerAccounts: " + consumerAccountList.toString());
        logger.debug("SMEAccounts: " + smeAccountList.toString());
        model.addAttribute("consumerAccounts", consumerAccountList);
        model.addAttribute("smeAccounts", smeAccountList);
        return "account_overview";
    }

    @PostMapping("account_overview")
    public ModelAndView selectAccount(@RequestParam("accountID") int accountID, Model model) {
        Account selectedAccount = accountOverviewService.getAccountByID(accountID);
        model.addAttribute(selectedAccount);
        return new ModelAndView("redirect:/accountPage");
    }
}
