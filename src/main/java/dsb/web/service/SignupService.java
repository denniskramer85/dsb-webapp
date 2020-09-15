package dsb.web.service;

import dsb.web.controller.beans.SignUpBean;
import dsb.web.domain.Address;
import dsb.web.domain.Customer;
import dsb.web.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class SignupService {

    private CustomerRepository customerRepository;

    @Autowired
    public SignupService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }


    //create printable name and address data + add to model
    public void printNameAndAddress(SignUpBean signUpBean, Model model) {

        Customer customerPrint = new Customer(signUpBean.getInitials(),
                signUpBean.getInserts(), signUpBean.getSurname());
        model.addAttribute("namePrint", customerPrint.printWholeName());

        int houseNumber = Integer.parseInt(signUpBean.getHouseNumberString());
        Address addressPrint = new Address(signUpBean.getStreet(), houseNumber, signUpBean.getAffixes(),
                signUpBean.getZipCode(), signUpBean.getCity());
        model.addAttribute("addressPrint", addressPrint.printWholeAddress());
    }

    //create real customer (incl. address) from bean + save in DB
    public Customer createAndSaveCustomer(SignUpBean signUpBean) {
        Address address = new Address(signUpBean.getStreet(), signUpBean.getHouseNumber(),
                signUpBean.getAffixes(), signUpBean.getZipCode(), signUpBean.getCity());

        Customer customer = new Customer(
                signUpBean.getSocialSecurityNo(), signUpBean.getInitials(), signUpBean.getInserts(),
                signUpBean.getSurname(), address, signUpBean.getUsername(), signUpBean.getPassword(), null);

        customerRepository.save(customer);
        System.out.println("klant met achternaam " + customer.getSurname() + " is opgeslagen");

        return customer;
    }



}
