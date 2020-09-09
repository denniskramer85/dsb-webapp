package dsb.web.service;


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

    public List<Transaction> getTop10Transaction(Transaction transaction) {
        List<Transaction> transactionList = transactionRepository.findAll();
        return transactionList;
    }



//In de eerste eventhandler roep je eerst je service klasse op,
    //1 maak klasse smeadashboardservice, in je servicepakket
    //2 Zet deze klasse klasse klaar in je controller via constructor met @autowired, dat wordtd een attribuut in je controller
    //3 zet in je serviceklasse account.repository klaar met @autowired zodat het een attribuut wordt in de service klasse, zodat je in je service klasse kan beschikken over de data
    //4 in de eerste eventhandler in controller een methode aanroepen die in de serviceklasse staat, die methode bouw je in de service klasse,
    //5 methode doet een querie in de repository.


}
