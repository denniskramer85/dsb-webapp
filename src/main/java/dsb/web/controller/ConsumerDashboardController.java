package dsb.web.controller;

import dsb.web.domain.ConsumerAccount;
import dsb.web.service.ConsumerDashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller

public class ConsumerDashboardController {

    private ConsumerDashboardService consumerDashboardService;

    @Autowired
    public ConsumerDashboardController(ConsumerDashboardService consumerDashboardService) {
        super();
        this.consumerDashboardService = consumerDashboardService;

    }

    @GetMapping("employee_consumer_dashboard")
    public String consumerDashboard(Model model) {
        List<ConsumerAccount> top10lijst = consumerDashboardService.findHighestAccounts();
        System.out.println(top10lijst);
        model.addAttribute("consumerAccounts", top10lijst);
        return "employee_consumer_dashboard";
    }


    }


