package dsb.web;

import dsb.web.controller.AccountOverviewController;
import dsb.web.controller.AttributeMapping;
import dsb.web.controller.beans.LoginBean;
import dsb.web.domain.Customer;
import dsb.web.repository.CustomerRepository;
import dsb.web.service.ApplicationStartupService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.assertj.core.api.Assertions.*;

import java.util.List;

/*
* System test for sign in use case. Mocks both a valid and invalid login attempt and checks if the
* system handles both corretly by checking server responses, model and session attributes.
* Uses 'real' data from the database.
* */
@SpringBootTest
@AutoConfigureMockMvc
public class SignInUseCaseTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private ApplicationStartupService service;

    private Logger logger = LoggerFactory.getLogger(AccountOverviewController.class);
    List<Customer> customers;
    private Customer customer;
    private LoginBean loginBean;
    private final String template = "/sign-in";

    @BeforeEach
    void setup() {
        customers = customerRepository.findAll();
        customer = service.getRandomFromList(customers);
        loginBean = new LoginBean();
    }

    @Test
    void signInWithValidCredentials() throws Exception {
        // Fill loginBean with real customer data
        loginBean.setUsername(customer.getUsername());
        loginBean.setPassword(customer.getPassword());

        // Build request and get results
        MvcResult result = mockMvc.perform(post(template)
                .flashAttr("loginBean", loginBean))
                // Check if redirects to correct template
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/account_overview"))
                .andReturn();

        // Try to load session variable for logged in customer
        Customer sessionCustomer = null;
        try {
            sessionCustomer = (Customer) result.getRequest().getSession().getAttribute(AttributeMapping.LOGGED_IN_CUSTOMER);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

        // Check if correct customer is loaded into session
        assertThat(sessionCustomer).isEqualTo(customer);
    }

    @Test
    void signInWithInvalidCredentials() throws Exception {
        // Fill loginBean with real user but wrong password
        loginBean.setUsername(customer.getUsername());
        loginBean.setPassword("");

        // Build request and get results
        MvcResult result = mockMvc.perform(post(template)
                .flashAttr("loginBean", loginBean))
                // Check if responds with correct template
                .andExpect(status().isOk())
                .andExpect(view().name("sign-in"))
                // Check if model attribute indicating failed login is present
                .andExpect(model().attribute("loginFailed", "true"))
                .andReturn();

        // Try to load session variable for logged in customer
        Customer sessionCustomer = null;
        try {
            sessionCustomer = (Customer) result.getRequest().getSession().getAttribute(AttributeMapping.LOGGED_IN_CUSTOMER);
        } catch (NullPointerException e) {
            logger.debug("signInWithInvalidCredentials Test: No session attribute found (good!)");
        }

        // Verify that no customer is loaded in session
        assertThat(sessionCustomer).isNull();
    }
}
