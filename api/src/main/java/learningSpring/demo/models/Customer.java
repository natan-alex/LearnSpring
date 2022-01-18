package learningSpring.demo.models;

import learningSpring.demo.dtos.customer.CreateCustomerDto;
import learningSpring.demo.interfaces.Updatable;

import java.util.UUID;

public class Customer implements Updatable<Customer> {
    private final UUID id;
    private String name;

    public Customer(String name) {
        this.name = name;
        id = UUID.randomUUID();
    }

    public static Customer fromCreateDto(CreateCustomerDto dto) {
        return new Customer(dto.getName());
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void updateSelfFromItem(Customer item) {
        this.name = item.getName();
    }
}
