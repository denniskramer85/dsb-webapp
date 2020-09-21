package dsb.web.controller;

import dsb.web.controller.beans.PrintAccountDataBean;
import dsb.web.domain.Account;
import dsb.web.domain.ConsumerAccount;
import dsb.web.domain.SMEAccount;
import dsb.web.service.AccountPageService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
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
    public void startTest() {
        Account account = new SMEAccount();

        List<String> ls = Arrays.asList("1", "2");


        PrintAccountDataBean padb = new PrintAccountDataBean(
                "SMEAccount", "1", "X BV",
                "Piet en Kees", "100,10", "XXX", ls);

        System.out.println(padb);


        Mockito.when(accountPageService.makePrintAccountDataBean(account)).thenReturn(padb);

        //wel gevuld
        PrintAccountDataBean xx = accountPageService.makePrintAccountDataBean(account);
        System.out.println("dit is de xx: " + xx);


        try {


            MockHttpServletRequestBuilder getRequest =
                    MockMvcRequestBuilders.get("/accountPage");
            ResultActions result = mockMvc.perform(getRequest);
            result.andDo(print()).andExpect(status().isOk());




        } catch (Exception e) {
            e.printStackTrace();
        }


    }






}