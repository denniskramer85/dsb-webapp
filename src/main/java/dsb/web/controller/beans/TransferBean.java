package dsb.web.controller.beans;

import dsb.web.domain.Account;
import dsb.web.service.validators.AccountNoConstraint;
import dsb.web.service.validators.DSBAccountConstraint;
import dsb.web.service.validators.SufficientFundsConstraint;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

public class TransferBean {
    private Account debitAccount;

    @NotBlank
    @AccountNoConstraint
    @DSBAccountConstraint
    private String creditAccountNo;

    @Digits(integer = 50, fraction = 2, message = "Voer een geldig bedrag in")
    @Positive(message = "Voer een bedrag groter dan 0 in")
    @SufficientFundsConstraint
    private BigDecimal transferAmount;

    @Length(max = 50, message = "Maximaal 50 karakters")
    private String message;

    @Autowired
    public TransferBean(Account debitAccount, String creditAccountNo, BigDecimal transferAmount, String message) {
        this.debitAccount = debitAccount;
        this.creditAccountNo = creditAccountNo;
        this.transferAmount = transferAmount;
        this.message = message;
    }

    public TransferBean() {
    }

    public Account getDebitAccount() {
        return debitAccount;
    }

    public void setDebitAccount(Account debitAccount) {
        this.debitAccount = debitAccount;
    }

    public String getCreditAccountNo() {
        return creditAccountNo;
    }

    public void setCreditAccountNo(String creditAccountNo) {
        this.creditAccountNo = creditAccountNo;
    }

    public Double getTransferAmount() {
        return transferAmount.doubleValue();
    }

    public void setTransferAmount(Double transferAmount) {
        this.transferAmount = BigDecimal.valueOf(transferAmount);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
