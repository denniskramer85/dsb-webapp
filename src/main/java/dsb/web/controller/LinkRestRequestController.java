package dsb.web.controller;

import dsb.web.domain.TokenPaymentMachine;
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
@SessionAttributes(AttributeMapping.LOGGED_IN_EMPLOYEE)
public class LinkRestRequestController {
   private SmeDashboardService smeDashboardService;
   private Logger logger = LoggerFactory.getLogger(LinkRestRequestController.class);

    @Autowired
    public LinkRestRequestController(SmeDashboardService smeDashboardService) {
        this.smeDashboardService = smeDashboardService;
    }


    public LinkRestRequestController() {
    }


    @GetMapping("LinkRequest_overview")
    public String linkRequestsOverview(Model model) {
        List<TokenPaymentMachine> getLinkRequests = smeDashboardService.getAllByLinkRequest();
        model.addAttribute("linkRequest", getLinkRequests);
        return "smeLinkRestRequest";
    }

}
