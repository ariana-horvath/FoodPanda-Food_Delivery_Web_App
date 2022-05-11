package app.foodpanda.service;

import app.foodpanda.dto.FoodDTO;
import app.foodpanda.dto.MessageDTO;
import app.foodpanda.dto.OrderDTO;
import app.foodpanda.model.Customer;
import app.foodpanda.model.Food;
import app.foodpanda.model.Order;
import app.foodpanda.model.Restaurant;
import app.foodpanda.repository.CustomerRepository;
import app.foodpanda.repository.FoodRepository;
import app.foodpanda.repository.OrderRepository;
import app.foodpanda.repository.RestaurantRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The type Order service. Used for saving an order, finding all orders by restaurant and by customer or changing status.
 * of an order.
 *
 * @author Ariana Horvath
 */
@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private RestaurantRepository restaurantRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private FoodRepository foodRepository;

    private static Logger logger = LogManager.getLogger(CustomerService.class);

    /**
     * Save an order in the database.
     *
     * @param orderDTO the order DTO
     * @return message dto, containing success boolean and a message
     */
    public MessageDTO save(OrderDTO orderDTO) {
        Restaurant restaurant = restaurantRepository.findByName(orderDTO.getRestaurant());
        if (restaurant == null) {
            logger.error("Restaurant " + orderDTO.getRestaurant() + " not existent!");
            return new MessageDTO(false, "Restaurant " + orderDTO.getRestaurant() + " not existent!");
        }

        Customer customer = customerRepository.findByUsername(orderDTO.getCustomer());
        if (customer == null) {
            logger.error("Customer " + orderDTO.getCustomer() + " not existent!");
            return new MessageDTO(false, "Customer " + orderDTO.getCustomer() + " not existent!");
        }

        List<Food> foodsOrdered = new ArrayList<>();
        for(FoodDTO foodDTO : orderDTO.getFoods()) {
            Food food = foodRepository.findByRestaurantAndName(restaurant, foodDTO.getName());
            if (food == null) {
                logger.error("Food " + foodDTO.getName() + " not existent.");
                return new MessageDTO(false, "Food " + foodDTO.getName() + " not existent!");
            }

            if (food.getRestaurant().getName().compareTo(restaurant.getName()) != 0)
                return new MessageDTO(false,
                                      "Food items cannot be from different restaurants! Please choose only from one");
            foodsOrdered.add(food);
        }

        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();

        Order order = new Order("PENDING", date, time, orderDTO.getPrice(), customer, restaurant, foodsOrdered);
        orderRepository.save(order);

        logger.info("Order " + order.getOrderId() + " successfully added.");
        return new MessageDTO(true, "Order successfully placed!");
    }


    /**
     * Food to food DTO, creates a food DTO from a food object.
     *
     * @param food the food
     * @return food DTO
     */
    public FoodDTO foodToDTO(Food food) {
        return new FoodDTO(food.getName(), food.getDescription(), food.getPrice(), food.getCategory().getName(),
                           food.getRestaurant().getName());
    }

    /**
     * Orders list to order DTOs list.
     *
     * @param orders the orders
     * @return the list of order DTOs
     */
    public List<OrderDTO> orderListToDTOList(List<Order> orders) {
        List<OrderDTO> orderDTOS = new ArrayList<>();
        for(Order o :orders) {
            List<FoodDTO> foodDTOs = o.getFoods()
                    .stream()
                    .map(this::foodToDTO)
                    .collect(Collectors.toList());

            orderDTOS.add(new OrderDTO(o.getOrderId(), o.getCustomer().getUsername(),
                    o.getRestaurant().getName(),
                    foodDTOs,
                    o.getPrice(),
                    o.getDate().toString(),
                    o.getTime().toString(),
                    o.getStatus()
            ));
        }
        return orderDTOS;
    }

    /**
     * Find all orders from database by restaurant name.
     *
     * @param restaurant the restaurant name
     * @return list of order DTOs
     */
    public List<OrderDTO> findAllByRestaurant(String restaurant) {
        Restaurant r = restaurantRepository.findByName(restaurant);

        return orderListToDTOList(orderRepository.findAllByRestaurant(r));
    }

    /**
     * Find all by orders from database by customer.
     *
     * @param customerName the customer name
     * @return list of order DTOs
     */
    public List<OrderDTO> findAllByCustomer(String customerName) {
        Customer customer = customerRepository.findByUsername(customerName);

        return orderListToDTOList(orderRepository.findAllByCustomer(customer));
    }

    /**
     * Change status of an order, return message dto.
     *
     * @param orderId the order id
     * @param status  the new status
     * @return message dto, containing success boolean and a message
     */
    public MessageDTO changeStatus(Long orderId, String status) {
        Order order = orderRepository.findByOrderId(orderId);
        order.setStatus(status);
        orderRepository.save(order);

        logger.info("Order status changed to "+ status);
        return new MessageDTO(true, "Status changed!");
    }
}
