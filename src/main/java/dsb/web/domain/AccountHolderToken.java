package dsb.web.domain;

import javax.persistence.*;

@Entity
public class AccountHolderToken {
    @Id
    @GeneratedValue
    private int accountHolderTokenId;

    @ManyToOne
    private Customer newAccountHolder;

    @ManyToOne
    private Account account;

    private int token;

    public AccountHolderToken(int accountHolderTokenId, Customer newAccountHolder, Account account, int token) {
        this.accountHolderTokenId = accountHolderTokenId;
        this.newAccountHolder = newAccountHolder;
        this.account = account;
        this.token = token;
    }

    public AccountHolderToken(Customer newAccountHolder, Account account, int token) {
        this.newAccountHolder = newAccountHolder;
        this.account = account;
        this.token = token;
    }

    public AccountHolderToken() {
    }

    public int getAccountHolderTokenId() {
        return accountHolderTokenId;
    }

    public void setAccountHolderTokenId(int accountHolderTokenId) {
        this.accountHolderTokenId = accountHolderTokenId;
    }

    public Customer getNewAccountHolder() {
        return newAccountHolder;
    }

    public void setNewAccountHolder(Customer newAccountHolder) {
        this.newAccountHolder = newAccountHolder;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public int getToken() {
        return token;
    }

    public void setToken(int token) {
        this.token = token;
    }
}
