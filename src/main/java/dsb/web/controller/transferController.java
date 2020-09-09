package dsb.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
//TODO session met rekening?
public class transferController {



    @GetMapping("transfer")
    public String startTransferPageHandler (Model model) {
        return "transferPage";
    }




}




