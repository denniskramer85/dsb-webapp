package dsb.web.controller.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import dsb.web.controller.beans.TransferNameBean;
import dsb.web.domain.Account;
import dsb.web.domain.ConsumerAccount;
import dsb.web.repository.AccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import dsb.web.domain.Customer;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MvcResult;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;



/*
* Integration test to check if REST controller TransferNameController behaves as expected
* Tests basic response acceptance and denial and all three possible response bodies
* */

@WebMvcTest(controllers = TransferNameController.class)
public class TransferNameControllerTest {
    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;
    @MockBean
    AccountRepository accountRepository;
    Account account;
    Customer customer;

    @BeforeEach
    void setup() {
        customer = new Customer(12345678, "H.", "van", "Thienen", null, "huub", "thienen", null);
        account = new ConsumerAccount(1, "NL40DSBB0684805332", 100.0, Arrays.asList(customer));
    }

    @Test
    void allowRequest() throws Exception{
        mockMvc.perform(get("/transfer-name-checks/NL40DSBB0684805332/H. van Thienen"))
                .andExpect(status().isOk());
    }

    @Test
    void missingParameters() throws Exception{
        mockMvc.perform(get("/transfer-name-checks"))
                .andExpect(status().is4xxClientError());
    }

    @Test
    void nameMatches() throws Exception {
        when(accountRepository.findAccountByAccountNo("NL40DSBB0684805332")).thenReturn(account);
        TransferNameBean expectedBean = new TransferNameBean(true, null);

        MvcResult mvcResult = mockMvc.perform(get("/transfer-name-checks/NL40DSBB0684805332/H. van Thienen")).andReturn();
        String parsedResult = mvcResult.getResponse().getContentAsString();

        assertThat(parsedResult).isEqualToIgnoringWhitespace(objectMapper.writeValueAsString(expectedBean));
    }

    @Test
    void nameNotMatching() throws Exception {
        when(accountRepository.findAccountByAccountNo("NL40DSBB0684805332")).thenReturn(account);
        TransferNameBean expectedBean = new TransferNameBean(false, "H. van Thienen");

        MvcResult mvcResult = mockMvc.perform(get("/transfer-name-checks/NL40DSBB0684805332/R. de Boer")).andReturn();
        String parsedResult = mvcResult.getResponse().getContentAsString();

        assertThat(parsedResult).isEqualToIgnoringWhitespace(objectMapper.writeValueAsString(expectedBean));
    }

    @Test
    void accountNotMatching() throws Exception {
        when(accountRepository.findAccountByAccountNo("NL40DSBB0684805332")).thenReturn(account);
        TransferNameBean expectedBean = new TransferNameBean(false, null);

        MvcResult mvcResult = mockMvc.perform(get("/transfer-name-checks/NL40DSBB0684805335/M. Oey")).andReturn();
        String parsedResult = mvcResult.getResponse().getContentAsString();

        assertThat(parsedResult).isEqualToIgnoringWhitespace(objectMapper.writeValueAsString(expectedBean));
    }
}
