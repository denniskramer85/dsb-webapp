package dsb.web.controller.beans;

import dsb.web.domain.Account;
import dsb.web.service.validators.AccountNoConstraint;
import dsb.web.service.validators.DSBAccountConstraint;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

/**
 *  This bean is used for currency transfers that have not yet been validated.
 *  All field validation happens in this bean, providing error messages to the front-end if validation fails.
 *  If validation is successful, a Transaction object can be create for storage in the database.
 */

public class TransferBean {
    private Account debitAccount;

    @NotBlank(message = "Vul een tegenrekening in")
    @AccountNoConstraint
    @DSBAccountConstraint
    private String creditAccountNo;

    @Digits(integer = 50, fraction = 2, message = "Voer een geldig bedrag in")
    @Positive(message = "Voer een bedrag groter dan 0 in")
    private BigDecimal transferAmount;

    @AssertTrue(message = "Onvoldoende saldo voor transactie")
    private boolean sufficientFunds;

    @Length(max = 50, message = "Maximaal 50 karakters")
    private String message;

    @Autowired
    public TransferBean(Account debitAccount, String creditAccountNo, BigDecimal transferAmount, String message) {
        this.debitAccount = debitAccount;
        this.creditAccountNo = creditAccountNo;
        this.transferAmount = transferAmount;
        this.message = message;
        checkSufficientFunds();
    }

    public TransferBean() {
    }

    private void checkSufficientFunds() {
        sufficientFunds = debitAccount.getBalance() > transferAmount.doubleValue();
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
        try {
            return transferAmount.doubleValue();
        } catch (NullPointerException exception) {
            System.out.println(exception);
            return null;
        }


    }

    public void setTransferAmount(Double transferAmount) {
        this.transferAmount = BigDecimal.valueOf(transferAmount);
        checkSufficientFunds();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSufficientFunds() {
        return sufficientFunds;
    }

    public void setSufficientFunds(boolean sufficientFunds) {
        this.sufficientFunds = sufficientFunds;
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
