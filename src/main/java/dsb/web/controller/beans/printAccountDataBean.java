package dsb.web.controller.beans;


import dsb.web.domain.Transaction;

import java.util.List;

public class printAccountDataBean {

    private String typeAccount;
    private String accountNo;
    private String companyName;
    private String holderNames;
    private String balance;
    private String timestamp;
    private List<String> transactionStrings;

    public printAccountDataBean(String typeAccount, String accountNo, String companyName, String holderNames,
                                String balance, String timestamp, List<String> transactionStrings) {
        this.typeAccount = typeAccount;
        this.accountNo = accountNo;
        this.companyName = companyName;
        this.holderNames = holderNames;
        this.balance = balance;
        this.timestamp = timestamp;
        this.transactionStrings = transactionStrings;
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

    public String getHolderNames() {
        return holderNames;
    }

    public void setHolderNames(String holderNames) {
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

    public List<String> getTransactionStrings() {
        return transactionStrings;
    }

    public void setTransactionStrings(List<String> transactionStrings) {
        this.transactionStrings = transactionStrings;
    }
}



