package dsb.web.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


class getHoldersStringTest {

    private ConsumerAccount account;



    @BeforeEach
    public void createData() {
        this.account = new ConsumerAccount();

        List<Customer> listHolders = Arrays.asList(
            new Customer("J.J.", "de", "Boer"),
            new Customer("A.L.S.", null, "Visser"),
            new Customer("B.", "ter", "Schouw"),
            new Customer("C.", "van", "Loen")
            );

        for (Customer c : listHolders) {
            account.addHolder(c);
        }

    }

    @Test
    @DisplayName("0")
    void getHoldersString_max0() {
        String actual = account.getHoldersString(0);
        String expected = " - ";
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("1")
    void getHoldersString_max1() {
        String actual = account.getHoldersString(1);
        String expected = "J.J. de Boer e.a.";
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("2")
    void getHoldersString_max2() {
        String actual = account.getHoldersString(2);
        String expected = "J.J. de Boer, C. van Loen e.a.";
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("3")
    void getHoldersString_max3() {
        String actual = account.getHoldersString(3);
        String expected = "J.J. de Boer, C. van Loen, B. ter Schouw e.a.";
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("3_fail")
    void getHoldersString_max3_fail() {
        String actual = account.getHoldersString(3);
        String expected = "J.J. de Boer, C. van Loen, B. ter Schouw, A.L.S. Visser";
        assertThat(actual).isNotEqualTo(expected);
    }

    @Test
    @DisplayName("4")
    void getHoldersString_max4() {
        String actual = account.getHoldersString(4);
        String expected = "J.J. de Boer, C. van Loen, B. ter Schouw, A.L.S. Visser";
        assertThat(actual).isEqualTo(expected);
    }




}