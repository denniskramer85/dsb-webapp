package dsb.web.controller.beans;


import dsb.web.domain.Transaction;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;

public class AccountPageBean {

    private int typeAccount;   // 0 = 1 consumer, 1 = SME
    private String accountNo;
    private String companyName;
    private List<String> holderNames;       //form, volgorde en aantal
    private double balance;
    private Timestamp timestamp;
    private List<Transaction> transactions;

}



