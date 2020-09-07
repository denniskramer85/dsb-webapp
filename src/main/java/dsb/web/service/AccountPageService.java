package dsb.web.service;


import dsb.web.controller.beans.AccountPageBean;
import dsb.web.domain.Account;
import dsb.web.domain.Customer;
import dsb.web.domain.SMEAccount;
import dsb.web.domain.Transaction;
import dsb.web.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class AccountPageService {

    private TransactionRepository transactionRepository;

    @Autowired
    public AccountPageService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public AccountPageBean makeAccountPageBean(Account account) {

        String typeAccount = account.printClassName();
        String accountNo = account.getAccountNo();

        //TODO: nog via SMEA ophalen, nu hardcoded
        String companyName = "HVA BV";

//        if (account instanceof SMEAccount) {
//            companyName = ((SMEAccount) account).getCompany().getName();
//        }




        List<String> holderNames = createListHolderNames(account.getHolders());
        String balance = String.format("%.2f", account.getBalance());
        String currentTime = getCurrentTime();
        //TODO nog limiteren met extra param!!!
        List<Transaction> transactions = transactionRepository.findTransactionByAccounts(account.getAccountID());

        return new AccountPageBean(typeAccount, accountNo, companyName,
                holderNames, balance, currentTime, transactions);
    }


    //TODO nog max en volgorde?
    public List<String> createListHolderNames (List<Customer> listHolders) {
        List<String> holderNames = new ArrayList<>();
        String inits, inserts, surname, result;
        for (Customer c : listHolders) {
            inits = c.getInitials();
            inserts = c.getInserts() + " ";
            if (c.getInserts() == null) inserts = "";
            surname = c.getSurname();

            result = String.format("%s %s%s", inits, inserts, surname);
            holderNames.add(result);
        }
        return holderNames;
    }

    public String getCurrentTime() {
        //huidige tijd
        System.currentTimeMillis();
        SimpleDateFormat formatter= new SimpleDateFormat("EEEE, dd MMMM yyyy '-' HH:mm 'uur'");
        Date timestamp = new Date(System.currentTimeMillis());
        String temp = formatter.format(timestamp);
        return temp.substring(0, 1).toUpperCase() + temp.substring(1);
    }



}
