package app.foodpanda.controller;

import app.foodpanda.dto.MessageDTO;
import app.foodpanda.model.Customer;
import app.foodpanda.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping(value = "/customers")
    MessageDTO registerCustomer(@RequestBody Customer newCustomer){
        return customerService.save(newCustomer);
    }

    @GetMapping("/customers")
    List<Customer> findAll(){
        return customerService.findAll();
    }

    @PostMapping("/customer")
    MessageDTO logInCustomer(@RequestBody Customer customer) {
        return customerService.logInCustomer(customer);
    }
}
