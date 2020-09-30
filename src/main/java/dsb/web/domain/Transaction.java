package dsb.web.domain;

import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "transaction_generator")
    @SequenceGenerator(name="transaction_generator", initialValue = 1000, sequenceName = "transaction_seq")
    private int transactionID;

    @ManyToOne(fetch = FetchType.LAZY)
    private Account debitAccount;
    @ManyToOne(fetch = FetchType.LAZY)
    private Account creditAccount;

    private double transactionAmount;
    private String message;
    private LocalDateTime transactionTimestamp;

    public Transaction(int transactionID, Account debitAccount, Account creditAccount,
                       double transactionAmount, String message, LocalDateTime transactionTimestamp) {
        this.transactionID = transactionID;
        this.debitAccount = debitAccount;
        this.creditAccount = creditAccount;
        this.transactionAmount = transactionAmount;
        this.message = message;
        this.transactionTimestamp = transactionTimestamp;
    }

    public Transaction(Account debitAccount, Account creditAccount, double transactionAmount, String message, LocalDateTime transactionTimestamp) {
        this.debitAccount = debitAccount;
        this.creditAccount = creditAccount;
        this.transactionAmount = transactionAmount;
        this.message = message;
        this.transactionTimestamp = transactionTimestamp;
    }

    public Transaction() {
    }


    @Override
    public String toString() {
        return "Transaction{" +
                "transactionID=" + transactionID +
                ", debitAccount=" + debitAccount +
                ", creditAccount=" + creditAccount +
                ", transactionAmount=" + transactionAmount +
                ", message='" + message + '\'' +
                ", transactionTimestamp=" + transactionTimestamp +
                '}';
    }

    public String printStyledTransaction() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        String timeStamp = transactionTimestamp.format(formatter);
        return String.format("%s    |    %s    |    %30s    |    %7.2f    |    %s",
                timeStamp, debitAccount.getAccountNo(), creditAccount.getAccountNo(), transactionAmount, message);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Transaction)) return false;
        Transaction that = (Transaction) o;
        return  Double.compare(that.getTransactionAmount(), getTransactionAmount()) == 0 &&
                getDebitAccount().equals(that.getDebitAccount()) &&
                getCreditAccount().equals(that.getCreditAccount()) &&
                getMessage().equals(that.getMessage()) &&
                getTransactionTimestamp().equals(that.getTransactionTimestamp());
    }


    public int getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(int transactionID) {
        this.transactionID = transactionID;
    }

    public Account getDebitAccount() {
        return debitAccount;
    }

    public void setDebitAccount(Account transactionAccountDebet) {
        this.debitAccount = transactionAccountDebet;
    }

    public Account getCreditAccount() {
        return creditAccount;
    }

    public void setCreditAccount(Account transactionAccountCredit) {
        this.creditAccount = transactionAccountCredit;
    }

    public double getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(double transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getTransactionTimestamp() {
        return transactionTimestamp;
    }

    public void setTransactionTimestamp(LocalDateTime transactionTimestamp) {
        this.transactionTimestamp = transactionTimestamp;
    }
}
