package dsb.web.controller;

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

/*
* Integration test to see if controller AccountOverviewController is proply allowing requests
* Tests response with loggedInCustomer session attribute
* */
@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = AccountOverviewController.class)
public class AccountOverviewControllerTest {
    @Autowired
    MockMvc mockMvc;
    @MockBean
    AccountOverviewService accountOverviewService;
    User user;

    @BeforeEach
    void setup() {
        user = new Customer(12345678, "H", "van", "Thienen", null, "huub", "thienen", null);
    }

    @Test
    void allowRequest() throws Exception {
        mockMvc.perform(get("/account_overview").sessionAttr(AttributeMapping.LOGGED_IN_CUSTOMER, user))
                .andExpect(status().isOk());
    }
}


