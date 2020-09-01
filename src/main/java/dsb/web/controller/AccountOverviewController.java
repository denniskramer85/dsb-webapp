package dsb.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AccountOverviewController {

    @Autowired
    public AccountOverviewController() {
    }

    @GetMapping("account_overview")
    public String accountOverview() {
        return "account_overview";
    }
}
