package dsb.web.service;

import dsb.web.domain.*;
import dsb.web.repository.AccountRepository;
import dsb.web.repository.ConsumerAccountRepository;
import dsb.web.repository.CustomerRepository;
import dsb.web.repository.SMEAccountRepository;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

@Service
public class ApplicationStartupService {
    private AccountRepository accountRepository;
    private ConsumerAccountRepository consumerAccountRepository;
    private SMEAccountRepository smeAccountRepository;
    private TransactionService transactionService;
    private CustomerRepository customerRepository;
    private NewAccountService newAccountService;

    public ApplicationStartupService(AccountRepository accountRepository, ConsumerAccountRepository consumerAccountRepository, SMEAccountRepository smeAccountRepository, TransactionService transactionService, CustomerRepository customerRepository, NewAccountService newAccountService) {
        this.accountRepository = accountRepository;
        this.consumerAccountRepository = consumerAccountRepository;
        this.smeAccountRepository = smeAccountRepository;
        this.transactionService = transactionService;
        this.customerRepository = customerRepository;
        this.newAccountService = newAccountService;
    }

    public void setupBalances() {
        List<Account> accounts = accountRepository.findAll();
        for (Account account : accounts) {
            transactionService.updateBalance(account);
        }
    }

    public void createCustomers() throws IOException {
        File customers = new ClassPathResource("data/customer-data.csv").getFile();
        String separator = ";";

        try (Scanner scanner = new Scanner(customers)) {
            while (scanner.hasNext()) {
                String[] data = scanner.nextLine().split(separator);
                // Create address
                Address address = new Address(data[0], Integer.valueOf(data[1]), data [2], data[3], data[4].trim());

                // Create customer and persist in database
                Customer customer = new Customer(Integer.valueOf(data[10]), data[5], data[6], data[7], address, data[8], data[9], null);
                customerRepository.save(customer);

                // Create random amount of new accounts for customer
                for (int i = 0; i < createRandomInteger(3); i++) {
                    ConsumerAccount account = (ConsumerAccount) newAccountService.saveNewConsumerAccount(customer);
                    // Do initial transaction for account
                    transactionService.doInitialTransaction(account);
                }

                System.out.println("Klantnummer: " + customer.getUserID());


            }
        }
    }

    private int createRandomInteger(int bandwidth) {
        int random = (int) (Math.random() * bandwidth) + 1;
        return random;
    }


}


