package dsb.web.controller.beans;


import dsb.web.domain.Transaction;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;

public class AccountPageBean {

    private String typeAccount;
    private String accountNo;
    private String companyName;
    private List<String> holderNames;
    private String balance;
    private String timestamp;
    private List<Transaction> transactions;


    public AccountPageBean(String typeAccount, String accountNo, String companyName, List<String> holderNames, String balance, String timestamp, List<Transaction> transactions) {
        this.typeAccount = typeAccount;
        this.accountNo = accountNo;
        this.companyName = companyName;
        this.holderNames = holderNames;
        this.balance = balance;
        this.timestamp = timestamp;
        this.transactions = transactions;
    }

    @Override
    public String toString() {
        return "AccountPageBean{" +
                "typeAccount='" + typeAccount + '\'' +
                ", accountNo='" + accountNo + '\'' +
                ", companyName='" + companyName + '\'' +
                ", holderNames=" + holderNames +
                ", balance=" + balance +
                ", timestamp=" + timestamp +
                ", transactions=" + transactions +
                '}';
    }

    public String getTypeAccount() {
        return typeAccount;
    }

    public void setTypeAccount(String typeAccount) {
        this.typeAccount = typeAccount;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public List<String> getHolderNames() {
        return holderNames;
    }

    public void setHolderNames(List<String> holderNames) {
        this.holderNames = holderNames;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }
}



