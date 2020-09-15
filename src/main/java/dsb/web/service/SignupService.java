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


    //create printable name and address data + add to model
    public void printNameAndAddress(CustomerBean customerBean, Model model) {

        Customer customerPrint = new Customer(customerBean.getInitials(),
                customerBean.getInserts(), customerBean.getSurname());
        model.addAttribute("namePrint", customerPrint.printWholeName());

        int houseNumber = Integer.parseInt(customerBean.getHouseNumberString());
        Address addressPrint = new Address(customerBean.getStreet(), houseNumber, customerBean.getAffixes(),
                customerBean.getZipCode(), customerBean.getCity());
        model.addAttribute("addressPrint", addressPrint.printWholeAddress());
    }

    //create real customer (incl. address) from bean + save in DB
    public Customer createAndSaveCustomer(CustomerBean customerBean) {
        Address address = new Address(customerBean.getStreet(), customerBean.getHouseNumber(),
                customerBean.getAffixes(), customerBean.getZipCode(), customerBean.getCity());

        Customer customer = new Customer(
                customerBean.getSocialSecurityNo(), customerBean.getInitials(), customerBean.getInserts(),
                customerBean.getSurname(), address, customerBean.getUsername(), customerBean.getPassword(), null);

        customerRepository.save(customer);
        System.out.println("klant met achternaam " + customer.getSurname() + " is opgeslagen");

        return customer;
    }



}
