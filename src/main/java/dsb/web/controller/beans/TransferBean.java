package dsb.web.controller.beans;

import dsb.web.domain.Account;
import dsb.web.service.validators.AccountNoConstraint;
import dsb.web.service.validators.BigDecimalConstraint;
import dsb.web.service.validators.DSBAccountConstraint;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.constraints.*;
import java.math.BigDecimal;

/**
 *  This bean is used for currency transfers that have not yet been validated.
 *  All field validation happens in this bean, providing error messages to the front-end if validation fails.
 *  If validation is successful, a Transaction object can be create for storage in the database.
 */

public class TransferBean {
    private Account debitAccount;

    //@NotBlank(message = "Vul een tegenrekening in")
    // TODO: Werkend krijgen @AccountNoConstraint
    //@DSBAccountConstraint
    private String creditAccountNo;

    @NotBlank(message = "Voer een bedrag in")
    @BigDecimalConstraint
    //@Digits(integer = 50, fraction = 2, message = "Voer een bedrag in euro's en centen in")
    //@Positive(message = "Voer een bedrag groter dan 0 in")
    private String transferAmountString;
    private BigDecimal transferAmount;

    //@AssertTrue(message = "Onvoldoende saldo voor transactie")
    private boolean sufficientFunds;

    //@Length(max = 50, message = "Maximaal 50 karakters")
    private String message;

    public TransferBean(Account debitAccount, String creditAccountNo, String transferAmountString, String message) {
        this.debitAccount = debitAccount;
        this.creditAccountNo = creditAccountNo;
        this.transferAmountString = transferAmountString;
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

    public String getTransferAmountString() {
        return transferAmountString;
    }

    public void setTransferAmountString(String transferAmountString) {
        this.transferAmountString = transferAmountString;
    }

    public BigDecimal getTransferAmount() {
        return transferAmount;
    }

    public void setTransferAmount(BigDecimal transferAmount) {
        this.transferAmount = transferAmount;
    }

    public boolean isSufficientFunds() {
        return sufficientFunds;
    }

    public void setSufficientFunds(boolean sufficientFunds) {
        this.sufficientFunds = sufficientFunds;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "TransferBean{" +
                "debitAccount=" + debitAccount +
                ", creditAccountNo='" + creditAccountNo + '\'' +
                ", transferAmount=" + transferAmount +
                ", sufficientFunds=" + sufficientFunds +
                ", message='" + message + '\'' +
                '}';
    }
}
