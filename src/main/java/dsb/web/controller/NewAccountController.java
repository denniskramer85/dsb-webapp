package dsb.web.controller;

import dsb.web.controller.beans.CompanyBean;
import dsb.web.controller.beans.ConfirmBean;
import dsb.web.domain.*;
import dsb.web.repository.AccountRepository;
import dsb.web.repository.CompanyRepository;
import dsb.web.repository.CustomerRepository;
import dsb.web.repository.SectorRepository;
import dsb.web.service.AccountOverviewService;
import dsb.web.service.NewAccountService;
import org.hibernate.validator.constraints.SafeHtml;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;


@Controller
@SessionAttributes({AttributeMapping.COMPANY_BEAN, AttributeMapping.LOGGED_IN_CUSTOMER,AttributeMapping.SELECTED_ACCOUNT})

public class NewAccountController {

    @Autowired
    private NewAccountService newAccountService;
    private CompanyRepository companyRepository;
    private SectorRepository sectorRepository;
    private AccountOverviewService accountOverviewService;

    public NewAccountController(NewAccountService newAccountService, CompanyRepository companyRepository, SectorRepository sectorRepository, AccountOverviewService accountOverviewService) {
        this.newAccountService = newAccountService;
        this.companyRepository = companyRepository;
        this.sectorRepository = sectorRepository;
        this.accountOverviewService = accountOverviewService;
    }


    @GetMapping("new-account")
    public String newAccountSetup(){
        return "new-account";
    }

    @PostMapping("new-account")
    public String newAccountType(
            @RequestParam(name = "accountType") int accountType,
            @ModelAttribute(AttributeMapping.LOGGED_IN_CUSTOMER) Customer loggedInCustomer,
            Model model) {
        CompanyBean cb = new CompanyBean();
        cb.setCurrentCustomer(loggedInCustomer);
        model.addAttribute(AttributeMapping.COMPANY_BEAN, cb);
        if (accountType == 0) {                         // if Radio 'partiuculier' was selected
            return "confirm-new-account";
        } else if (accountType == 1) {                  // if Radio 'zakelijk' was selected
            return "redirect:company-details";
        }
        return "index";
    }

    @GetMapping("company-details")
    public String companyDetailsHandler (
            Model model) {
        model.addAttribute("sectors", sectorRepository.findAll());
        return "company-details";
    }

    @PostMapping("company-details-completed")
    public ModelAndView companyDetailsCompleted(@Valid @ModelAttribute(AttributeMapping.COMPANY_BEAN) CompanyBean cb,
                                          Errors errors,
                                          ModelMap model){
        model.addAttribute("companyBean", cb);
        if (errors.hasErrors()){
            model.addAttribute("sectors", sectorRepository.findAll());
            return new ModelAndView( "company-details", model);
        }
        return newAccountService.companyExists(cb,model);
    }

    @GetMapping("confirm-new-account")
    public String confirmNewAccount(
            @ModelAttribute("company") Company company,
            @ModelAttribute(AttributeMapping.COMPANY_BEAN) CompanyBean companyBean,
            Model model){
        return "confirm-new-account";
    }

    @PostMapping("account-confirmed")
    public String confirmNewAccountPost(
            @ModelAttribute(AttributeMapping.COMPANY_BEAN) CompanyBean companyBean,
            Model model){
        Account account = newAccountService.createAndSaveNewAccountFromBean(companyBean);
        model.addAttribute(AttributeMapping.SELECTED_ACCOUNT, account);
        model.addAttribute("confirmBean", new ConfirmBean("Nieuwe rekening aangevraagd", "Gefeliciteerd, je nieuwe rekening is aangevraagd en is vanaf nu te vinden in je rekening overzicht. Vanaf nu ECHT veilig bankieren bij DSB!","accountPage", "Naar rekening"));
        return "confirm";
    }
}


