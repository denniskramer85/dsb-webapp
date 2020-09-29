package dsb.web.domain;

import javax.persistence.*;

@Entity
public class AccountHolderToken {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "aht_generator")
    @SequenceGenerator(name="aht_generator", initialValue = 1000, sequenceName = "aht_seq")
    private int accountHolderTokenId;

    @ManyToOne
    private Customer newAccountHolder;

    @ManyToOne
    private Account account;

    private String token;

    public AccountHolderToken(int accountHolderTokenId, Customer newAccountHolder, Account account, String token) {
        this.accountHolderTokenId = accountHolderTokenId;
        this.newAccountHolder = newAccountHolder;
        this.account = account;
        this.token = token;
    }

    public AccountHolderToken(Customer newAccountHolder, Account account, String token) {
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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "AccountHolderToken{" +
                "accountHolderTokenId=" + accountHolderTokenId +
                ", newAccountHolder=" + newAccountHolder +
                ", account=" + account +
                ", token=" + token +
                '}';
    }
}
