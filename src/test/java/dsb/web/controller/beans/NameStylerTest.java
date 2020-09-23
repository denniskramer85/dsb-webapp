package dsb.web.controller.beans;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class NameStylerTest {

    @Autowired
    SignUpBean signUpBean;

    @BeforeEach
    public void createData() {
        this.signUpBean = new SignUpBean();
    }


    @Test
    @DisplayName("NameStylerTest")
    void nameStyler() {

        signUpBean.setInitials("s@5.,DD1Q");
        signUpBean.setInserts("van der");
        signUpBean.setSurname("kroechten");

        signUpBean.nameStyler();

        String actual = signUpBean.toString();
        String expected = "SignUpBean{surname='Kroechten', inserts='van der', initials='S.D.D.Q.'}";

        assertThat(actual).isEqualTo(expected);
    }


}