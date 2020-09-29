package dsb.web;

import dsb.web.controller.AccountOverviewController;
import dsb.web.service.ApplicationStartupService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class WebApplicationSetup implements ApplicationListener<ApplicationReadyEvent> {
    ApplicationStartupService applicationStartupService;
    private Logger logger = LoggerFactory.getLogger(AccountOverviewController.class);

    public WebApplicationSetup(ApplicationStartupService applicationStartupService) {
        this.applicationStartupService = applicationStartupService;
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        // TODO: Turn on to load dummy data in database; will take approx. 40 minutes
       /*try {
            applicationStartupService.createCustomers();
            applicationStartupService.createCompanies();
            applicationStartupService.TransactionGenerator(5000000);
        } catch (IOException e) {
            logger.debug(e.toString());
        }*/

        applicationStartupService.setupBalances();
    }
}
