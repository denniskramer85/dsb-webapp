package dsb.web.service;

import dsb.web.domain.Employee;
import dsb.web.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeSignInService {
    private EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeSignInService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Employee validateLogin(String employeeUserName, String password) {
        Optional<Employee> employeeOptional = employeeRepository.findOneByEmployeeUserName(employeeUserName);
        Employee loginEmployee = null;
        if (employeeOptional.isPresent()) {
            loginEmployee = employeeOptional.get();
        } else {
            return loginEmployee;
        }
        if (loginEmployee.getPassword().equals(password)) {
            return loginEmployee;
        }
        else {
            return null;
        }
    }
}