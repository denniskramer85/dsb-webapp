package dsb.web.service;

import dsb.web.controller.beans.CustomerBean;
import dsb.web.domain.Address;
import dsb.web.domain.Customer;
import dsb.web.repository.AddressRepository;
import dsb.web.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
