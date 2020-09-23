package dsb.web.domain;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
public class Customer extends User implements Comparable<Customer> {

    private int socialSecurityNo;
    @ManyToOne(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    private Address address;
    @ManyToMany (mappedBy = "holders", cascade = {CascadeType.MERGE} , fetch = FetchType.EAGER)
    private List<Account> accounts;


    //TODO beide constructors nodig? of fluent interface?
    public Customer(int userID, int socialSecurityNo, String initials, String inserts, String surname,
                    Address address, String username, String password, List<Account> accounts) {
        super(userID, initials, inserts, surname, username, password);
        this.socialSecurityNo = socialSecurityNo;
        this.address = address;
        this.accounts = accounts;
    }

    public Customer(int socialSecurityNo, String initials, String inserts, String surname,
                    Address address, String username, String password, List<Account> accounts) {
        super(initials, inserts, surname, username, password);
        this.socialSecurityNo = socialSecurityNo;
        this.address = address;
        this.accounts = accounts;
    }

    //constructor only for printing whole names
    public Customer(String initials, String inserts, String surname) {
        this();
        this.setInitials(initials);
        this.setInserts(inserts);
        this.setSurname(surname);
    }

    public Customer() {
    }

    @Override
    public String toString() {
        return "Customer{" +
                "socialSecurityNo=" + socialSecurityNo +
                ", address=" + address +
                ", accounts=" + accounts +
                "} " + super.toString();
    }

    public String printWholeName() {
        if (super.getInserts() == null) {
            super.setInserts("");
        } else {
            super.setInserts(super.getInserts() + " ");
        }
        return String.format("%s %s%s", super.getInitials(), super.getInserts(), super.getSurname());
    }


    public int getSocialSecurityNo() {
        return socialSecurityNo;
    }

    public void setSocialSecurityNo(int socialSecurityNo) {
        this.socialSecurityNo = socialSecurityNo;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    @Override
    public int compareTo(Customer o) {
        return this.getSurname().compareTo(o.getSurname());
    }
}
