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


    /** create domain Customer form bean and save in db **/
    public Customer createAndSaveCustomer(CustomerBean cb) {
        Address address = new Address(cb.getStreet(), cb.getHouseNumber(),
                cb.getAffixes(), cb.getZipCode(), cb.getCity());

        Customer customer = new Customer(
                cb.getSocialSecurityNo(), cb.getInitials(), cb.getInserts(), cb.getSurname(),
                address, cb.getUsername(), cb.getPassword(), null);

        customerRepository.save(customer);
        System.out.println("klant met achternaam " + customer.getSurname() + " is opgeslagen");

        return customer;
    }

    /**2 stylers for name data**/
    public String initialsStyler(String initials) {
        String[] asArray = initials.trim().replaceAll("[^a-zA-Z0-9]", "")
                .toUpperCase().split("");
        return String.join(".", asArray);
    }
    public String surnameStyler(String surname) {
        String mid = surname.toLowerCase();
        return mid.substring(0,1).toUpperCase() + mid.substring(1);
    }




    public void allServerSideChecksBean(CustomerBean cb) {

        //hier komen achtereenvolgens alle serverside bean-checks
        //alle velden niet leeg (behalve x2)
        //bsn en huisnummer type nummer
        //bsn tjek
        //gebruikersnaam al in gebruik
        //ww eisen sja: sterke? nth
        //ww 2x hetzelfde

        //leeg: in bean methode die relevante velden tjekt of ze leeg zijn, zo ja geeft naam veld




        //aanroepen elfproef BSN
        boolean validBSN = isValidBSN(cb.getSocialSecurityNo());



    }





    private boolean isValidBSN(Integer socialSecurityNo) {
        if (socialSecurityNo <= 9999999 || socialSecurityNo > 999999999) {
            return false;
        }
        int sum = -1 * socialSecurityNo % 10;
        for (int i = 2; socialSecurityNo > 0; i++) {
            int val = (socialSecurityNo /= 10) % 10;
            sum += i * val;
        }
        return sum != 0 && sum % 11 == 0;
    }



}
