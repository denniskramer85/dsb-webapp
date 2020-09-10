package dsb.web.controller;

import dsb.web.controller.beans.CompanyBean;
import dsb.web.domain.Company;
import dsb.web.domain.Customer;
import dsb.web.repository.AccountRepository;
import dsb.web.repository.CompanyRepository;
import dsb.web.repository.CustomerRepository;
import dsb.web.service.NewAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


@Controller
@SessionAttributes({AttributeMapping.COMPANY_BEAN, AttributeMapping.LOGGED_IN_CUSTOMER})
public class NewAccountController {
    private NewAccountService newAccountService;
    private AccountRepository accountRepository;
    private CompanyRepository companyRepository;
    private CustomerRepository customerRepository;

    public CompanyBean newAccountBean(){
        return new CompanyBean();
    }

    @Autowired
    public NewAccountController(NewAccountService newAccountService, AccountRepository accountRepository, CompanyRepository companyRepository) {
        this.newAccountService = newAccountService;
        this.accountRepository = accountRepository;
        this.companyRepository = companyRepository;
    }

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
            return "company-details";
        }
        return "index";
    }



    @GetMapping("confirm-new-account")
    public String confirmNewAccount(
            @ModelAttribute(AttributeMapping.COMPANY_BEAN) CompanyBean companyBean,
            Model model){
        return "confirm-new-account";
    }

    @GetMapping("company-details")
    public String companyDetails (
            @ModelAttribute(AttributeMapping.COMPANY_BEAN) CompanyBean companyBean,
            Model model) {
        return "company-details";
    }

    @PostMapping("company-details-completed")
    public String companyDetailsCompleted(@ModelAttribute(AttributeMapping.COMPANY_BEAN) CompanyBean companyBean,
            Model model){
        //Check geldigheid KVK-nummer
        //companyBean.getKVKno()
        //Check geldigheid BTW-nummer
        return "confirm-new-account";
    }

    @PostMapping("account-confirmed")
    public ModelAndView confirmNewAccountPost(
            @ModelAttribute(AttributeMapping.COMPANY_BEAN) CompanyBean companyBean,
            @ModelAttribute(AttributeMapping.LOGGED_IN_CUSTOMER) Customer loggedInCustomer,
            Model model){
        System.out.println("1: " + loggedInCustomer);
        companyBean.setCurrentCustomer(loggedInCustomer);

        System.out.println("2: "+companyBean);
        newAccountService.saveNewAccount(companyBean);
        model.addAttribute("companyBean", companyBean);
        return new ModelAndView("redirect:/account_overview");
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
                return (comp.getName() + "," + comp.getBTWno());
            }
            return null;
        }
    }

}


