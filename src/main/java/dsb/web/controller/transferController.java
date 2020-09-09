package dsb.web.controller;

import dsb.web.controller.beans.AccountPageBean;
import dsb.web.controller.beans.TransferBean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

@Controller
@SessionAttributes("selectedAccountSession")
public class transferController {



    @GetMapping("transfer")
    public String startTransferPageHandler (Model model) {

        //dummy
        String[] lijstje = {"x", "a",};
        List<String> lijstDummy = Arrays.asList(lijstje);
        AccountPageBean dummy = new AccountPageBean("SMEAccount", "123", "Hans BV",
                "Kees en piet", "189,77", "1-4-33", lijstDummy);


        //AccountPageBean acp = (AccountPageBean) model.getAttribute("selectedAccountSession");
        model.addAttribute("selectedAccount", dummy);

        model.addAttribute("transferBean", new TransferBean());



        return "transferPage";
    }

    @PostMapping("transfer")
    public String transferDataHandler (@Valid @ModelAttribute TransferBean tb, Model model) {

        return null;
    }




}




