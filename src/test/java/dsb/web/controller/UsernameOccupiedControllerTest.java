package dsb.web.controller;

import dsb.web.controller.beans.PrintAccountDataBean;
import dsb.web.controller.rest.UsernameOccupiedController;
import dsb.web.domain.Customer;
import dsb.web.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UsernameOccupiedController.class)
class UsernameOccupiedControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerRepository customerRepository;




    private Customer customerTest;
    private List<Customer> listTest;

    @BeforeEach
    private void setUpData() {
        customerTest = new Customer();
        customerTest.setUsername("Berry");
        listTest = Arrays.asList(customerTest);
    }




    public UsernameOccupiedControllerTest() {
        super();
    }

    @Test
    @DisplayName("UsernameOccupiedControllerTest_pass")
    public void testPass() {

        Mockito.when(customerRepository.findAllByUsername(customerTest.getUsername())).
                thenReturn(listTest);

        try {
            MockHttpServletRequestBuilder getRequest =
                    MockMvcRequestBuilders.get("/username-occupied-check/Berry");
            ResultActions result = mockMvc.perform(getRequest);

            result.andExpect(status().isOk());

            MvcResult response = result.andReturn();
            MockHttpServletResponse m = response.getResponse();

            String expected = "true";
            String actual = m.getContentAsString();
            assertThat(expected).isEqualTo(actual);



        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @DisplayName("UsernameOccupiedControllerTest_fail")
    public void testFail() {

        Mockito.when(customerRepository.findAllByUsername(customerTest.getUsername())).
                thenReturn(listTest);

        try {
            MockHttpServletRequestBuilder getRequest =
                    MockMvcRequestBuilders.get("/username-occupied-check/Barry");
            ResultActions result = mockMvc.perform(getRequest);

            result.andExpect(status().isOk());

            MvcResult response = result.andReturn();
            MockHttpServletResponse m = response.getResponse();

            String expected = "false";
            String actual = m.getContentAsString();
            assertThat(expected).isEqualTo(actual);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }




}