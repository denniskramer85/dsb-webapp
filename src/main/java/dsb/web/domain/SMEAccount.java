package dsb.web.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.List;

@Entity
public class SMEAccount extends Account{

    private String randString;


    public SMEAccount(int accountID, String accountNo, double balance, List<Customer> holders, String randString) {
        super(accountID, accountNo, balance, holders);
        this.randString = randString;
    }


    public SMEAccount() {
    }

    public String getRandString() {
        return randString;
    }

    public void setRandString(String randString) {
        this.randString = randString;
    }
}


