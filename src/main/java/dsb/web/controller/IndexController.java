package dsb.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.w3c.dom.Attr;

@Controller
@SessionAttributes({AttributeMapping.LOGGED_IN_CUSTOMER, AttributeMapping.LOGGED_IN_EMPLOYEE})
public class IndexController {

    @Autowired
    public IndexController() {
    }

    @GetMapping("/")
    public String handleIndex() {
        return "index";
    }
}
