package dsb.web.service;

import dsb.web.domain.Iban;

import java.util.Random;

public class IbanService {
    private static final String IBAN_COUNTRY_CODE = "NL";
    private static final int IBAN_CHECKSUM_DEFAULT = 00;
    private static final String IBAN_BANK_CODE = "DSBB";
    private static final int IBAN_CHECKSUM_DEFAULT_LENGTH = 2;
    private static final int IBAN_ACCOUNT_NUMBER_LENGTH = 10;
    private static final int ALPHABET_LOWER_BOUND = 10;
    private static final int ALPHABET_HIGHER_BOUND = 35;
    private static final int VALID_REMAINDER_MOD_97 = 1;
    private static final String START_NUMBER_ACCOUNT = "000";
    private static final long MAX = 999999999;
    private static final int MODULUS = 97;

    public static int ibanLength(){
        return (IBAN_COUNTRY_CODE.length() +
                IBAN_CHECKSUM_DEFAULT_LENGTH +
                IBAN_BANK_CODE.length() +
                IBAN_ACCOUNT_NUMBER_LENGTH);
    }

    public static Iban randIBAN(){
        Iban iban = new Iban(IBAN_COUNTRY_CODE,0,IBAN_BANK_CODE,randomAccountNumber());
        int checksum = calculateMod(iban);
        iban.setCheckSum(98-checksum);
        return iban;
    }

    private static int randomAccountNumber() {
        //Generates random ints and appends to String
        StringBuilder accountNumber = new StringBuilder(START_NUMBER_ACCOUNT);
        Random r = new Random();
        for (int i = 0; i < IBAN_ACCOUNT_NUMBER_LENGTH - START_NUMBER_ACCOUNT.length(); i++)
            accountNumber.append(r.nextInt(10 - 1 + 1));
        return Integer.parseInt(accountNumber.toString());
    }

    public static Iban stringToIBAN (String iban){
        //Only works for correct ibans in Dutch formatting
        iban =  iban.toUpperCase();
        String countryCode = iban.substring(0,2);
        int checkSum = Integer.parseInt(iban.substring(2,4));
        String bankCode = iban.substring(4,8);
        int accountNumber = Integer.parseInt(iban.substring(8));
        return new Iban(countryCode,checkSum,bankCode,accountNumber);
    }

    public static String stringToIntegerString(String str) {
        String result = "";
        for (int i = 0; i < str.length(); i++) {
            int chr = Character.getNumericValue(str.charAt(i));
            if (chr >= ALPHABET_LOWER_BOUND && chr <= ALPHABET_HIGHER_BOUND) {
                result += Integer.toString(chr);
            } else {
                result += chr;
            }
        }
        return result;
    }

    public static boolean verifyIban(Iban iban) {
        if (checkValidityIbanLength(iban) &&
                checkValidityIbanDigits(iban) &&
                calculateMod(iban)==VALID_REMAINDER_MOD_97)
            return true;
        return false;
    }


    private static int calculateMod(Iban iban) {
        long total = 0;
        String str = iban.toNumericalString();
        for (int i = 0; i < str.length(); i++) {
            int numericValue = Character.getNumericValue(str.charAt(i));
            if (numericValue < 0 || numericValue > ALPHABET_HIGHER_BOUND) {
                throw new IllegalArgumentException("Invalid character -   s: " + str.charAt(i) + "  n: " + numericValue);
            }
            total = (numericValue > 9 ? total * 100 : total * 10) + numericValue;
            if (total > MAX) {
                total = (total % MODULUS);
            }
        }
        int result = (int) (total % MODULUS);
        return result;
    }

    private static boolean checkValidityIbanLength(Iban iban){
        if (iban.toString().length() != ibanLength()){
            return false;
        } else {
            return true;
        }
    }

    private static boolean checkValidityIbanDigits(Iban iban){
        for (int i = 0; i < iban.toString().length(); i++) {
            if (iban.toString().charAt(i) < ALPHABET_HIGHER_BOUND){
                return false;
            }
        }
        return true;
    }
}
