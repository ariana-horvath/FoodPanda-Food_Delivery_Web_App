package app.foodpanda.repository;

import app.foodpanda.model.Customer;

public interface CustomerRepository extends AbstractRepository<Customer> {

    Customer findByUsername(String username);
}
