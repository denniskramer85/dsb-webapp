package dsb.web.controller;

import dsb.web.controller.beans.EmployeeLoginBean;
import dsb.web.domain.Employee;
import dsb.web.service.EmployeeSignInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@SessionAttributes(AttributeMapping.LOGGED_IN_EMPLOYEE)
public class EmployeeSignInController {

    private EmployeeSignInService employeeSignInService;

    @Autowired
    public EmployeeSignInController(EmployeeSignInService employeeSignInService) {
        this.employeeSignInService = employeeSignInService;
    }

    @GetMapping("employee_sign-in")
    public String employeeSignIn(Model model) {

        model.addAttribute("employeeLoginBean", new EmployeeLoginBean());
     return "employee_sign-in";
    }

    @PostMapping("employee_sign-in")
    public ModelAndView employeeSignInHandler(
            @ModelAttribute EmployeeLoginBean employeeLoginBean,
            Model model) {
        Employee loginEmployee = employeeSignInService.validateLogin(employeeLoginBean.getEmployeeUserName(),
                employeeLoginBean.getEmployeePassword());
        if (loginEmployee != null) {
            model.addAttribute(AttributeMapping.LOGGED_IN_EMPLOYEE, loginEmployee);
            return new ModelAndView("employee_dashboard"); //TODO nog aanpassen (2 inlogs?)
        } else {
            model.addAttribute("employeeUserName", employeeLoginBean.getEmployeeUserName());
            return new ModelAndView("employee_sign-in");
        }
    }
}
