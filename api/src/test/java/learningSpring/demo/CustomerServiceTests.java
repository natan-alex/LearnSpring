package learningSpring.demo;

import learningSpring.demo.exceptions.customer.CustomerNotFoundException;
import learningSpring.demo.models.Customer;
import learningSpring.demo.services.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

@SpringBootTest
class CustomerServiceTests {
	CustomerService customerService;

	@BeforeEach
	void createService() {
		customerService = new CustomerService();
		customerService.addItem(new Customer("foo"));
		customerService.addItem(new Customer("bar"));
		customerService.addItem(new Customer("john"));
		customerService.addItem(new Customer("doe"));
	}

	@Test
	void customersCorrectlyGetFromCustomerService() {
		var customerList = customerService.getAllItems();
		assertFalse(customerList.isEmpty());
		assertEquals(customerList.iterator().next().getName(), "foo");
	}

	@Test
	void customerFoundByHisName() {
		var customer = customerService.getItem(c -> c.getName().compareTo("foo") == 0);
		assertDoesNotThrow(() -> customerService.getItem(c -> c.getName().compareTo("foo") == 0));
		assertNotNull(customer);
	}

	@Test
	void customerFoundByHisId() {
		var allCustomers = customerService.getAllItems();
		var firstCustomer = allCustomers.iterator().next();
		var customer = customerService.getItem(
			c -> c.getId().compareTo(firstCustomer.getId()) == 0);
		assertDoesNotThrow(() -> customerService.getItem(
			c -> c.getId().compareTo(firstCustomer.getId()) == 0));
		assertNotNull(customer);
		assertEquals(firstCustomer.getName(), customer.getName());
		assertEquals(firstCustomer.getId(), customer.getId());
	}

	@Test
	void customerNotFoundThrowsException() {
		assertThrows(
			CustomerNotFoundException.class,
			() -> customerService.getItem(c -> c.getId().equals(UUID.randomUUID()))
		);
	}

	@Test
	void customerCouldBeAddedSuccessfully() {
		var newCustomer = new Customer("test");
		customerService.addItem(newCustomer);
		assertFalse(customerService.getAllItems().isEmpty());
		assertTrue(customerService.getAllItems().size() > 0);
		assertNotNull(customerService.getItemById(newCustomer.getId()));
	}

	@Test
	void customerNotFoundOnReplaceThrowsException() {
		var newCustomer = new Customer("test");
		assertThrows(
			CustomerNotFoundException.class,
			() -> customerService.replaceItem(UUID.randomUUID(), newCustomer));
	}

	@Test
	void customerReplacedSuccessfully() {
		var customer = customerService.getItem(c -> c.getName().compareTo("foo") == 0);
		customer.setName("foo updated");
		customerService.replaceItem(customer.getId(), customer);
		customer = customerService.getItemById(customer.getId());
		assertEquals(0, customer.getName().compareTo("foo updated"));
	}

	@Test
	void customerNotFoundOnDeleteThrowsException() {
		assertThrows(
			CustomerNotFoundException.class,
			() -> customerService.deleteItemById(UUID.randomUUID()));
		assertThrows(
			CustomerNotFoundException.class,
			() -> customerService.deleteItem(
				customer -> customer.getId().compareTo(UUID.randomUUID()) == 0));
	}

	@Test
	void customerDeletedSuccessfully() {
		var customer = customerService.getAllItems().stream().findFirst().orElseThrow();
		assertDoesNotThrow(() -> customerService.deleteItemById(customer.getId()));
		assertDoesNotThrow(() -> {
			customerService.addItem(customer);
			customerService.deleteItem(c -> c.getId().compareTo(customer.getId()) == 0);
		});
	}

	@Test
	void customerNotFoundAfterDeletion() {
		var customer = customerService.getAllItems().stream().findFirst().orElseThrow();
		assertDoesNotThrow(() -> customerService.deleteItemById(customer.getId()));
		assertThrows(
			CustomerNotFoundException.class,
			() -> customerService.deleteItem(c -> c.getId().compareTo(customer.getId()) == 0));
	}
}