package dsb.web.controller;

import dsb.web.domain.Account;
import dsb.web.domain.Customer;
import dsb.web.domain.User;
import dsb.web.service.AccountOverviewService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

/*
* Integration test to see if controller AccountOverviewController is properly allowing requests
* Tests response with loggedInCustomer session attribute
* Tests if correct html template is returned
* */
@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = AccountOverviewController.class)
public class AccountOverviewControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private AccountOverviewService accountOverviewService;
    private User user;
    private Account account;

    @BeforeEach
    void setup() {
        user = new Customer(12345678, "H", "van", "Thienen", null, "huub", "thienen", null);
    }

    @Test
    void allowRequest() {
        try {
            mockMvc.perform(get("/account_overview").sessionAttr(AttributeMapping.LOGGED_IN_CUSTOMER, user))
                    .andExpect(view().name("account_overview"))
                    .andExpect(status().isOk());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


