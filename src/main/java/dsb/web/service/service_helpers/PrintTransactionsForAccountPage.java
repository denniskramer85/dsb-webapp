package dsb.web.service.service_helpers;

import dsb.web.domain.Account;
import dsb.web.domain.Transaction;
import dsb.web.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class PrintTransactionsForAccountPage {


    private static final int MAX_NR_TRANSACTIONS_SHOWN = 10;

    private Account account;
    private String ownAccountNo, timeStamp, counterAccount, message, plusMinus, amount;
    private List<Transaction> transactions;

    private TransactionRepository transactionRepository;


    @Autowired
    public PrintTransactionsForAccountPage(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }


    public List<String[]> printTransactionsForAccountPage(Account accountMP) {

        //set up variables
        account = accountMP;
        ownAccountNo = account.getAccountNo();
        List<String[]> transactionStrings = new ArrayList<>();

        //get all transactions for this account
        transactions = transactionRepository.
                findTopNTransactionByAccounts(account.getAccountID(), MAX_NR_TRANSACTIONS_SHOWN);

        //loop thru each transaction to make stylized strings for display
        for (Transaction transaction : transactions) {
            transactionStrings.add(createIndividualTransaction(transaction));
        }

        return transactionStrings;
    }

    private String[] createIndividualTransaction(Transaction transaction) {

        determineCounterAccount(transaction);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        timeStamp = transaction.getTransactionTimestamp().format(formatter);
        message = transaction.getMessage();
        amount = String.format("%s%.2f", plusMinus, transaction.getTransactionAmount());

        String[] result = {timeStamp, counterAccount, amount, message};

        return result;
    }

    private void determineCounterAccount(Transaction transaction) {
        //determine counter account
        //if my own account is credit, display '+' before amount and vice versa
        if (ownAccountNo.equals(transaction.getCreditAccount().getAccountNo())) {
            counterAccount = transaction.getDebitAccount().getAccountNo();
            plusMinus = "+";
        } else {
            counterAccount = transaction.getCreditAccount().getAccountNo();
            plusMinus = "-";
        }
    }


}
