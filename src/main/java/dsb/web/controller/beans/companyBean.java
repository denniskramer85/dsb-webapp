package dsb.web.controller.beans;

import dsb.web.domain.Company;

public class companyBean {
    private int accountType;
    private Company company;

    public companyBean(int accountType, Company company) {
        this.accountType = accountType;
        this.company = company;
    }

    public companyBean() {
    }

    @Override
    public String toString() {
        return "newAccountBean{" +
                "accountType=" + accountType +
                ", company=" + company +
                '}';
    }

    public int getAccountType() {
        return accountType;
    }

    public void setAccountType(int accountType) {
        this.accountType = accountType;
    }

    public Company getcompany() {
        return company;
    }

    public void setcompany(Company company) {
        this.company = company;
    }
}
