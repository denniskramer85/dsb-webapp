package dsb.web.service;

import dsb.web.domain.Account;
import dsb.web.domain.AccountHolderToken;
import dsb.web.domain.ConsumerAccount;
import dsb.web.domain.Customer;
import dsb.web.repository.AccountHolderTokenRepository;
import dsb.web.repository.AccountRepository;
import dsb.web.repository.ConsumerAccountRepository;
import dsb.web.repository.SMEAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddAccountHolderService {
    private ConsumerAccountRepository consumerAccountRepository;
    private SMEAccountRepository smeAccountRepository;
    private AccountRepository accountRepository;
    private AccountHolderTokenRepository accountHolderTokenRepository;

    @Autowired
    public AddAccountHolderService(ConsumerAccountRepository consumerAccountRepository, SMEAccountRepository smeAccountRepository, AccountRepository accountRepository, AccountHolderTokenRepository accountHolderTokenRepository) {
        this.accountHolderTokenRepository = accountHolderTokenRepository;
        this.consumerAccountRepository = consumerAccountRepository;
        this.smeAccountRepository = smeAccountRepository;
        this.accountRepository = accountRepository;
    }

    public AccountHolderToken createAddAccountHolderToken(Customer userToAdd, Account accountToAddTo, String token) {
        return accountHolderTokenRepository.save(new AccountHolderToken(userToAdd,accountToAddTo,token));
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
