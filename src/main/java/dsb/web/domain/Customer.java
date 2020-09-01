package dsb.web.domain;

import javax.persistence.*;
import java.util.List;

@Entity
public class Customer {
    @Id
    @GeneratedValue
    private int customerID;
    private int socialSecurityNo;
    private String initials;
    private String inserts;
    private String surname;
    @ManyToOne(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    private Address address;
    private String username;
    private String password;
    @ManyToMany
    private List<Account> accounts;

    public Customer(int customerID, int socialSecurityNo, String initials, String inserts, String surname, Address address, String username, String password, List<Account> accounts) {
        this.customerID = customerID;
        this.socialSecurityNo = socialSecurityNo;
        this.initials = initials;
        this.inserts = inserts;
        this.surname = surname;
        this.address = address;
        this.username = username;
        this.password = password;
        this.accounts = accounts;
    }

    public Customer(int socialSecurityNo, String initials, String inserts, String surname,
                    Address address, String username, String password, List<Account> accounts) {
        this(0, socialSecurityNo, initials, inserts, surname, address, username, password, accounts);
    }

    public Customer() {
    }

    @Override
    public String
    toString() {
        return "Customer{" +
                "customerID=" + customerID +
                ", socialSecurityNo=" + socialSecurityNo +
                ", initials='" + initials + '\'' +
                ", inserts='" + inserts + '\'' +
                ", surname='" + surname + '\'' +
                ", address=" + address +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", accounts=" + accounts +
                '}';
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public int getSocialSecurityNo() {
        return socialSecurityNo;
    }

    public void setSocialSecurityNo(int socialSecurityNo) {
        this.socialSecurityNo = socialSecurityNo;
    }

    public String getInitials() {
        return initials;
    }

    public void setInitials(String initials) {
        this.initials = initials;
    }

    public String getInserts() {
        return inserts;
    }

    public void setInserts(String inserts) {
        this.inserts = inserts;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }
}
