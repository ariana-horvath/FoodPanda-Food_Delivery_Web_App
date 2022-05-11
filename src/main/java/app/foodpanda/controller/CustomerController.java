package app.foodpanda.controller;

import app.foodpanda.dto.MessageDTO;
import app.foodpanda.model.Customer;
import app.foodpanda.service.CustomerService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class CustomerController {

    @Autowired
    private CustomerService customerService;
    private static Logger logger = LogManager.getLogger(CustomerController.class);

    @PostMapping(value = "/customers")
    MessageDTO registerCustomer(@RequestBody Customer newCustomer){
        logger.info("Executing the post request for a customer.");
        return customerService.save(newCustomer);
    }

    @PostMapping("/customer")
    MessageDTO logInCustomer(@RequestBody Customer customer) {
        logger.info("Executing the log in for customer: " + customer.getUsername());
        return customerService.logInCustomer(customer);
    }
}
