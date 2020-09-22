package dsb.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.HttpSessionRequiredException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.w3c.dom.Attr;

import javax.servlet.http.HttpServletRequest;

/*
* Controller to catch any error that occurs.
* SessionRequired exceptions are redirected to sign-in page for either consumers or employees
* */
@Controller
@SessionAttributes({AttributeMapping.LOGGED_IN_EMPLOYEE})
public class ErrorController implements org.springframework.boot.web.servlet.error.ErrorController {

    @RequestMapping("/error")
    public String errorHandler(HttpServletRequest request, Model model) {
        // Retrieve statuscode and exception object from request
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        Exception exception = (Exception) request.getAttribute("javax.servlet.error.exception");

        // Always add status code to model
        model.addAttribute("statusCode", statusCode);

        // If error is caused by user not logged in, redirect to login page after check for user type
        if (exception.getClass() == HttpSessionRequiredException.class) {
            if (model.containsAttribute(AttributeMapping.LOGGED_IN_EMPLOYEE)) {
                return "redirect:employee_sign-in";
            }
            return "redirect:sign-in";
        }

        // Else return generic error, displaying error code
        model.addAttribute("exception", exception.getMessage());
        return "error";
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}
