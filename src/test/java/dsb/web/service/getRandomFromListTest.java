package dsb.web.service;

import dsb.web.domain.Iban;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.assertj.core.api.Assertions.*;

/*
 * Test if generic method getRandomFromList is able to extract an object from any given list of objects
 * Test if generic method getRandomFromList reaches list edges
 * */
public class getRandomFromListTest {
    private ApplicationStartupService applicationStartupService = new ApplicationStartupService();
    List<Integer> integerList;
    List<String> stringList;
    List<Iban> ibanList;

    @BeforeEach
    void setup() {
        integerList = Arrays.asList(new Integer[] { 1000, 2000, 3000, 4000, 5000, 6000} );
        stringList = Arrays.asList(new String[] { "String 1", "String 2", "String 3", "String 4" });
        ibanList = Arrays.asList(new Iban("NL", 40, "DSBB", (long)12345678),
                new Iban("NL", 52, "DSBB", (long)12345678),
                new Iban("NL", 61, "DSBB", (long)12345678));
    }

    @Test
    void correctObjectExtraction() {
        Integer randomInteger = applicationStartupService.getRandomFromList(integerList);
        String randomString = applicationStartupService.getRandomFromList(stringList);
        Iban randomIban = applicationStartupService.getRandomFromList(ibanList);

        assertThat(integerList.contains(randomInteger));
        assertThat(stringList.contains(randomString));
        assertThat(ibanList.contains(randomIban));
    }

    @Test
    void reachingEdges() {
        List<Integer> selectedIntegers = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            applicationStartupService.getRandomFromList(integerList);
        }

        assertThat(selectedIntegers.contains(1000));
        assertThat(selectedIntegers.contains(2000));
        assertThat(selectedIntegers.contains(3000));
        assertThat(selectedIntegers.contains(4000));
        assertThat(selectedIntegers.contains(5000));
        assertThat(selectedIntegers.contains(6000));
    }
}

