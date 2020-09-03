package dsb.web.controller.beans;

import dsb.web.domain.Company;
import dsb.web.domain.Customer;
import org.springframework.context.annotation.Scope;

import java.util.List;

public class NewAccountBean {
    private String accountNo;
    private double balance;
    private Customer holder;
    private String name;
    private String KVKno;
    private String BTWno;

    public NewAccountBean(String accountNo, double balance, Customer holder, String name, String KVKno, String BTWno) {
        this.accountNo = accountNo;
        this.balance = balance;
        this.holder = holder;
        this.name = name;
        this.KVKno = KVKno;
        this.BTWno = BTWno;
    }

    public NewAccountBean() {
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

    public Customer getHolder() {
        return holder;
    }

    public void setHolder(Customer holder) {
        this.holder = holder;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKVKno() {
        return KVKno;
    }

    public void setKVKno(String KVKno) {
        this.KVKno = KVKno;
    }

    public String getBTWno() {
        return BTWno;
    }

    public void setBTWno(String BTWno) {
        this.BTWno = BTWno;
    }

    @Override
    public String toString() {
        return "NewAccountBean{" +
                "accountNo='" + accountNo + '\'' +
                ", balance=" + balance +
                ", holder=" + holder.getUsername() +
                ", name='" + name + '\'' +
                ", KVKno='" + KVKno + '\'' +
                ", BTWno='" + BTWno + '\'' +
                '}';
    }
}
