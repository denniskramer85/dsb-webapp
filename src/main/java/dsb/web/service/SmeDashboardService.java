package dsb.web.service;


import dsb.web.domain.SMEAccount;
import dsb.web.domain.Transaction;
import dsb.web.repository.SMEAccountRepository;
import dsb.web.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SmeDashboardService {
    private SMEAccountRepository smeAccountRepository;
    private TransactionRepository transactionRepository;

    @Autowired
    public SmeDashboardService(SMEAccountRepository smeAccountRepository, TransactionRepository transactionRepository) {
        this.smeAccountRepository = smeAccountRepository;
        this.transactionRepository = transactionRepository;
    }

    public SmeDashboardService() {
    }

    public List<Transaction> getTop10SmeTransaction() {
        List<Transaction> transactionList = transactionRepository.findAll();



        // door deze lijst heen loope, alle transcaties bijbehorende bedragen optellen, sorteren op de grootste. Compartor schrijven, 2 transacties. Alle transacties per klant,
        // Lijst per klant hebben waarin al de transacties in staan, in de from of de to, als die lijsten heb je top 10.
        // map maken(has) key customer, int(counter). value is een lijst van aantal transacties. Voor elke transactie heb je een from en to, voor beide ga je hashmap ophogen, aan het einde van de loop heb je een.
        return transactionList;
    }


    public List<SMEAccount> getTop10bySmeBalance() {
        List<SMEAccount> smeAccountsList = smeAccountRepository.findTop10ByOrderByBalanceDesc();
        return smeAccountsList;
    }


}
