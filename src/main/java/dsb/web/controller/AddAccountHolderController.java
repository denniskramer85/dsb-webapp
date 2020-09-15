package dsb.web.controller;

import dsb.web.controller.beans.ConfirmBean;
import dsb.web.controller.beans.LoginBean;
import dsb.web.controller.beans.PrintAccountDataBean;
import dsb.web.domain.Account;
import dsb.web.domain.AccountHolderToken;
import dsb.web.domain.Company;
import dsb.web.domain.Customer;
import dsb.web.repository.AccountHolderTokenRepository;
import dsb.web.repository.CustomerRepository;
import dsb.web.service.AddAccountHolderService;
import dsb.web.service.SignInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.w3c.dom.Attr;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;


@Controller
@SessionAttributes({AttributeMapping.LOGGED_IN_CUSTOMER, AttributeMapping.SELECTED_ACCOUNT})
public class AddAccountHolderController {

    @Autowired
    private SignInService signInService;
    private CustomerRepository customerRepository;
    private AddAccountHolderService addAccountHolderService;

    public AddAccountHolderController(SignInService signInService, CustomerRepository customerRepository, AddAccountHolderService addAccountHolderService) {
        this.addAccountHolderService = addAccountHolderService;
        this.signInService = signInService;
        this.customerRepository = customerRepository;
    }

    @GetMapping("add-account-holder")
    ModelAndView addAccountHolderHandler(Model model){
        ModelAndView mav = new ModelAndView("add-account-holder");
        Account account = (Account) model.getAttribute(AttributeMapping.SELECTED_ACCOUNT);
        mav.addObject("accountNo", account.getAccountNo());
        mav.addObject("loginBean",new LoginBean());
        return mav;
    }

    @PostMapping("confirm-add-account-holder")
    Object addAccountHolderVerify(
            @ModelAttribute LoginBean loginBean,
            @ModelAttribute(AttributeMapping.LOGGED_IN_CUSTOMER) Customer loggedInCustomer,
            @ModelAttribute(AttributeMapping.SELECTED_ACCOUNT) Account account,
            Model model){
        Optional<Customer> newAccountHolder = customerRepository.findOneByUsername(loginBean.getUsername());
        if (!newAccountHolder.isPresent()) {
            loginBean.setPassword(null);
            model.addAttribute("loginBean", loginBean);
            return new ModelAndView("redirect:/add-account-holder");
        } else {
            ConfirmBean confirmBean = new ConfirmBean(
                    "Aanvraag nieuwe rekeninghouder geslaagd",
                    new ArrayList<String>(Arrays.asList("Je aanvraag om een nieuwe rekeninghouder toe te voegen is geslaagd. Klik op volgende om naar de rekeningpagina terug te keren")),
                    "account_overview");
            model.addAttribute("confirmBean", confirmBean);
            int tokenCode = 00000;
            addAccountHolderService.createAddAccountHolderToken(loggedInCustomer,account,tokenCode);
            return "confirm";
        }
    }

    @GetMapping("confirm")
    String addAccountHolderConfirm(
            @ModelAttribute ConfirmBean confirmBean,
            Model model){
        System.out.println(confirmBean);
        model.addAttribute("confirmBean", confirmBean);
        return "confirm";
    }

    /*resolve tokens:*/

    @PostMapping("resolve-account-holder-token")
    String resolve(@ModelAttribute("tokenId") String tokenId,
                   Model model){
        System.out.println(tokenId);
        return "resolve-account-holder-token";
    }


    @PostMapping("resolve-account-holder-token-verify")
    String compareTokens(@RequestParam(name = "tokenCode") int tokenCode,
                         @ModelAttribute("accountHolderTokens") AccountHolderToken accountHolderToken,
                         Model model){

        System.out.println(accountHolderToken);
        return "";
    }


    @RestController
    @RequestMapping(value = "/find_users")
    class FindUserController{

        public FindUserController() {
            super();
        }

        @GetMapping(value = "/{username}")
        public String findUser(@PathVariable("username") String username){
            Optional<Customer> customerOptional = customerRepository.findOneByUsername(username);
            if (customerOptional.isPresent()) {
                return customerOptional.get().getUsername();
            }
            return null;
        }
    }

}
