package dsb.web.controller.beans;

import dsb.web.service.validators.TokenCodeConstraint;

public class AccountHolderTokenBean {
    private String accountNo;
    private String holders;
    private String tokenId;
    //@TokenCodeConstraint
    private String codeInput;

    public AccountHolderTokenBean(String accountNo, String holders, String tokenId, String codeInput) {
        this.accountNo = accountNo;
        this.holders = holders;
        this.tokenId = tokenId;
        this.codeInput = codeInput;
    }

    public AccountHolderTokenBean(String accountNo, String holders, String tokenId) {
        this.accountNo = accountNo;
        this.holders = holders;
        this.tokenId = tokenId;
    }

    public AccountHolderTokenBean() {
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getHolders() {
        return holders;
    }

    public void setHolders(String holders) {
        this.holders = holders;
    }

    public String getTokenId() {
        return tokenId;
    }

    public void setTokenId(String tokenId) {
        this.tokenId = tokenId;
    }

    public String getCodeInput() {
        return codeInput;
    }

    public void setCodeInput(String codeInput) {
        this.codeInput = codeInput;
    }

    @Override
    public String toString() {
        return "AccountHolderTokenBean{" +
                "accountNo='" + accountNo + '\'' +
                ", holders='" + holders + '\'' +
                ", tokenId='" + tokenId + '\'' +
                ", codeInput='" + codeInput + '\'' +
                '}';
    }
}
