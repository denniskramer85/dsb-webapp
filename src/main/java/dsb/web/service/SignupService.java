package dsb.web.service;

import dsb.web.controller.beans.CustomerBean;
import dsb.web.domain.Address;
import dsb.web.domain.Customer;
import dsb.web.repository.AddressRepository;
import dsb.web.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.lang.reflect.Field;
import java.util.List;

@Service
public class SignupService {

    private CustomerRepository customerRepository;

    @Autowired
    public SignupService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }



    public void printformatNameAndAddress(CustomerBean customerBean, Model model) {
    }
    
    /**styler for name data**/
    public String createNamePrint(CustomerBean cb) {

        Customer custPrint = new Customer(cb.getInitials(), cb.getInserts(), cb.getSurname());
        return custPrint.printWholeName();

    }

    /**styler for address data**/
    public String createAddressPrint(CustomerBean cb) {
        int houseNumber = Integer.parseInt(cb.getHouseNumberString());
        Address addressPrint = new Address(cb.getStreet(), houseNumber, cb.getAffixes(), cb.getZipCode(), cb.getCity());
        return addressPrint.printWholeAddress();
    }

    /** create domain Customer form bean and save in db **/
    public Customer createAndSaveCustomer(CustomerBean customerBean) {
        Address address = new Address(customerBean.getStreet(), customerBean.getHouseNumber(),
                customerBean.getAffixes(), customerBean.getZipCode(), customerBean.getCity());

        Customer customer = new Customer(
                customerBean.getSocialSecurityNo(), customerBean.getInitials(), customerBean.getInserts(),
                customerBean.getSurname(), address, customerBean.getUsername(), customerBean.getPassword(), null);

        customerRepository.save(customer);
        System.out.println("klant met achternaam " + customer.getSurname() + " is opgeslagen");

        //TODO hier in sess hangen?

        return customer;
    }



}
