package dsb.web.domain;

import javax.persistence.*;

@Entity
public class TokenPaymentMachine {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tpm_generator")
    @SequenceGenerator(name="tpm_generator", initialValue = 1000, sequenceName = "tpm_seq")
    private int tokenID;

    @ManyToOne
    private SMEAccount smeAccount;

    private int securityCode;

    public TokenPaymentMachine(int tokenID, SMEAccount smeAccount) {
        this.tokenID = tokenID;
        this.smeAccount = smeAccount;
    }

    public TokenPaymentMachine(SMEAccount smeAccount) {
        this.smeAccount = smeAccount;
    }

    public TokenPaymentMachine() {

    }

    public int getTokenID() {
        return tokenID;
    }

    public void setTokenID(int tokenID) {
        this.tokenID = tokenID;
    }

    public SMEAccount getSmeAccount() {
        return smeAccount;
    }

    public void setSmeAccount(SMEAccount smeAccount) {
        this.smeAccount = smeAccount;
    }

    public int getSecurityCode() {
        return securityCode;
    }

    public void setSecurityCode(int securityCode) {
        this.securityCode = securityCode;
    }

    @Override
    public String toString() {
        return "TokenPaymentMachine{" +
                "tokenID=" + tokenID +
                ", smeAccount=" + smeAccount +
                ", securityCode=" + securityCode +
                '}';
    }
}
