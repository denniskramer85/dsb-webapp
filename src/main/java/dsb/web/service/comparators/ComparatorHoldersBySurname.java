package dsb.web.service.comparators;

import dsb.web.domain.Customer;

import java.util.Comparator;

public class ComparatorHoldersBySurname implements Comparator<Customer> {

    @Override
    public int compare(Customer a, Customer b) {
        return a.getSurname().compareToIgnoreCase(b.getSurname());
    }
}
