package dsb.web.domain;

import javax.persistence.*;
import java.util.List;

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
    public String printClassName() {
        return this.getClass().getSimpleName();
    }
}

