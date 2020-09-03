package dsb.web.service;


import dsb.web.domain.Transaction;
import dsb.web.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountPageService {

    private TransactionRepository transactionRepository;

    @Autowired
    public AccountPageService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }




    public List<Transaction> findAllTransactionsByAccountID (int id) {
        return transactionRepository.findTransactionByAccounts(111);
    }



}
