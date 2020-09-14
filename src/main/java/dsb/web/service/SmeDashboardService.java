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
        return transactionList;
    }


    public List<SMEAccount> getTop10bySmeBalance() {
        List<SMEAccount> smeAccountsList = smeAccountRepository.findTop10ByOrderByBalanceDesc();
        return smeAccountsList;
    }

    public List<SMEAccount> getAverageTop10BySector() {
        List<SMEAccount> AverageTop10BySector = smeAccountRepository.findAverageBalanceBySector();
        return AverageTop10BySector;
    }


}
