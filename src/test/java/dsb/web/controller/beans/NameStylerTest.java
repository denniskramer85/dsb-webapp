package dsb.web.controller.beans;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;


class NameStylerTest {

    SignUpBean signUpBean;

    @BeforeEach
    public void createData() {
        this.signUpBean = new SignUpBean();
    }


    @Test
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