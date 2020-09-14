package dsb.web.controller;

import dsb.web.domain.ConsumerAccount;
import dsb.web.domain.Employee;
import dsb.web.service.ConsumerDashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@SessionAttributes(AttributeMapping.LOGGED_IN_EMPLOYEE)
public class ConsumerDashboardController {

    private ConsumerDashboardService consumerDashboardService;

    @Autowired
    public ConsumerDashboardController(ConsumerDashboardService consumerDashboardService) {
        super();
        this.consumerDashboardService = consumerDashboardService;

    }

    //afmaken m.b.v. de medewerker sign-in van Dennis (Session meegeven).
    @GetMapping("employee_consumer_dashboard")
    public String consumerDashboard(Employee employee, Model model) {
        model.addAttribute("selectedEmployee", employee);
        List<ConsumerAccount> top10lijst = consumerDashboardService.findHighestAccounts();
        model.addAttribute("consumerAccounts1", top10lijst);
        return "employee_consumer_dashboard";
    }


    }


