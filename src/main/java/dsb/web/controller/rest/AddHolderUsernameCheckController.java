package dsb.web.controller.rest;

import dsb.web.controller.AttributeMapping;
import dsb.web.domain.Account;
import dsb.web.domain.Customer;
import dsb.web.service.AddAccountHolderService;
import org.springframework.web.bind.annotation.*;

@SessionAttributes({AttributeMapping.LOGGED_IN_CUSTOMER, AttributeMapping.SELECTED_ACCOUNT})
@RestController
@RequestMapping(value = "/find_users")
public class AddHolderUsernameCheckController{

    private AddAccountHolderService addAccountHolderService;

    public AddHolderUsernameCheckController(AddAccountHolderService addAccountHolderService) {
        super();
        this.addAccountHolderService = addAccountHolderService;
    }

    @GetMapping(value = "/{usernameCheck}")
    public String findUser(@PathVariable("usernameCheck") String usernameCheck,
                           @ModelAttribute(AttributeMapping.LOGGED_IN_CUSTOMER) Customer loggedInCustomer,
                           @ModelAttribute(AttributeMapping.SELECTED_ACCOUNT) Account account){
        return addAccountHolderService.checkUsernameValidity(usernameCheck, loggedInCustomer, account);
    }
}