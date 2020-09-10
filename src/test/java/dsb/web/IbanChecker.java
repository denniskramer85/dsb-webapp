package dsb.web;

import dsb.web.domain.Iban;
import dsb.web.service.IbanService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class IbanChecker {

    @Test
    void test1(){
        boolean debug = false;
        String iban1 = "NL88INGB0008829995";
        String iban2 = "NL59ABNA7691116985";
        String iban3 = "NL46ABNA8871007662";
        String iban4 = "NL74RABO7023532722";
        String iban5 = "NL92INGB7798392807";
        String iban6 = "NL96INGB7659536168";
/*        System.out.println(IbanService.verifyIban(iban4, true));
        System.out.println(IbanService.stringToIBAN("NL74RABO7023532722").toNumericalString());*/
        assertTrue(IbanService.verifyIban(iban1, debug));
        assertTrue(IbanService.verifyIban(iban2, debug));
        assertTrue(IbanService.verifyIban(iban3, debug));
        assertTrue(IbanService.verifyIban(iban4, debug));
        assertTrue(IbanService.verifyIban(iban5, debug));
        assertTrue(IbanService.verifyIban(iban6, debug));
    }
}