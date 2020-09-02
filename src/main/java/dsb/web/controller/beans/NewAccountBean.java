package dsb.web.controller.beans;

import dsb.web.domain.Bussiness;

public class NewAccountBean {
    private int accountType;
    private Bussiness bussiness;

    public NewAccountBean(int accountType, Bussiness bussiness) {
        this.accountType = accountType;
        this.bussiness = bussiness;
    }

    public NewAccountBean() {
    }

    @Override
    public String toString() {
        return "newAccountBean{" +
                "accountType=" + accountType +
                ", bussiness=" + bussiness +
                '}';
    }

    public int getAccountType() {
        return accountType;
    }

    public void setAccountType(int accountType) {
        this.accountType = accountType;
    }

    public Bussiness getBussiness() {
        return bussiness;
    }

    public void setBussiness(Bussiness bussiness) {
        this.bussiness = bussiness;
    }
}
