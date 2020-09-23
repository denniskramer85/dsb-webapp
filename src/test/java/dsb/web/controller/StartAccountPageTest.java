package dsb.web.controller;

import dsb.web.controller.beans.PrintAccountDataBean;
import dsb.web.domain.Account;
import dsb.web.domain.SMEAccount;
import dsb.web.service.AccountPageService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(AccountPageController.class)
class StartAccountPageTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AccountPageService accountPageService;


    public StartAccountPageTest() {
        super();
    }

    @Test
    @DisplayName("StartAccountPageTest")
    public void startTest() {
        Account account = new SMEAccount();

        //dummy bean
        List<String> testList = Arrays.asList("test", "test");
        PrintAccountDataBean expected = new PrintAccountDataBean(
                "test", "test", "test",
                "test", "test", "test", testList);

        Mockito.when(accountPageService.makePrintAccountDataBean(account)).thenReturn(expected);


        try {
            MockHttpServletRequestBuilder getRequest =
                    MockMvcRequestBuilders.get("/accountPage")
                            .sessionAttr("selectedAccountSession", account);
            ResultActions result = mockMvc.perform(getRequest);

            //test A
            result.andExpect(status().isOk());

            //test B
            MvcResult response = result.andReturn();
            Map<String, Object> map = response.getModelAndView().getModel();
            PrintAccountDataBean actual = (PrintAccountDataBean) map.get("printAccountDataBean");


            assertThat(expected).isEqualTo(actual);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }






}