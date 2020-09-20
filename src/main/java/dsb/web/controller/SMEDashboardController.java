package dsb.web.controller;

import dsb.web.domain.*;

import dsb.web.repository.SMEAccountRepository;
import dsb.web.repository.SectorRepository;
import dsb.web.service.RequestPaymentMachineService;
import dsb.web.service.SmeDashboardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.List;
import java.util.Map;


@Controller
@SessionAttributes(AttributeMapping.LOGGED_IN_CUSTOMER)
public class SMEDashboardController {
    private SmeDashboardService smeDashboardService;
    private SMEAccountRepository smeAccountRepository;
    private SectorRepository sectorRepository;
    private RequestPaymentMachineService requestPaymentMachineService;
    private Logger logger = LoggerFactory.getLogger(SMEDashboardController.class);

    @Autowired
    public SMEDashboardController(SmeDashboardService smeDashboardService, SMEAccountRepository smeAccountRepository,SectorRepository sectorRepository) {
        this.smeAccountRepository = smeAccountRepository;
        this.smeDashboardService = smeDashboardService;
        this.sectorRepository = sectorRepository;
    }


    public SMEDashboardController() {
    }

    @GetMapping("SME_dashboard")
    public String smeDashboardOverview(Model model) {

        Map<SMEAccount, Integer> top10Transaction = smeDashboardService.getTop10SmeTransaction();
        for (Map.Entry<SMEAccount, Integer> entry : top10Transaction.entrySet()) {
            System.out.println("Rekeningnummer: " + entry.getKey().getAccountNo());
            System.out.println("Aantal: " + entry.getValue());
        }

        List<TokenPaymentMachine> getAllLinkRequests = smeDashboardService.getAllByLinkRequest();
        List<SMEAccount> top10Balance = smeDashboardService.getTop10bySmeBalance();
        Map<Sector, Integer> averageTop10BySector = smeDashboardService.averageTop10BySector();
        model.addAttribute("naam", "Naam medewerker");
        model.addAttribute("linkRequestList", getAllLinkRequests);
        model.addAttribute("transactionsList", top10Transaction);
        model.addAttribute("balances", top10Balance);
        model.addAttribute("averageBalanceBySector", averageTop10BySector);
        return "sme_employee_dashboard";
    }
}

