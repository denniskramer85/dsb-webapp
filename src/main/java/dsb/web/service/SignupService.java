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
    private AddressRepository addressRepository;

    @Autowired
    public SignupService(CustomerRepository customerRepository, AddressRepository addressRepository) {
        this.customerRepository = customerRepository;
        this.addressRepository = addressRepository;
    }

    public void saveCustomerAndAddress (CustomerBean cb) {

        Address address = new Address(cb.getStreet(), cb.getHouseNumber(),
                cb.getAffixes(), cb.getZipCode(), cb.getCity());

        Customer customer = new Customer(
                cb.getSocialSecurityNo(), cb.getInitials(), cb.getInserts(), cb.getSurname(),
                address, cb.getUsername(), cb.getPassword(), null);

        customerRepository.save(customer);
        System.out.println("klant met achternaam " + customer.getSurname() + " is opgeslagen");
    }

    public void serverCheck(CustomerBean cb) {

//        //lijst met alle attribs
//        Field[] fields = cb.getClass().getDeclaredFields();
//
//
//        for (Field f : fields) {
//            if (f.get(this) == null) System.out.println("xxx");
//        }

    }



        /*
    NIET LEEG
    alles behalve inserts en affixes

    NUMMER
    housenumber en socSec

    BSN



    private String surname;
    private String inserts;
    private String initials;
    private String street;
    private Integer houseNumber;
    private String affixes;
    private String zipCode;
    private String city;
    private Integer socialSecurityNo;
    private String username;
    private String password;

        */





}
