package dsb.web.service;

import dsb.web.controller.beans.TransferBean;
import dsb.web.domain.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.SessionAttributes;

@Service
@SessionAttributes({"selectedAccountSession", "transferBeanSession"})
public class TransferService {

    private AccountPageService accountPageService;


    @Autowired
    public TransferService(AccountPageService accountPageService) {
        this.accountPageService = accountPageService;

    }

    public String validateTransferData (TransferBean transferBean, Model model) {
        Account account = (Account) model.getAttribute("selectedAccountSession");
        model.addAttribute("transferBean", transferBean);
        model.addAttribute("printAccountDataBean", accountPageService.makePrintAccountDataBean(account));
        return "transferPage";
    }






}
