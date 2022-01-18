package learningSpring.demo.controllers;

import learningSpring.demo.dtos.customer.CreateCustomerDto;
import learningSpring.demo.dtos.customer.ReplaceCustomerDto;
import learningSpring.demo.exceptions.customer.CustomerNotFoundException;
import learningSpring.demo.models.Customer;
import learningSpring.demo.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    @Autowired
    private final CustomerService customerService;

    private void loadInitialData() {
        var customers = java.util.List.of(
            new Customer("foo"),
            new Customer("bar"),
            new Customer("john"),
            new Customer("doe")
        );
        customers.forEach(c -> customerService.addItem(c));
    }

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
        loadInitialData();
    }

    @GetMapping
    public Collection<Customer> getAllCustomers() {
        return customerService.getAllItems();
    }

    @GetMapping("/{id}")
    public Customer getCustomerById(
        @PathVariable String id
    ) {
        var customerId = tryConvertStringToUuid(id)
            .orElseThrow(CustomerNotFoundException::new);
        return customerService.getItemById(customerId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Customer addNewCustomer(
        @RequestBody CreateCustomerDto newCustomer
    ) {
        return customerService.addItem(Customer.fromCreateDto(newCustomer));
    }

    @PutMapping("/{id}")
    public Customer replaceAnExistingCustomer(
        @PathVariable String id,
        @RequestBody ReplaceCustomerDto newCustomer
    ) {
        var customerId = tryConvertStringToUuid(id)
            .orElseThrow(CustomerNotFoundException::new);
        var updatedCustomer = new Customer(newCustomer.getName());
        return customerService.replaceItem(customerId, updatedCustomer);
    }

    @DeleteMapping("/{id}")
    public void deleteCustomerById(
        @PathVariable String id
    ) {
        var customerId = tryConvertStringToUuid(id)
            .orElseThrow(CustomerNotFoundException::new);
        customerService.deleteItemById(customerId);
    }

    private static Optional<UUID> tryConvertStringToUuid(String id) {
        try {
            return Optional.of(UUID.fromString(id));
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}
