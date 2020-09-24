package dsb.web.controller;

import dsb.web.domain.ConsumerAccount;
import dsb.web.domain.Customer;
import dsb.web.domain.Employee;
import dsb.web.repository.ConsumerAccountRepository;
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
    private ConsumerAccountRepository consumerAccountRepository;

    @Autowired
    public ConsumerDashboardController(ConsumerDashboardService consumerDashboardService, ConsumerAccountRepository consumerAccountRepository) {
        super();
        this.consumerDashboardService = consumerDashboardService;
        this.consumerAccountRepository = consumerAccountRepository;

    }


    @GetMapping("employee_consumer_dashboard")
    public String consumerDashboard(@ModelAttribute(AttributeMapping.LOGGED_IN_EMPLOYEE) Employee employee, Model model) {
        model.addAttribute("selectedEmployee", employee.printWholeName());


        List<ConsumerAccount> top10lijst = consumerDashboardService.getTop10byConsumerBalance();
        model.addAttribute("consumerAccounts1", top10lijst);
        return "employee_consumer_dashboard";
    }


    }


