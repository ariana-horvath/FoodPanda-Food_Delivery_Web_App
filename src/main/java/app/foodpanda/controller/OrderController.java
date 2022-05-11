package app.foodpanda.controller;

import app.foodpanda.dto.MessageDTO;
import app.foodpanda.dto.OrderDTO;
import app.foodpanda.service.OrderService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class OrderController {

    @Autowired
    private OrderService orderService;
    private static Logger logger = LogManager.getLogger(OrderController.class);

    @PostMapping(value = "/orders")
    MessageDTO newOrder(@RequestBody OrderDTO orderDTO) {
        logger.info("Executing the post request for an order.");
        return orderService.save(orderDTO);
    }

    @GetMapping(value = "/orders-restaurant/{restaurant}")
    List<OrderDTO> findAllByRestaurant(@PathVariable String restaurant) {
        logger.info("Executing the get request for orders from restaurant " + restaurant);
        return orderService.findAllByRestaurant(restaurant);
    }

    @GetMapping(value="/orders-customer/{customerName}")
    List<OrderDTO> findAllByCustomer(@PathVariable String customerName){
        logger.info("Executing the get request for orders for customer " + customerName);
        return orderService.findAllByCustomer(customerName);
    }

    @PutMapping("/orders/{orderId}/{status}")
    MessageDTO changeStatus(@PathVariable Long orderId, @PathVariable String status) {
        logger.info("Executing the put request for an order");
        return orderService.changeStatus(orderId, status);
    }
}
