package dsb.web.service;


import dsb.web.controller.beans.AccountPageBean;
import dsb.web.domain.Account;
import dsb.web.domain.Customer;
import dsb.web.domain.Transaction;
import dsb.web.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class AccountPageService {

    private TransactionRepository transactionRepository;

    /**max number of holders shown**/
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

        String holderNames = createListHolderNames(account.getHolders());





        String balance = String.format("%.2f", account.getBalance());
        String currentTime = getCurrentTime();
        //TODO nog limiteren met extra param!!!
        List<Transaction> transactions = transactionRepository.findTransactionByAccounts(account.getAccountID());

        return new AccountPageBean(typeAccount, accountNo, companyName,
                holderNames, balance, currentTime, transactions);
    }


    public String createListHolderNames (List<Customer> listHolders) {

//        //ff dubbylijst maken
//        Customer c1 = new Customer();
//        c1.setSurname("a");
//        Customer c2 = new Customer();
//        c2.setSurname("b");
//        Customer c3 = new Customer();
//        c3.setSurname("c");
//        Customer c4 = new Customer();
//        c4.setSurname("d");
//        Customer[] list = {c3,c1};
//        List<Customer> listHolders = Arrays.asList(list);

        //sort by surname
        Collections.sort(listHolders);

        //create unified name strings
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

        StringBuilder sb = new StringBuilder();

        //uitloop voorkomen
        int maxLoop = maxNrHoldersShown;
        if (holderNames.size() < maxNrHoldersShown) {
             maxLoop = holderNames.size();
        }

        for (int i = 0; i < maxLoop ; i++) {
            sb.append(holderNames.get(i)).append(", ");
        }

        //laaste komma weg
        String finalString = sb.toString();
        finalString = finalString.substring(0, finalString.length() - 2);

        if (holderNames.size() > maxNrHoldersShown)
            finalString = finalString + " e.a.";

        return finalString;
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
