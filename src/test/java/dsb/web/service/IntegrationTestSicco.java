package dsb.web.service;

import dsb.web.controller.beans.PrintAccountDataBean;
import dsb.web.domain.*;
import dsb.web.domain.domain_helpers.CreateAccountHoldersString;
import dsb.web.repository.TransactionRepository;
import dsb.web.service.service_helpers.PrintTransactionsForAccountPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class IntegrationTestSicco {


    @Autowired
    private AccountPageService accountPageService;

    @Autowired
    private PrintTransactionsForAccountPage printTransactionsForAccountPage;


    @MockBean
    private CreateAccountHoldersString createAccountHoldersString;

    @MockBean
    private TransactionRepository transactionRepository;


    public IntegrationTestSicco() {
    }

    /**tests integration of AccountPageService and printTransactionsForAccountPage with mocked repository
     * checks that result of interaction (a PrintAccountDataBean) is filled correctly**/
    @Test
    @DisplayName("makePrintAccountDataBean_integration")
    void makePrintAccountDataBean() {

        //set holder list (needed for account)
        Customer c1 = new Customer();
        c1.setSurname("alfen");
        c1.setInitials("a.");
        Customer c2 = new Customer();
        c2.setSurname("beren");
        c2.setInitials("b.");
        List<Customer> clist = Arrays.asList(c1,c2);

        //setup account
        Account a0 = new ConsumerAccount(100, "reknr 123", 100.1, clist);

        //setup transaction + list
        LocalDateTime ldt = LocalDateTime.of(2017, Month.FEBRUARY,3,6,30,40,50000);
        Transaction t = new Transaction(1, a0, a0, 100.1, "hoihoi", ldt);
        List<Transaction> tlist = Arrays.asList(t, t, t, t, t, t, t);

        Mockito.when(createAccountHoldersString.createAccountHoldersString()).thenReturn("h1 h2 h3");
        Mockito.when(transactionRepository.findTopNTransactionByAccounts(a0.getAccountID(), 10)).thenReturn(tlist);

        PrintAccountDataBean actual = accountPageService.makePrintAccountDataBean(a0);

        //assert that fields of integration (PrintAccountDataBean) is filled correctly
        assertThat(actual.getTypeAccount()).isEqualTo("ConsumerAccount");
        assertThat(actual.getAccountNo()).isEqualTo("reknr 123");
        assertThat(actual.getCompanyName()).isEqualTo(" - ");
        assertThat(actual.getHolderNames()).isEqualTo("a. alfen, b. beren");
        assertThat(actual.getBalance()).isEqualTo("100,10");
        assertThat(actual.getTimestamp()).isNotNull();
        assertThat(actual.getTransactionStrings().size()).isGreaterThan(0);
    }




}