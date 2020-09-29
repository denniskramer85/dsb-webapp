package dsb.web.service;

import dsb.web.controller.AccountOverviewController;
import dsb.web.domain.*;
import dsb.web.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class ApplicationStartupService {
    private Logger logger = LoggerFactory.getLogger(AccountOverviewController.class);
    private static final int ACCOUNT_MANAGER_ID = 45;
    private static final double TRANSACTION_LOW = 2.99;
    private static final double TRANSACTION_HIGH = 800.0;
    private AccountRepository accountRepository;
    private TransactionService transactionService;
    private TransactionRepository transactionRepository;
    private CustomerRepository customerRepository;
    private NewAccountService newAccountService;
    private EmployeeRepository employeeRepository;
    private SectorRepository sectorRepository;
    private CompanyRepository companyRepository;

    @Autowired
    public ApplicationStartupService(AccountRepository accountRepository, TransactionService transactionService, TransactionRepository transactionRepository, CustomerRepository customerRepository, NewAccountService newAccountService, EmployeeRepository employeeRepository, SectorRepository sectorRepository, CompanyRepository companyRepository) {
        this.accountRepository = accountRepository;
        this.transactionService = transactionService;
        this.transactionRepository = transactionRepository;
        this.customerRepository = customerRepository;
        this.newAccountService = newAccountService;
        this.employeeRepository = employeeRepository;
        this.sectorRepository = sectorRepository;
        this.companyRepository = companyRepository;
    }

    public ApplicationStartupService() {
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
        int accountsGenerated = 0;

        logger.debug("Generating 5k customers with 1-3 accounts...");
        long startTime = System.currentTimeMillis();

        try (Scanner scanner = new Scanner(customers)) {
            while (scanner.hasNext()) {
                // Split each csv line into string array
                String[] data = scanner.nextLine().split(separator);

                // Create address
                Address address = new Address(data[0], Integer.valueOf(data[1]), data [2], data[3], data[4].trim());

                // Create customer and persist in database
                Customer customer = new Customer(Integer.valueOf(data[10]), data[5].trim(), data[6], data[7], address, data[8], data[9], null);
                customerRepository.save(customer);

                // Create random amount of new accounts for customer
                for (int i = 0; i < createRandomInteger(3, 1); i++) {
                    Account account = newAccountService.saveNewConsumerAccount(customer);
                    // Do initial transaction for account
                    transactionService.doInitialTransaction(account);
                    accountsGenerated++;
                }
                logger.debug("Customer " + customer.printWholeName() + "(" + customer.getUserID() + ") " +  "created");
            }
        }
        long endTime = System.currentTimeMillis();
        logger.debug("5k customers and " + accountsGenerated + " consumer accounts generated in " +
                ((endTime - startTime) / 1000) + " seconds.");
    }

    public void createCompanies() throws IOException {
        // Open file, set start variables
        File companies = new ClassPathResource("data/company-data.csv").getFile();
        String separator = ";";
        int accountsGenerated = 0;

        logger.debug("Generating 1k companies with 1 account...");
        long startTime = System.currentTimeMillis();

        // Get needed entities
        Employee accountManager = employeeRepository.findById(ACCOUNT_MANAGER_ID).get();
        List<Sector> sectors = sectorRepository.findAll();
        List<Customer> customers = customerRepository.findAll();

        try (Scanner scanner = new Scanner(companies)) {
            while (scanner.hasNext()) {
                // Split each csv line into string array
                String[] data = scanner.nextLine().split(separator);

                // Find random customer
                Customer customer = getRandomFromList(customers);

                // Create new company from pulled entities and persist in database
                Company company = new Company(data[2], data[0], data[1], accountManager, getRandomFromList(sectors));
                companyRepository.save(company);

                // Create new SMEAccount for company and random holder
                SMEAccount smeAccount = (SMEAccount) newAccountService.saveNewSMEAccount(customer, company);

                // Do initial transaction for generated account
                transactionService.doInitialTransaction(smeAccount);
                accountsGenerated++;

                logger.debug("Company " + company.getName() + " (" + company.getCompanyId() + ")" + " created.");
            }
        }
        long endTime = System.currentTimeMillis();
        logger.debug("1k companies and " + accountsGenerated + " accounts generated in " +
                ((endTime - startTime) / 1000) + " seconds.");
    }

    public void TransactionGenerator(long numberOfTransactions) throws IOException {
        // Open file, load messages in variable
        File messagesFile = new ClassPathResource("data/transaction-data.csv").getFile();
        List<String> messages = new ArrayList<>();
        long transactionsGenerated;

        try (Scanner scanner = new Scanner(messagesFile)) {
            while (scanner.hasNext()) {
                messages.add(scanner.nextLine());
            }
        }

        // Get needed entities
        List<Account> accounts = accountRepository.findAll();

        logger.debug("Generating " + numberOfTransactions + " transactions...");
        long startTime = System.currentTimeMillis();

        // For given number, create a transaction and save to database
        for (transactionsGenerated = 0; transactionsGenerated < numberOfTransactions; transactionsGenerated++) {
            // Generate two random accounts, make sure they are not the same
            Account account1 = null;
            Account account2 = null;
            boolean sameAccount = true;
            while (sameAccount == true) {
                account1 = getRandomFromList(accounts);
                account2 = getRandomFromList(accounts);
                sameAccount = account1.equals(account2);
            }

            // Make  new transaction object and fill fields
            Transaction transaction = new Transaction();
            transaction.setDebitAccount(account1);
            transaction.setCreditAccount(account2);
            transaction.setTransactionAmount(createRandomDouble(TRANSACTION_LOW, TRANSACTION_HIGH));
            transaction.setMessage(getRandomFromList(messages));
            transaction.setTransactionTimestamp(LocalDateTime.now());

            // Persist to database
            transactionRepository.save(transaction);

            logger.debug("Transaction generated: " + transaction.getTransactionID());
        }

        long endTime = System.currentTimeMillis();
        logger.debug(numberOfTransactions + " transactions generated in " +
                ((endTime - startTime) / 1000) + " seconds.");
    }

    // Generic method to return a random item from a list of any size
    public <E> E getRandomFromList(List<E> list) {
        return list.get(createRandomInteger(list.size(), 0));
    }

    public double createRandomDouble(double low, double high) {
        double random = new Random().nextDouble();
        return low + (random * (high - low));
    }

    public int createRandomInteger(int bandwidth, int start) {
        int random = (int) (Math.random() * bandwidth) + start;
        return random;
    }

}


