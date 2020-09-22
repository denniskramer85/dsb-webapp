package dsb.web.controller.beans;

import dsb.web.controller.AccountOverviewController;
import dsb.web.service.service_helpers.BigDecimalHelper;
import dsb.web.service.validators.AccountNoConstraint;
import dsb.web.service.validators.CurrencyFormatConstraint;
import dsb.web.service.validators.DSBAccountConstraint;
import dsb.web.service.validators.FieldsValueDifferent;
import org.hibernate.validator.constraints.Length;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.constraints.*;
import java.math.BigDecimal;

/**
 *  This bean is used for currency transfers that have not yet been validated.
 *  All field validation happens in this bean, providing error messages to the front-end if validation fails.
 *  If validation is successful, a Transaction object can be create for storage in the database.
 */

@FieldsValueDifferent(first = "debitAccountNo", second = "CreditAccountNo")
public class TransferBean {
    private Logger logger = LoggerFactory.getLogger(AccountOverviewController.class);

    private double accountBalance;
    private String debitAccountNo;

    @NotBlank(message = "Vul een tegenrekening in")
    @AccountNoConstraint
    @DSBAccountConstraint
    private String creditAccountNo;

    @NotBlank(message = "Vul de naam van de ontvanger in")
    @Length(max = 100, message = "Gebruik maximaal 100 karakters")
    private String name;

    @NotBlank(message = "Voer een bedrag in")
    @CurrencyFormatConstraint
    private String transferAmountString;

    @Positive(message = "Voer een bedrag groter dan 0 in")
    @Digits(integer = 50, fraction = 2, message = "Voer maximaal twee cijfers achter de komma in")
    private BigDecimal transferAmount;

    @AssertTrue(message = "Onvoldoende saldo voor transactie")
    private boolean sufficientFunds = false;

    @Length(max = 100, message = "Gebruik maximaal 100 karakters")
    private String message;

    public TransferBean() {
    }

    // Compare parsed BigDecimal transferAmount to account balance
    private void checkSufficientFunds() {
        if (transferAmount != null) {
            setSufficientFunds(accountBalance > transferAmount.doubleValue());
        }
    }

    public double getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(double accountBalance) {
        this.accountBalance = accountBalance;
    }

    public String getDebitAccountNo() {
        return debitAccountNo;
    }

    public void setDebitAccountNo(String debitAccountNo) {
        this.debitAccountNo = debitAccountNo;
    }

    public String getCreditAccountNo() {
        return creditAccountNo;
    }

    public void setCreditAccountNo(String creditAccountNo) {
        this.creditAccountNo = creditAccountNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTransferAmountString() {
        return transferAmountString;
    }

    public void setTransferAmountString(String transferAmountString) {
        this.transferAmountString = transferAmountString;
        transferAmount = BigDecimalHelper.parse(transferAmountString);
        checkSufficientFunds();
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
                "accountBalance=" + accountBalance +
                ", accountNo='" + debitAccountNo + '\'' +
                ", creditAccountNo='" + creditAccountNo + '\'' +
                ", transferAmountString='" + transferAmountString + '\'' +
                ", transferAmount=" + transferAmount +
                ", message='" + message + '\'' +
                '}';
    }
}
