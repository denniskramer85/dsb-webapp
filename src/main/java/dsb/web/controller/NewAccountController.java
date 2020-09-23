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

    public CompanyBean newAccountBean(){
        return new CompanyBean();
    }

    @Autowired


    @GetMapping("new-account")
    public String newAccountSetup(){
        return "new-account";
    }

    @PostMapping("new-account")
    public String newAccountType(
            @RequestParam(name = "accountType") int accountType,
            Model model) {
        model.addAttribute(AttributeMapping.COMPANY_BEAN, new CompanyBean());
        if (accountType == 0) {                         // if Radio 'partiuculier' was selected
            return "confirm-new-account";
        } else if (accountType == 1) {                  // if Radio 'zakelijk' was selected
            model.addAttribute("companyError",null);
            return "redirect:/company-details";
        }
        return "index";
    }

    @GetMapping("company-details")
    public String companyDetailsHandler (
            @ModelAttribute(AttributeMapping.COMPANY_BEAN) CompanyBean companyBean,
            @ModelAttribute("companyError") String error,
            Model model) {
        model.addAttribute("sectors", sectorRepository.findAll());
        return "company-details";
    }

    @PostMapping("company-details-completed")
    public ModelAndView companyDetailsCompleted(@Valid @ModelAttribute(AttributeMapping.COMPANY_BEAN) CompanyBean cb,
                                          Errors errors,
                                          ModelMap model){
        if (errors.hasErrors()){
            model.addAttribute("companyBean", cb);
            model.addAttribute("companyError", null);
            return new ModelAndView( "company-details");
        }
        if (cb.getKVKno() == null){                                                                                     // if kvkNo is blank: consumerAccount
            model.addAttribute("company", null);
            return new ModelAndView(  "confirm-new-account");
        }
        Company company = companyRepository.findCompanyByKVKno(cb.getKVKno());
        if (company != null) {                                                                                          // if kvkNo occurs in DB:
            if (newAccountService.isCustomerAuthorizedForCompany(cb.getKVKno(), cb.getCurrentCustomer())) {                   // if customer is authorized for account creation on behalf of Company
                model.addAttribute("company", company);
                return new ModelAndView(  "confirm-new-account");
            } else {
                model.addAttribute("companyBean", cb);
                model.addAttribute("companyError", "Je bent helaas niet bevoegd om een rekening aan te maken voor dit bedrijf");
                return new ModelAndView(  "redirect:/company-details", model);
            }
        } else {                                                                                                        // if kvkNo doesnt occur in DB
            model.addAttribute("company", newAccountService.createAndSaveCompanyFromBean(cb));
            return new ModelAndView(  "confirm-new-account");
        }
    }

    @GetMapping("confirm-new-account")
    public String confirmNewAccount(
            @ModelAttribute("company") Company company,
            Model model){
        return "confirm-new-account";
    }

    @PostMapping("account-confirmed")
    public String confirmNewAccountPost(
            @ModelAttribute(AttributeMapping.COMPANY_BEAN) CompanyBean companyBean,
            @ModelAttribute(AttributeMapping.LOGGED_IN_CUSTOMER) Customer loggedInCustomer,
            Model model){
        companyBean.setCurrentCustomer(loggedInCustomer);
        //Account account = newAccountService.saveNewAccount(companyBean);
        //model.addAttribute(AttributeMapping.SELECTED_ACCOUNT, account);
        model.addAttribute("confirmBean", new ConfirmBean("Nieuwe rekening aangevraagd", "Gefeliciteerd, je nieuwe rekening is aangevraagd en is vanaf nu te vinden in je rekening overzicht. Vanaf nu ECHT veilig bankieren bij DSB!","accountPage", "Naar rekening"));
        return "confirm";
    }

    @RestController
    @RequestMapping(value = "/kvks")
    class BussinessDetailsController{
        public BussinessDetailsController() {
            super();
        }

        @GetMapping(value = "/{kvk}")
        public String findCompanyByKVK(@PathVariable("kvk") String kvkno){
            Company comp = companyRepository.findCompanyByKVKno(kvkno);
            if (comp != null) {
                System.out.println(comp);
                return (comp.getName() + "," + comp.getBTWno() + "," + comp.getSector().getSectorId());
            }
            return null;
        }
    }
}


