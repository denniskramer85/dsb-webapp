package dsb.web.service;


import dsb.web.controller.beans.AccountPageBean;
import dsb.web.domain.Account;
import dsb.web.domain.Customer;
import dsb.web.domain.Transaction;
import dsb.web.repository.TransactionRepository;
import dsb.web.service.comparators.ComparatorHoldersBySurname;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
public class AccountPageService {

    private TransactionRepository transactionRepository;

    /**max numbe rof holders shown**/
    private int maxNrHoldersShown = 3;

    @Autowired
    public AccountPageService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public AccountPageBean makeAccountPageBean(Account account) {

        /**Achtereenvolgens beangegevens ophalen**/
        String typeAccount = account.printClassName();
        String accountNo = account.getAccountNo();

        //TODO implementeren
        String companyName = "HVA industries BV";
//            if (account instanceof SMEAccount) {
//                String compNameTemp = ((SMEAccount) account).getCompany().getName();
//                System.out.println(compNameTemp);
//                if (compNameTemp != null) {companyName = compNameTemp;}
//            }
//        System.out.println(companyName);

        //TODO hier nog string van maken (detail)
        List<String> holderNames = createListHolderNames(account.getHolders());
        String balance = String.format("%.2f", account.getBalance());
        String currentTime = getCurrentTime();
        //TODO nog limiteren met extra param!!!
        List<Transaction> transactions = transactionRepository.findTransactionByAccounts(account.getAccountID());

        return new AccountPageBean(typeAccount, accountNo, companyName,
                holderNames, balance, currentTime, transactions);
    }


    //TODO VERBETEREN: return een string.format met alle specs rond max, kommas en e.a.
    public List<String> createListHolderNames (List<Customer> listHolders) {

        //TODO test of werkt bij meerdere (sorteren op achternaam)
        Collections.sort(listHolders, new ComparatorHoldersBySurname());

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

        //TODO test of werkt bij meerdere
        if (holderNames.size() > maxNrHoldersShown) {
            holderNames = holderNames.subList(0, (maxNrHoldersShown-1));
            holderNames.add("e.a.");
        }

        return holderNames;
    }

    public String getCurrentTime() {
        //huidige tijd
        System.currentTimeMillis();
        SimpleDateFormat formatter= new SimpleDateFormat("EEEE, dd MMM yyyy '-' HH:mm 'uur'");
        Date timestamp = new Date(System.currentTimeMillis());
        String temp = formatter.format(timestamp);
        return temp.substring(0, 1).toUpperCase() + temp.substring(1);
    }



}
