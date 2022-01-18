package learningSpring.demo.services;

import learningSpring.demo.exceptions.customer.CustomerNotFoundException;
import learningSpring.demo.interfaces.Repository;
import learningSpring.demo.models.Customer;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;
import java.util.function.Predicate;

@Service
public class CustomerService implements Repository<Customer, UUID> {
    private final Collection<Customer> customers;

    public CustomerService() {
        customers = new ArrayList<>();
    }

    @Override
    public Customer getItemById(UUID id) {
        return getItem(customer -> customer.getId().compareTo(id) == 0);
    }

    @Override
    public Customer getItem(Predicate<Customer> predicate) {
        return customers.stream()
            .filter(predicate)
            .findFirst()
            .orElseThrow(CustomerNotFoundException::new);
    }

    @Override
    public Collection<Customer> getAllItems() {
        return customers;
    }

    @Override
    public Customer replaceItem(UUID id, Customer newItem) {
        var customerFound = getItemById(id);
        customerFound.updateSelfFromItem(newItem);

        return customerFound;
    }

    @Override
    public Customer addItem(Customer newItem) {
        customers.add(newItem);
        return newItem;
    }

    @Override
    public void deleteItem(Predicate<Customer> predicate) {
        if (!customers.removeIf(predicate)) {
            throw new CustomerNotFoundException();
        }
    }

    @Override
    public void deleteItemById(UUID id) {
        deleteItem(customer -> customer.getId().compareTo(id) == 0);
    }
}
