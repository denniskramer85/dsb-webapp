package dsb.web.controller;

import dsb.web.controller.beans.SignUpBean;
import dsb.web.service.SignupService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SignUpController.class)
class handlerSignUpTest {


    @Autowired
    private MockMvc mockMvc;

    @MockBean
    SignupService signupService;

    public handlerSignUpTest() {
    }



    @Test
    @DisplayName("handlerSignUpTest")
    void handlerSignUp() {

        try {
            MockHttpServletRequestBuilder getRequest =
                    MockMvcRequestBuilders.get("/sign-up").
                            sessionAttr("signUpBeanSession", new SignUpBean());
            ResultActions result = mockMvc.perform(getRequest);
            result.andDo(print()).andExpect(status().isOk());
        } catch (Exception e) {
            e.printStackTrace();
        }





    }
}