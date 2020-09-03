package dsb.web.domain;

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
    @ManyToMany (mappedBy = "accounts", fetch = FetchType.LAZY)
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
}
