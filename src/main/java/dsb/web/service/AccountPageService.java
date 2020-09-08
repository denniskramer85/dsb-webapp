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
        String companyName = getCompanyName(account);
        String holderNames = createHoldersString(account.getHolders());
        String balance = String.format("%.2f", account.getBalance());
        String currentTime = getCurrentTime();
        List<String> transactionStrings = getTransactionStrings(account);

        return new AccountPageBean(typeAccount, accountNo, companyName,
                holderNames, balance, currentTime, transactionStrings);
    }



    private List<String> getTransactionStrings(Account account) {

        //set up needed variables
        String ownAccountNo = account.getAccountNo();
        String stringResult, timeStamp, counterAccount, message, plusMinus;
        double amount;

        //final result list
        List<String> transactionStrings = new ArrayList<>();

        //getting transactions from database
        //TODO LIMIT WERKT NIET MET PARAM!!!
        List<Transaction> transactions = transactionRepository.findTransactionByAccounts(account.getAccountID());


        //loop thru transactions to make proper strings for display
        for (Transaction t : transactions) {
            timeStamp = new SimpleDateFormat("MM/dd/yyyy '-' HH:mm").
                    format(t.getTransactionTimestamp());
            message = t.getMessage();
            amount = t.getTransactionAmount();

            //find counter account; if my account is credit [PLUS], if not [MINUS]
            if (ownAccountNo.equals(t.getTransactionAccountCredit().getAccountNo())) {
                counterAccount = t.getTransactionAccountDebet().getAccountNo();
                plusMinus = "+";
            } else {
                counterAccount = t.getTransactionAccountCredit().getAccountNo();
                plusMinus = "-";
            }

            //stylize transaction string
            stringResult = String.format("%s    |    %s    |    %s%.2f    |    %s",
                    timeStamp, counterAccount, plusMinus, amount, message);

            transactionStrings.add(stringResult);
        }

        return transactionStrings;
    }



    private String getCompanyName(Account account) {
        if (account instanceof SMEAccount) {
            try {
                return ((SMEAccount) account).getCompany().getName();
            } catch (NullPointerException e) {

            }
        }
        return " - ";
    }


    public String createHoldersString(List<Customer> listHolders) {

        //sort by surname
        Collections.sort(listHolders);

        //create unified name strings from Customer attributes (outsourced to aux method)
        List<String> holderNames = createListHolderNames(listHolders);

        //create final String with proper styling
        StringBuilder sb = new StringBuilder();

        //prevent outOfBound
        int maxLoop = maxNrHoldersShown;
        if (holderNames.size() < maxNrHoldersShown) maxLoop = holderNames.size();

        //compose string by appending
        for (int i = 0; i < maxLoop ; i++) sb.append(holderNames.get(i)).append(", ");

        //create actual String and remove last comma
        String finalString = sb.toString();
        finalString = finalString.substring(0, finalString.length() - 2);

        //add "etc." if number of holders exceeds maxNrHoldersShown
        if (holderNames.size() > maxNrHoldersShown) finalString = finalString + " e.a.";

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


    public String getCurrentTime() {
        System.currentTimeMillis();
        SimpleDateFormat formatter= new SimpleDateFormat("EEEE, dd MMM yyyy '-' HH:mm 'uur'");
        Date timestamp = new Date(System.currentTimeMillis());
        String temp = formatter.format(timestamp);
        return temp.substring(0, 1).toUpperCase() + temp.substring(1);
    }



}
