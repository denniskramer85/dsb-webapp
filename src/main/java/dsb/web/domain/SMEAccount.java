package dsb.web.domain;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
public class SMEAccount extends Account{
    @ManyToOne
    private Company company;


    public SMEAccount(int accountID, String accountNo, double balance, List<Customer> holders, Company company) {
        super(accountID, accountNo, balance, holders);
        this.company = company;
    }

    public SMEAccount(String accountNo, double balance, List<Customer> holders, Company company) {
        super(accountNo, balance, holders);
        this.company = company;
    }

    public SMEAccount(Company company) {
        this.company = company;
    }

    public SMEAccount() {
    }

    @Override
    public String toString() {
        return "SMEAccount{" +
                "company=" + company +
                '}' + super.toString();
    }


    @Override
    public String getHolderString() {
        if (this.company != null) {
            return this.company.getName() + " (" + super.getHolderString() + ")";
        } else {
            return super.getHolderString();
        }
    }

    @Override
    public String printClassName() {
        return this.getClass().getSimpleName();
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

}

