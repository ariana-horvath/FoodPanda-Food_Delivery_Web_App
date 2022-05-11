package app.foodpanda;

import app.foodpanda.dto.MessageDTO;
import app.foodpanda.model.Customer;
import app.foodpanda.repository.CustomerRepository;
import app.foodpanda.service.CustomerService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.crypto.bcrypt.BCrypt;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CustomerServiceTest {

    @InjectMocks
    private CustomerService customerService;

    @Mock
    private CustomerRepository customerRepository;

    @Test
    public void testSaveError() {
        Customer customer = new Customer("customer", "customer");
        when(customerRepository.findByUsername("customer")).thenReturn(customer);

        MessageDTO expectedResponse = new MessageDTO(false, "Customer with this username already " +
                "existent! Please choose another one.");
        MessageDTO response = customerService.save(customer);
        Assert.assertEquals(expectedResponse, response);
    }

    @Test
    public void testSaveOk() {
        Customer customer = new Customer("customer3", "customer3");
        when(customerRepository.findByUsername("customer3")).thenReturn(null);

        MessageDTO expectedResponse = new MessageDTO(true, "Customer successfully added.");
        MessageDTO response = customerService.save(customer);
        Assert.assertEquals(expectedResponse, response);
    }

    @Test
    public void testLogInError() {
        Customer customer = new Customer();
        customer.setUsername("customer1");
        customer.setPassword(BCrypt.hashpw("customer1", BCrypt.gensalt()));
        when(customerRepository.findByUsername("customer1")).thenReturn(null);

        MessageDTO expectedResponse = new MessageDTO(false, "Customer not existent!");
        MessageDTO response = customerService.logInCustomer(customer);
        Assert.assertEquals(expectedResponse, response);
    }

    @Test
    public void testLogInError2() {
        Customer customer = new Customer("customer", "blablabla");

        Customer existentCustomer = new Customer();
        existentCustomer.setUsername("customer");
        existentCustomer.setPassword(BCrypt.hashpw("customer", BCrypt.gensalt()));

        when(customerRepository.findByUsername("customer")).thenReturn(existentCustomer);

        MessageDTO expectedResponse = new MessageDTO(false, "Incorrect Password!");
        MessageDTO response = customerService.logInCustomer(customer);
        Assert.assertEquals(expectedResponse, response);
    }

    @Test
    public void testLogInOk() {
        Customer customer = new Customer("customer", "customer");

        Customer existentCustomer = new Customer();
        existentCustomer.setUsername("customer");
        existentCustomer.setPassword(BCrypt.hashpw("customer", BCrypt.gensalt()));

        when(customerRepository.findByUsername("customer")).thenReturn(existentCustomer);

        MessageDTO expectedResponse = new MessageDTO(true, "Log in successfully.");
        MessageDTO response = customerService.logInCustomer(customer);
        Assert.assertEquals(expectedResponse, response);
    }
}
