package dsb.web.controller.beans;

public class EmployeeLoginBean {
    private String employeeUserName;
    private String employeePassword;

    public EmployeeLoginBean(String employeeUserName, String employeePassword) {
        this.employeeUserName = employeeUserName;
        this.employeePassword = employeePassword;
    }

    public EmployeeLoginBean() {
    }

    public String getEmployeeUserName() {
        return employeeUserName;
    }

    public void setEmployeeUserName(String employeeUserName) {
        this.employeeUserName = employeeUserName;
    }

    public String getEmployeePassword() {
        return employeePassword;
    }

    public void setEmployeePassword(String employeePassword) {
        this.employeePassword = employeePassword;
    }
}
