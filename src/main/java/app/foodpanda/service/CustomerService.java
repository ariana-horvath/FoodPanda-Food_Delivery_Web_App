package app.foodpanda.service;

import app.foodpanda.dto.MessageDTO;
import app.foodpanda.model.Customer;
import app.foodpanda.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public MessageDTO save(Customer customer) {
        if (customerRepository.findByUsername(customer.getUsername()) != null) {
            return new MessageDTO(false, "Customer with this username already existent! Please choose another one.");
        }
        else {
            customer.setPassword(BCrypt.hashpw(customer.getPassword(), BCrypt.gensalt()));
            customerRepository.save(customer);
            return new MessageDTO(true, "Customer successfully added.");
        }
    }

    public List<Customer> findAll() {
        List<Customer> customers = new ArrayList<>();
        customerRepository.findAll().forEach(customers::add);
        return customers;
    }

    public MessageDTO logInCustomer(Customer customer) {
        Customer existentCustomer = customerRepository.findByUsername(customer.getUsername());
        if (existentCustomer == null)
            return new MessageDTO(false, "Customer not existent!");
        else
            if (!BCrypt.checkpw(customer.getPassword(), existentCustomer.getPassword()))
                return new MessageDTO(false, "Incorrect Password!");
            else
                return new MessageDTO(true, "Log in successfully");
    }
}
