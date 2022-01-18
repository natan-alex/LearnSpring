package learningSpring.demo.exceptions.customer;

public class CustomerNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public CustomerNotFoundException(String customerId) {
        super(String.format("Customer with ID = %s not found", customerId));
    }

    public CustomerNotFoundException() {
        super("Customer could not be found.");
    }
}
