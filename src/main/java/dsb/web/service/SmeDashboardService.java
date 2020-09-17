package dsb.web.service;


import dsb.web.domain.*;
import dsb.web.repository.TokenPaymentMachineRepository;
import dsb.web.repository.SMEAccountRepository;
import dsb.web.repository.SectorRepository;
import dsb.web.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SmeDashboardService {
    private SMEAccountRepository smeAccountRepository;
    private TransactionRepository transactionRepository;
    private SectorRepository sectorRepository;
    private TokenPaymentMachineRepository tokenPaymentMachineRepository;

    @Autowired
    public SmeDashboardService(SMEAccountRepository smeAccountRepository, TransactionRepository transactionRepository, SectorRepository sectorRepository, TokenPaymentMachineRepository tokenPaymentMachineRepository) {
        this.smeAccountRepository = smeAccountRepository;
        this.transactionRepository = transactionRepository;
        this.sectorRepository = sectorRepository;
        this.tokenPaymentMachineRepository = tokenPaymentMachineRepository;
    }

    public SmeDashboardService() {
    }


    public List<Transaction> getTop10SmeTransaction() {
        List<Transaction> transactionList = transactionRepository.findAll();
        return transactionList;
    }



    public List<SMEAccount> getTop10bySmeBalance() {
        List<SMEAccount> smeAccountsList = smeAccountRepository.findTop10ByOrderByBalanceDesc();
        System.out.println();
        return smeAccountsList;
    }

    public List<TokenPaymentMachine> getAllByLinkRequest() {
            List<TokenPaymentMachine> listRequest = tokenPaymentMachineRepository.findAll();
            List<TokenPaymentMachine> newRequests = new ArrayList<>();
            for(TokenPaymentMachine token : listRequest) {
                if(token.getSecurityCode() == 0) {
                    newRequests.add(token);
                }
            }

        return listRequest;
    // bootstrapalert toevoegen
    }

    public Map<Sector, Integer> averageTop10BySector() {
        Map<Sector, Integer> averageTop10BySector = new HashMap<>();
        for (Sector sec : sectorRepository.findAll()) {
           int average = 0;
           List<SMEAccount> list = smeAccountRepository.findAllByCompany_Sector(sec);
           for (SMEAccount acc : list) {
               average += acc.getBalance();
           }
           if (list.size() > 0)
               average = average/list.size();
           averageTop10BySector.put(sec,average);
        }
        return averageTop10BySector;

    }





}
