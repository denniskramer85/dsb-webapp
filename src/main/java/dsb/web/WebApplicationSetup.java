package dsb.web;

import dsb.web.service.ApplicationStartupService;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class WebApplicationSetup implements ApplicationListener<ApplicationReadyEvent> {
    ApplicationStartupService applicationStartupService;

    public WebApplicationSetup(ApplicationStartupService applicationStartupService) {
        this.applicationStartupService = applicationStartupService;
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        applicationStartupService.setupBalances();
    }
}
