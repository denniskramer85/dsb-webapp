package dsb.web.service.service_helpers;

import dsb.web.domain.Account;
import dsb.web.domain.Transaction;
import dsb.web.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class PrintTransactionsForAccountPage {


    private static final int MAX_NR_TRANSACTIONS_SHOWN = 10;
    private TransactionRepository transactionRepository;


    private Account account;
    private String ownAccountNo, stringResult, timeStamp, counterAccount, message, plusMinus;
    double amount;
    private List<Transaction> transactions;
    private List<String> transactionStrings = new ArrayList<>();



    @Autowired
    public PrintTransactionsForAccountPage(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }


    public List<String> printTransactionsForAccountPage(Account accountMP) {

        account = accountMP;
        ownAccountNo = account.getAccountNo();

        //get all transactions for this account
        transactions = transactionRepository.
                findTopNTransactionByAccounts(account.getAccountID(), MAX_NR_TRANSACTIONS_SHOWN);


        //loop thru each transaction to make stylized strings for display
        for (Transaction t : transactions) {
            createIndividualTransaction(t);
        }

        return transactionStrings;

    }

    private void createIndividualTransaction(Transaction transaction) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        timeStamp = transaction.getTransactionTimestamp().format(formatter);
        message = transaction.getMessage();
        amount = transaction.getTransactionAmount();

        //find counter account; if my account is credit [PLUS], if not [MINUS]
        if (ownAccountNo.equals(transaction.getCreditAccount().getAccountNo())) {
            counterAccount = transaction.getDebitAccount().getAccountNo();
            plusMinus = "+";
        } else {
            counterAccount = transaction.getCreditAccount().getAccountNo();
            plusMinus = "-";
        }

        //stylize transaction string
        stringResult = String.format("%s    |    %s    |    %s%.2f    |    %s",
                timeStamp, counterAccount, plusMinus, amount, message);

        transactionStrings.add(stringResult);
    }


}
