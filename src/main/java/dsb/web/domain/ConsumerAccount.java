package dsb.web.domain;

import javax.persistence.Entity;

import java.util.List;

@Entity
public class ConsumerAccount extends Account{

    public ConsumerAccount(int accountID, String accountNo, double balance, List<Customer> holders) {
        super(accountID, accountNo, balance, holders);
    }

    public ConsumerAccount(String accountNo, double balance, List<Customer> holders) {
        super(accountNo, balance, holders);
    }

    public ConsumerAccount() {
    }

    @Override
    public String printClassName() {
        return this.getClass().getSimpleName();
    }

    @Override
    public String toString() {
        return "ConsumerAccount: " + super.toString();
    }
}
