package dsb.web.controller;

import dsb.web.controller.beans.AccountHolderTokenBean;
import dsb.web.domain.*;
import dsb.web.service.AccountOverviewService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
//TODO: Oplosssen zonder account in sessie
@SessionAttributes({AttributeMapping.LOGGED_IN_CUSTOMER, AttributeMapping.SELECTED_ACCOUNT})
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
        // Retrieve list of consumer- and SME-accounts for logged in customer

        // find all addAccountHolderTokens and add to model
        model.addAttribute("accountHolderTokenBeans", accountOverviewService.getAccountHolderTokens(loggedInCustomer) );
        model.addAttribute("NewAccountHolderTokenBean", new AccountHolderTokenBean());

        List<ConsumerAccount> consumerAccountList = accountOverviewService.getConsumerAccountsForCustomer(loggedInCustomer);
        List<SMEAccount> smeAccountList = accountOverviewService.getSMEAccountsForCustomer(loggedInCustomer);
        logger.debug("ConsumerAccounts: " + consumerAccountList.toString());
        logger.debug("SMEAccounts: " + smeAccountList.toString());

        model.addAttribute("consumerAccounts", consumerAccountList);
        model.addAttribute("smeAccounts", smeAccountList);
        return "account_overview";
    }

    @PostMapping("account_overview")
    public String selectAccount(@RequestParam("accountID") int accountID,
                                      @ModelAttribute(AttributeMapping.LOGGED_IN_CUSTOMER) Customer loggedInCustomer,
                                      RedirectAttributes redirectAttributes, Model model) {
        // Get selected account from DB
        Account selectedAccount = accountOverviewService.getAccountByID(accountID);


        // Check whether logged in customer has access to selected account
        /**TODO dit ff uitgezet en gewijzigde versie hieronder:
         * om sessie te vullen en te werken met model**/
        /*if (accountOverviewService.accessPermitted(selectedAccount, loggedInCustomer)) {
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("redirect:/accountPage");

            // Add selected account to session and redirectAttributes
            modelAndView.addObject(AttributeMapping.SELECTED_ACCOUNT, selectedAccount);
            redirectAttributes.addAttribute(AttributeMapping.SELECTED_ACCOUNT, selectedAccount);

            return modelAndView;
        }*/


        //versie sicco nu
        if (accountOverviewService.accessPermitted(selectedAccount, loggedInCustomer)) {
            model.addAttribute(AttributeMapping.SELECTED_ACCOUNT, selectedAccount);
            return "redirect:/accountPage";
        }



        return null; // TODO: Return Access Denied
    }
}
