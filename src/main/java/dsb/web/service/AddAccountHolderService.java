package dsb.web.service;

import dsb.web.controller.AttributeMapping;
import dsb.web.controller.beans.ConfirmBean;
import dsb.web.domain.Account;
import dsb.web.domain.AccountHolderToken;
import dsb.web.domain.ConsumerAccount;
import dsb.web.domain.Customer;
import dsb.web.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@SessionAttributes(AttributeMapping.LOGGED_IN_CUSTOMER)
@Service
public class AddAccountHolderService {
    private ConsumerAccountRepository consumerAccountRepository;
    private SMEAccountRepository smeAccountRepository;
    private AccountRepository accountRepository;
    private AccountHolderTokenRepository accountHolderTokenRepository;
    private CustomerRepository customerRepository;

    @Autowired
    public AddAccountHolderService(ConsumerAccountRepository consumerAccountRepository,
                                   SMEAccountRepository smeAccountRepository,
                                   AccountRepository accountRepository,
                                   AccountHolderTokenRepository accountHolderTokenRepository,
                                   CustomerRepository customerRepository) {
        this.accountHolderTokenRepository = accountHolderTokenRepository;
        this.consumerAccountRepository = consumerAccountRepository;
        this.smeAccountRepository = smeAccountRepository;
        this.accountRepository = accountRepository;
        this.customerRepository = customerRepository;
    }

    public AccountHolderToken createAddAccountHolderToken(Customer userToAdd, Account accountToAddTo, String token) {
        return accountHolderTokenRepository.save(new AccountHolderToken(userToAdd,accountToAddTo,token));
    }

    /**
     *
     * Verifies candidate account holder. If not, a message is returned. Else, method returns 'null'
     *
     * @param username
     * @param loggedInCustomer
     * @param account
     * @return
     */
    public String checkUsernameValidity(String username, Customer loggedInCustomer, Account account){
        Optional<Customer> OptionalCustomer = customerRepository.findOneByUsername(username);
        if (!OptionalCustomer.isPresent()){
            return "Gebruikersnaam niet geldig, probeer opnieuw";
        }else {
            Customer customer = OptionalCustomer.get();
            if(customer.getCustomerID() == loggedInCustomer.getCustomerID()){
                return "Voer hier de gebruikersnaam van de nieuwe rekeninghouder in";
            }else if (account.getHolders().contains(customer)) {
                return "Deze gebruiker is reeds rekeninghouder van deze rekening";
            }
        }
        return "";
    }

    public ConfirmBean getConfirmBeanAccountHolderToken(){
        return new ConfirmBean(
                "Aanvraag nieuwe rekeninghouder geslaagd",
                "Je aanvraag om een nieuwe rekeninghouder toe te voegen is geslaagd. Klik op volgende om naar de rekeningpagina terug te keren",
                "",
                "");
    }



    /*TODO fix return value, maybe bool instead of acc*/
    public Account resolveToken(String tokenId, String tokenCode, Customer loggedInCustomer){
            Optional<AccountHolderToken> OToken = accountHolderTokenRepository.findById(Integer.parseInt(tokenId));
            if (OToken.isPresent()){
                AccountHolderToken token = OToken.get();
                if (tokenCode.equals(token.getToken())){
                    accountHolderTokenRepository.delete(token);
                    Account acc = token.getAccount();
                    List<Customer> holders = acc.getHolders();
                    holders.add(loggedInCustomer);
                    acc.setHolders(holders);
                    return accountRepository.save(acc);
                }
            }
        //AccountHolderToken token = accountHolderTokenRepository.findById(1);
        return new ConsumerAccount();
    }
}
