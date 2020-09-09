package dsb.web.controller;

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
    public String SmeDashboardOverview(Transaction transaction, Model model) {
        List<Transaction> top10Transaction = smeDashboardService.getTop10Transaction(transaction);
    //Model info verstuurt naar je template
    model.addAttribute("naam", "thijs");
    model.addAttribute("transaction", top10Transaction);
    return "sme_employee_dashboard";
}


//In de eerste eventhandler roep je eerst je service klasse op,
    //1 maak klasse smeadashboardservice, in je servicepakket
    //2 Zet deze klasse klasse klaar in je controller via constructor met @autowired, dat wordtd een attribuut in je controller
    //3 zet in je serviceklasse account.repository klaar met @autowired zodat het een attribuut wordt in de service klasse, zodat je in je service klasse kan beschikken over de data
    //4 in de eerste eventhandler in controller een methode aanroepen die in de serviceklasse staat, die methode bouw je in de service klasse,
    //5 methode doet een querie in de repository.


}

