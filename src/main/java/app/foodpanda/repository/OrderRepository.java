package app.foodpanda.repository;

import app.foodpanda.model.Customer;
import app.foodpanda.model.Order;
import app.foodpanda.model.Restaurant;

import java.util.List;

public interface OrderRepository extends AbstractRepository<Order> {

    List<Order> findAllByCustomer(Customer customer);

    List<Order> findAllByRestaurant(Restaurant restaurant);

    Order findByOrderId(Long orderId);
}
