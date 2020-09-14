package dsb.web.service;

import dsb.web.controller.beans.CustomerBean;
import dsb.web.domain.Address;
import dsb.web.domain.Customer;
import dsb.web.repository.AddressRepository;
import dsb.web.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.List;

@Service
public class SignupService {

    private CustomerRepository customerRepository;


    @Autowired
    public SignupService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
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

    /**2 stylers for name data**/
    public String initialsStyler(String initials) {
        String[] asArray = initials.trim().replaceAll("[^a-zA-Z]", "")
                .toUpperCase().split("");
        return String.join(".", asArray) + ".";
    }
    public String surnameStyler(String surname) {
        String mid = surname.toLowerCase();
        return mid.substring(0,1).toUpperCase() + mid.substring(1);
    }

    /**styler for address data**/
    public String createNamePrint(CustomerBean customerBean) {
        String initials = customerBean.getInitials();
        String inserts = customerBean.getInserts() + " ";
        if (customerBean.getInserts() == null) inserts = "";
        String surname = customerBean.getSurname();

        return String.format("%s %s%s", initials, inserts, surname);
    }

    /**styler for address data**/
    public String createAddressPrint(CustomerBean customerBean) {
        String street = customerBean.getStreet();
        String houseNumber = customerBean.getHouseNumberString();
        String affixes = customerBean.getAffixes();
        String zipCode = customerBean.getZipCode();
        String city = customerBean.getCity();

        return String.format("%s %s %s\n%s %s", street, houseNumber, affixes, zipCode, city);
    }
}
