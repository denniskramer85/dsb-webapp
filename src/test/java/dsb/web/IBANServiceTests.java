package dsb.web;

import dsb.web.domain.Iban;
import dsb.web.service.IbanService;
import org.junit.jupiter.api.Test;

import static dsb.web.service.IbanService.*;
import static org.junit.jupiter.api.Assertions.*;

class IBANServiceTests {

    @Test
    void modelToString(){
        String expected = "NL88INGB0008829995";
        Iban iban = new Iban("NL",88,"INGB", 8829995);
        assertEquals(expected, iban.toString());
    }

    @Test
    void modelToNumericalString(){
        String expected = "182316110008829995232188";
        Iban iban = new Iban("NL",88,"INGB", 8829995);
        System.out.println(expected);
        System.out.println(iban.toNumericalString());
        assertEquals(expected, iban.toNumericalString());
    }


//    @Test
//    void checkIbanBuilderLength(){
//        int expected = 18;
//        int actual = IBANService.IBANBuilder().length();
//        assertEquals(expected,actual);
//    }
//    @Test
//    void checkIbanCountryCode(){
//        String IBAN = IBANService.IBANBuilder();
//        assertTrue(IBAN.startsWith("NL"));
//    }
//
//    @Test
//    void checkIbanBankCode(){
//        String IBAN = IBANService.IBANBuilder();
//        System.out.println(IBAN);
//        assertEquals(IBAN.substring(4, 8), "DSBB");
//    }


    @Test
    void checkStringToIban(){
        String expected = "NL88INGB0008829995";
        Iban iban = IbanService.stringToIBAN(expected);
        String actual = iban.toString();
        assertEquals(expected,actual);
    }

    @Test
    void checkIbanFormatr1(){
        //String expected = "NL88iNGB0008829995";
        String expected = "182316110008829995232188";
        Iban iban = new Iban("NL",88,"INGB", 8829995);
        String actual = iban.toNumericalString();
        assertEquals(expected,actual);
    }

    @Test
    void checkIbanValidifier2(){
        Iban iban = new Iban("NL",88,"INGB", 8829995);
        assertTrue(verifyIban(iban));
    }

    @Test
    void checkIbanValidifier3(){
        Iban iban = new Iban("NL",45,"INGB", 2141848644);
        assertTrue(verifyIban(iban));
    }

    @Test
    void test(){
        Iban iban = IbanService.randIBAN();
        System.out.println(iban);
        assertTrue(verifyIban(iban));
    }








}



