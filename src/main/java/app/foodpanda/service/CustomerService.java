package app.foodpanda.service;

import app.foodpanda.dto.MessageDTO;
import app.foodpanda.model.Customer;
import app.foodpanda.repository.CustomerRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

/**
 * The type Customer service. User for saving a customer, finding all customers or logging in a customer.
 *
 * @author Ariana Horvath
 */
@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;
    private static Logger logger = LogManager.getLogger(CustomerService.class);

    /**
     * Save a new customer to the database.
     *
     * @param customer the customer to be added to the database (username and password)
     * @return message DTO, containing success boolean and a message
     */
    public MessageDTO save(Customer customer) {
        if (customerRepository.findByUsername(customer.getUsername()) != null) {
            logger.error("Customer "+customer.getUsername()+ " already existent.");
            return new MessageDTO(false,
                                  "Customer with this username already existent! Please choose another one.");
        }
        else {
            customer.setPassword(BCrypt.hashpw(customer.getPassword(), BCrypt.gensalt()));
            customerRepository.save(customer);
            logger.info("Customer "+ customer.getUsername() + " successfully added.");
            return new MessageDTO(true, "Customer successfully added.");
        }
    }

    /**
     * Log in a customer.
     *
     * @param customer the customer to be logged in
     * @return message DTO, containing success boolean and a message
     */
    public MessageDTO logInCustomer(Customer customer) {
        Customer existentCustomer = customerRepository.findByUsername(customer.getUsername());
        if (existentCustomer == null) {
            logger.error("Customer "+ customer.getUsername() + " not existent.");
            return new MessageDTO(false, "Customer not existent!");
        }
        else
            if (!BCrypt.checkpw(customer.getPassword(), existentCustomer.getPassword())) {
                logger.error("Incorrect password for customer " + customer.getUsername());
                return new MessageDTO(false, "Incorrect Password!");
            }
            else {
                logger.info("Customer " + customer.getUsername() + " logged in successfully.");
                return new MessageDTO(true, "Log in successfully.");
            }
    }
}
