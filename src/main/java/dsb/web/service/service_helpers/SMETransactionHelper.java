package dsb.web.service.service_helpers;

import dsb.web.domain.Account;

public class SMETransactionHelper implements Comparable<SMETransactionHelper> {

   private Account account;
   private int numberOfTransactions;

    public SMETransactionHelper(Account account, int numberOfTransactions) {
        this.account = account;
        this.numberOfTransactions = numberOfTransactions;
    }


    @Override
    public int compareTo(SMETransactionHelper o) {

        return o.numberOfTransactions - this.numberOfTransactions;
    }


    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public int getNumberOfTransactions() {
        return numberOfTransactions;
    }

    public void setNumberOfTransactions(int numberOfTransactions) {
        this.numberOfTransactions = numberOfTransactions;
    }

    @Override
    public String toString() {
        return "SMETransactionHelper{" +
                "account=" + account +
                ", numberOfTransactions=" + numberOfTransactions +
                '}';
    }
}
