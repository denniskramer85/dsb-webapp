package dsb.web.service;

import dsb.web.domain.Customer;
import dsb.web.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.web.bind.annotation.SessionAttributes;

import java.lang.reflect.Array;
import java.util.*;

@Service
public class NewAccountService {
    private static final int IBAN_LENGTH = 20;
    private static final String IBAN_START = "NL88DSBB";
    public static final List<String> ACCOUNT_TYPES = Arrays.asList("particulier","zakelijk");


    public NewAccountService() {
    }

    public String buildIBAN(){
        /*Generate valid IBAN in string format*/
        String result = IBAN_START;
        result += "0000";
        Random rand = new Random();
        while (result.length() < 20){
            result += rand.nextInt();
        }
        return result;
    }
}
