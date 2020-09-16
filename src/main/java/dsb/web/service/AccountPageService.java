package dsb.web.service;


import dsb.web.controller.beans.PrintAccountDataBean;
import dsb.web.domain.Account;
import dsb.web.domain.SMEAccount;
import dsb.web.service.service_helpers.PrintTransactionsForAccountPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class AccountPageService {


    PrintTransactionsForAccountPage printTransactionsForAccountPage;

    @Autowired
    public AccountPageService(PrintTransactionsForAccountPage printTransactionsForAccountPage) {
        this.printTransactionsForAccountPage = printTransactionsForAccountPage;
    }

    public PrintAccountDataBean makePrintAccountDataBean(Account account) {

        /**Get all bean data subsequently**/
        String typeAccount = account.printClassName();
        String accountNo = account.getAccountNo();
        String companyName = getCompanyName(account);
        String holderNames = account.getHoldersString(3);
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



    private List<String> getTransactionStrings(Account account) {

        return printTransactionsForAccountPage.printTransactionsForAccountPage(account);


    }

}
