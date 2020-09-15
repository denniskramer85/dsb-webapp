package dsb.web.domain;

import dsb.web.domain.domain_helpers.CreateAccountHoldersString;

import javax.persistence.*;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Account {
    @Id
    @GeneratedValue
    private int accountID;
    private String accountNo;
    private double balance;
    @ManyToMany ( fetch = FetchType.EAGER)
    private List<Customer> holders;

    public Account(int accountID, String accountNo, double balance, List<Customer> holders) {
        this.accountID = accountID;
        this.accountNo = accountNo;
        this.balance = balance;
        this.holders = holders;
    }

    public Account(String accountNo, double balance, List<Customer> holders) {
        this.accountNo = accountNo;
        this.balance = balance;
        this.holders = holders;
    }

    //copy constructor
    public Account(Account account) {
        this.accountID = account.accountID;
        this.accountNo = account.accountNo;
        this.balance = account.balance;
        this.holders = account.holders;
    }

    public Account() {
    }

    public abstract String printClassName();

    public int getAccountID() {
        return accountID;
    }

    public void setAccountID(int accountID) {
        this.accountID = accountID;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public List<Customer> getHolders() {
        return holders;
    }

    public void setHolders(List<Customer> holders) {
        this.holders = holders;
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountID=" + accountID +
                ", accountNo='" + accountNo + '\'' +
                ", balance=" + balance +
                '}';
    }


    //TODO weghalen
//    public String getHolderString() {
//        String returnString = "";
//        for (Customer holder : holders) {
//            returnString += holder.getInitials() + " ";
//            if (holder.getInserts() != null) {
//                returnString += holder.getInserts() + " ";
//            }
//            returnString += holder.getSurname();
//        }
//        return returnString;
//    }

    //zoeken met ctrl+R en wijzigen
    public String getHoldersString (int maxHoldersShown) {
        CreateAccountHoldersString createAccountHoldersString =
                new CreateAccountHoldersString(holders, maxHoldersShown);
        return createAccountHoldersString.createAccountHoldersString();
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return accountNo.equals(account.accountNo);
    }

    @Override
    public int hashCode() {
        return 0;
    }
}
