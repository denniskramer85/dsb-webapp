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
            return "redirect:/company-details";
        }
        return "index";
    }



    @GetMapping("confirm-new-account")
    public String confirmNewAccount(
            @ModelAttribute(AttributeMapping.COMPANY_BEAN) CompanyBean companyBean){
        return "confirm-new-account";
    }

    @GetMapping("company-details")
    public String companyDetails (
            @ModelAttribute(AttributeMapping.COMPANY_BEAN) CompanyBean companyBean,
            Model model) {
        model.addAttribute("sectors", sectorRepository.findAll());
        return "company-details";
    }

    @PostMapping("company-details")
    public String companyDetails1 (
            @ModelAttribute(AttributeMapping.COMPANY_BEAN) CompanyBean companyBean,
            Model model) {
        return "company-details";
    }

    @PostMapping("company-details-completed")
    public String companyDetailsCompleted(@Valid @ModelAttribute(AttributeMapping.COMPANY_BEAN) CompanyBean companyBean,
                                          @ModelAttribute(AttributeMapping.LOGGED_IN_CUSTOMER) Customer loggedInCustomer,
                                          Errors errors,
                                          Model model){
        if (errors.hasErrors()){
            model.addAttribute("companyBean", companyBean);
            model.addAttribute("sectors", sectorRepository.findAll());
            return "company-details";
        }
        //check if kvk exists
        String kvkNo = companyBean.getKVKno();
        if(companyRepository.existsByKVKno(kvkNo)){
            //check of user gemachtigd is om rekening aan te maken
            List<Company> companies = new ArrayList<>();
            for (Account account : loggedInCustomer.getAccounts()){
                if (account instanceof SMEAccount){
                    companies.add(((SMEAccount) account).getCompany());
                }
            }
        }
        return "confirm-new-account";
    }

    @PostMapping("account-confirmed")
    public String confirmNewAccountPost(
            @ModelAttribute(AttributeMapping.COMPANY_BEAN) CompanyBean companyBean,
            @ModelAttribute(AttributeMapping.LOGGED_IN_CUSTOMER) Customer loggedInCustomer,
            Model model){
        companyBean.setCurrentCustomer(loggedInCustomer);
        Account account = newAccountService.saveNewAccount(companyBean);
        model.addAttribute(AttributeMapping.SELECTED_ACCOUNT, account);
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


