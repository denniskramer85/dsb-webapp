package dsb.web.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.List;

@Entity
public class Account {
    @Id
    @GeneratedValue
    private int accountID;
    private String accountNo;
    private double balance;
    @ManyToMany (mappedBy = "accounts")
    private List<Customer> holders;

    public Account(int accountID, String accountNo, double balance, List<Customer> holders) {
        this.accountID = accountID;
        this.accountNo = accountNo;
        this.balance = balance;
        this.holders = holders;
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
}
