package dsb.web.controller;

import dsb.web.domain.*;
import dsb.web.repository.AccountRepository;
import dsb.web.service.AccountPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
//TODO sessions / wat komt er door?
public class AccountPageController {

    private AccountPageService accountPageService;
    private AccountRepository accountRepository;


    @Autowired
    public AccountPageController(AccountPageService accountPageService, AccountRepository accountRepository) {
        this.accountPageService = accountPageService;
        this.accountRepository = accountRepository;
    }




    @GetMapping("accountPage")
    public String startAccountPage (Model model) {

        //account ophalen als dummy bean
        List <Account> listAccounts = accountRepository.findAll();
        Account account = listAccounts.get(0);
//        Account account2 = listAccounts.get(4);


        System.out.println(account.printClassName());
  //      System.out.println(account2.printClassName());

        //actereenv: namenlijst, bedrnaam, transax

        //bean-vulling uit rekening: type , reknr, saldo
        String typeAccount = account.printClassName();
        String accountNo = account.getAccountNo();
        double balance = account.getBalance();



        //indirect uit klant


        //huidige tijd
        System.currentTimeMillis();
        SimpleDateFormat formatter= new SimpleDateFormat("EEE, dd-MM-yyyy '-' HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        System.out.println(formatter.format(date));




        //transax


/*

        int type;






        if (account instanceof ConsumerAccount) System.out.println("eerste is consumer");
        //if (dummyAccount2 instanceof SMEAccount) System.out.println("tweede is mkb");

        System.out.println("requested dummy-account: " + account);




        //bijbehorende klanten ophalen
        List<Customer> listCustomers = account.getHolders();

        for (Customer c : listCustomers) System.out.println(c);


//        //model.addAttribute("accountBean", account);


*/


        return "account_page2";
    }

}
