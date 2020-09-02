package dsb.web.controller;

import dsb.web.controller.beans.NewAccountBean;
import dsb.web.domain.*;
import dsb.web.repository.AccountRepository;
import dsb.web.repository.BussinessRepository;
import dsb.web.service.NewAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@SessionAttributes("loggedInCustomer")
@Controller
public class NewAccountController {
    private NewAccountService newAccountService;
    private AccountRepository accountRepository;
    private BussinessRepository bussinessRepository;

    @Autowired
    public NewAccountController(NewAccountService newAccountService, AccountRepository accountRepository, BussinessRepository bussinessRepository) {
        this.newAccountService = newAccountService;
        this.accountRepository = accountRepository;
        this.bussinessRepository = bussinessRepository;
    }

    @GetMapping("new-account")
    public String newAccountSetup(Model model){
        model.addAttribute("newAccountBean",new NewAccountBean());
        return "new-account";

    }

    @PostMapping("new-account")
    public String newAccountType(
            @ModelAttribute NewAccountBean newAccountBean,
            Model model) {
        System.out.println(newAccountBean);
        model.addAttribute("newAccountBean1", newAccountBean);
        if (newAccountBean.getAccountType() == 0) {
            return "confirm-new-account";
        } else if (newAccountBean.getAccountType() == 1) {
            System.out.println(newAccountBean);
            return "bussiness-details";
        } else {
            return "new-account";
        }
    }

    @GetMapping("bussiness-details")
    public String bussinessDetails (
            Model model) {
        return "bussiness-details";
    }

    @PostMapping("bussiness-details-completed")
    public String bussinessDetailsCompleted(
            @ModelAttribute NewAccountBean newAccountBean,
            @RequestParam(name = "input-name") String name,
            @RequestParam(name = "input-KVKno") String KNKno,
            @RequestParam(name = "input-BTWno") String BTWno,
            Model model){
        newAccountBean.setAccountType(1);
        newAccountBean.setBussiness(new Bussiness(name,KNKno,BTWno));
        model.addAttribute("newAccountBean", newAccountBean);
        return "confirm-new-account";
    }


    @GetMapping("confirm-new-account")
    public String confirmNewAccount(
            @ModelAttribute NewAccountBean newAccountBean,
            Model model){
        return "confirm-new-account";
    }

    @PostMapping("confirm-new-account")
    public String confirmNewAccountPost(){

        return "index";
    }
}
