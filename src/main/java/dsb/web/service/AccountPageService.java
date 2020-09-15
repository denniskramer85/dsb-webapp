package dsb.web.service;


import dsb.web.controller.beans.PrintAccountDataBean;
import dsb.web.domain.Account;
import dsb.web.domain.Customer;
import dsb.web.domain.SMEAccount;
import dsb.web.domain.Transaction;
import dsb.web.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
public class AccountPageService {

    private TransactionRepository transactionRepository;

    private static final int MAX_NR_HOLDERS_SHOWN = 3;
    private static final int MAX_NR_TRANSACTIONS_SHOWN = 10;
    private static final String STRING_ETCETERA = " e.a.";

    @Autowired
    public AccountPageService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }


    public PrintAccountDataBean makePrintAccountDataBean(Account account) {

        /**Get all bean data subsequently**/
        String typeAccount = account.printClassName();
        String accountNo = account.getAccountNo();
        String companyName = getCompanyName(account);
        String holderNames = createHoldersString(account.getHolders());
        String balance = String.format("%.2f", account.getBalance());
        String currentTime = getCurrentTime();
        List<String> transactionStrings = getTransactionStrings(account);

        return new PrintAccountDataBean(typeAccount, accountNo, companyName,
                holderNames, balance, currentTime, transactionStrings);
    }


    private String getCompanyName(Account account) {
        if (account instanceof SMEAccount) {
            try {
                return ((SMEAccount) account).getCompany().getName();
            } catch (NullPointerException e) {
                System.out.println("No company found");
            }
        }
        return " - ";
    }

    public String getCurrentTime() {
        System.currentTimeMillis();
        SimpleDateFormat formatter= new SimpleDateFormat("EEEE, dd MMMM yyyy '-' HH:mm 'uur'");
        Date timestamp = new Date(System.currentTimeMillis());
        String temp = formatter.format(timestamp);
        return temp.substring(0, 1).toUpperCase() + temp.substring(1);
    }




    public String createHoldersString(List<Customer> listHolders) {

        //TODO dit vereenvoudigen evt naar eigen klasse +  de aux methode

        //sort by surname
        Collections.sort(listHolders);

        //create unified name strings from Customer attributes (outsourced to aux method)
        List<String> holderNames = createListHolderNames(listHolders);

        //create final String with proper styling
        StringBuilder sb = new StringBuilder();

        //prevent outOfBound
        int maxLoop = MAX_NR_HOLDERS_SHOWN;
        if (holderNames.size() < MAX_NR_HOLDERS_SHOWN) {
            maxLoop = holderNames.size();
        };

        //compose string by appending
        for (int i = 0; i < maxLoop ; i++) {
            sb.append(holderNames.get(i)).append(", ");
        };

        //create actual String and remove last comma
        String finalString = sb.toString();
        finalString = finalString.substring(0, finalString.length() - 2);

        //add "etc." if number of holders exceeds maxNrHoldersShown
        if (holderNames.size() > MAX_NR_HOLDERS_SHOWN) {
            finalString = finalString + STRING_ETCETERA;
        };

        return finalString;
    }

    //aux method for previous one
    private List<String> createListHolderNames(List<Customer> listHolders) {
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






    private List<String> getTransactionStrings(Account account) {

        //TODO dit vereenvoudiggen evt naar eigen klasse ()incl bovenstaande method?

        //set up needed variables
        String ownAccountNo = account.getAccountNo();
        String stringResult, timeStamp, counterAccount, message, plusMinus;
        double amount;
        //final result list
        List<String> transactionStrings = new ArrayList<>();

        //getting transactions from database
        List<Transaction> transactions = transactionRepository.
                findTopNTransactionByAccounts(account.getAccountID(), MAX_NR_TRANSACTIONS_SHOWN);


        //loop thru transactions to make proper strings for display
        for (Transaction t : transactions) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
            timeStamp = t.getTransactionTimestamp().format(formatter);
            message = t.getMessage();
            amount = t.getTransactionAmount();

            //find counter account; if my account is credit [PLUS], if not [MINUS]
            if (ownAccountNo.equals(t.getCreditAccount().getAccountNo())) {
                counterAccount = t.getDebitAccount().getAccountNo();
                plusMinus = "+";
            } else {
                counterAccount = t.getCreditAccount().getAccountNo();
                plusMinus = "-";
            }

            //stylize transaction string
            stringResult = String.format("%s    |    %s    |    %s%.2f    |    %s",
                    timeStamp, counterAccount, plusMinus, amount, message);

            transactionStrings.add(stringResult);
        }

        return transactionStrings;
    }

}
