package dsb.web.controller.rest;

import dsb.web.controller.beans.TransferNameBean;
import dsb.web.domain.Account;
import dsb.web.repository.AccountRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/transfer-name-checks")
public class TransferNameController {
    AccountRepository accountRepository;

    public TransferNameController(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @GetMapping(value = "/{accountNo}/{name}")
    public TransferNameBean matchAccountName(@PathVariable("accountNo") String accountNo,
                                             @PathVariable("name") String name) {

        Account matchingAccount = accountRepository.findAccountByAccountNo(accountNo);

        if (matchingAccount == null) {
            return new TransferNameBean(false, null);
        }

        if (matchingAccount.getHoldersString(matchingAccount.getHolders().size()).toLowerCase().equals(name.toLowerCase())) {
            return new TransferNameBean(true, null);
        }

        return new TransferNameBean(false, matchingAccount.getHoldersString(matchingAccount.getHolders().size()));
    }

}
