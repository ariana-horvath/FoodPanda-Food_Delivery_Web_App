package app.foodpanda.controller;

import app.foodpanda.dto.MessageDTO;
import app.foodpanda.dto.OrderDTO;
import app.foodpanda.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping(value = "/orders")
    MessageDTO newOrder(@RequestBody OrderDTO orderDTO) {
        return orderService.save(orderDTO);
    }

    @GetMapping(value = "/orders-restaurant/{restaurant}")
    List<OrderDTO> findAllByRestaurant(@PathVariable String restaurant) {
        return orderService.findAllByRestaurant(restaurant);
    }

    @GetMapping(value="/orders-customer/{customerName}")
    List<OrderDTO> findAllByCustomer(@PathVariable String customerName){
        return orderService.findAllByCustomer(customerName);
    }

    @PutMapping("/orders/{orderId}/{status}")
    MessageDTO changeStatus(@PathVariable Long orderId, @PathVariable String status) {
        return orderService.changeStatus(orderId, status);
    }
}
