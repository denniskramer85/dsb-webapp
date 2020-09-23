package dsb.web.service;

import dsb.web.domain.Iban;
import dsb.web.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
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

    public AccountRepository accountRepository;

    public IbanService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public static int ibanLength(){
        return (IBAN_COUNTRY_CODE.length() +
                IBAN_CHECKSUM_DEFAULT_LENGTH +
                IBAN_BANK_CODE.length() +
                IBAN_ACCOUNT_NUMBER_LENGTH);
    }

    public static Iban randIBAN(){
        Iban iban = new Iban(IBAN_COUNTRY_CODE,0,IBAN_BANK_CODE,randomAccountNumber());
        int checksum = calculateMod(iban.toString());
        iban.setCheckSum(98-checksum);
        return iban;
    }

    public Iban getUniqueIban(){
        Iban iban = IbanService.randIBAN();
        while (accountRepository.existsByAccountNo(iban.toString()))
            iban = IbanService.randIBAN();
        return iban;
    }

    private static long randomAccountNumber() {
        //Generates random ints and appends to String
        StringBuilder accountNumber = new StringBuilder(START_NUMBER_ACCOUNT);
        Random r = new Random();
        for (int i = 0; i < IBAN_ACCOUNT_NUMBER_LENGTH - START_NUMBER_ACCOUNT.length(); i++)
            accountNumber.append(r.nextInt(10 - 1 + 1));
        return Long.parseLong(accountNumber.toString());
    }

    public static Iban stringToIBAN (String iban){
        //Only works for correct ibans in Dutch formatting
        iban =  iban.toUpperCase();
        String countryCode = iban.substring(0,2);
        int checkSum = Integer.parseInt(iban.substring(2,4));
        String bankCode = iban.substring(4,8);
        Long accountNumber = Long.parseLong(iban.substring(8));
        return new Iban(countryCode,checkSum,bankCode,accountNumber);
    }

    public static String stringToNumericalString(String str) {
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


    public static boolean verifyIban(String ibanStr){
        return verifyIban(ibanStr,false);
    }

    /**
     * Verifies Iban using three different strategies
     * 1. Iban length
     * 2. validity of characters
     * 3. Calculates if Iban's checksum is valid
     *
     * @param ibanStr
     * @return boolean Set to true for more detailed terminal output
     */
    public static boolean verifyIban(String ibanStr, boolean debug) {

        if (checkValidityIbanLength(ibanStr) &&
                checkValidityIbanDigits(ibanStr) &&
                calculateMod(ibanStr)==VALID_REMAINDER_MOD_97)
            return true;
        else if (debug){
            System.out.println("input: " + ibanStr);
            System.out.println("ibanToStr: " + stringToIBAN(ibanStr).toString());
            System.out.println("ibanToStr: " + stringToIBAN(ibanStr).toNumericalString());
            System.out.println("length: " + checkValidityIbanLength(ibanStr));
            System.out.println("digits: " + checkValidityIbanDigits(ibanStr));
            System.out.println("mod: " + (calculateMod(ibanStr)==VALID_REMAINDER_MOD_97));
            System.out.println("mod result: " + calculateMod(ibanStr));
        }
        return false;
    }


    private static int calculateMod(String ibanStr) {
        long total = 0;
        String str = stringToIBAN(ibanStr).toNumericalString();
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

    private static boolean checkValidityIbanLength(String ibanStr){
        if (ibanStr.length() != ibanLength()){
            return false;
        } else {
            return true;
        }
    }

    private static boolean checkValidityIbanDigits(String ibanStr){
        for (int i = 0; i < ibanStr.length(); i++) {
            if (ibanStr.charAt(i) < ALPHABET_HIGHER_BOUND){
                return false;
            }
        }
        return true;
    }
}
