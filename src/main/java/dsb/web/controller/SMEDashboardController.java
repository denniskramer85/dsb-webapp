package dsb.web.controller;

import dsb.web.domain.Customer;
import dsb.web.domain.SMEAccount;
import dsb.web.domain.Transaction;
import dsb.web.service.SmeDashboardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.List;


@Controller
@SessionAttributes(AttributeMapping.LOGGED_IN_CUSTOMER)
public class SMEDashboardController {
    private SmeDashboardService smeDashboardService;
    private Logger logger = LoggerFactory.getLogger(SMEDashboardController.class);

    @Autowired
    public SMEDashboardController(SmeDashboardService smeDashboardService) {
        this.smeDashboardService = smeDashboardService;
    }

    public SMEDashboardController() {
    }

    @GetMapping("SME_dashboard")
    public String SmeDashboardOverview(Model model) {

        List<Transaction> top10Transaction = smeDashboardService.getTop10SmeTransaction();
        List<SMEAccount> top10Balance = smeDashboardService.getTop10bySmeBalance();
        //Model info verstuurt naar je template
        model.addAttribute("naam", "thijs");
        model.addAttribute("balances", top10Balance);
        model.addAttribute("transaction", top10Transaction);

        return "sme_employee_dashboard";
    }

}

