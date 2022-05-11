package app.foodpanda;

import app.foodpanda.dto.MessageDTO;
import app.foodpanda.dto.OrderDTO;
import app.foodpanda.model.Order;
import app.foodpanda.model.Restaurant;
import app.foodpanda.repository.CustomerRepository;
import app.foodpanda.repository.OrderRepository;
import app.foodpanda.repository.RestaurantRepository;
import app.foodpanda.service.OrderService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class OrderServiceTest {

    @InjectMocks
    private OrderService orderService;

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private RestaurantRepository restaurantRepository;

    @Mock
    private CustomerRepository customerRepository;


    @Test
    public void testChangeStatus() {
        Order order = new Order();
        order.setOrderId(1234L);
        order.setStatus("PENDING");

        when(orderRepository.findByOrderId(1234L)).thenReturn(order);

        MessageDTO expected = new MessageDTO(true, "Status changed!");
        MessageDTO response = orderService.changeStatus(1234L, "IN DELIVERY");

        Assert.assertEquals(expected, response);
    }

    @Test
    public void testSaveNoRestaurant() {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setRestaurant("Marty");
        when(restaurantRepository.findByName(orderDTO.getRestaurant())).thenReturn(null);

        MessageDTO expected = new MessageDTO(false, "Restaurant " + orderDTO.getRestaurant() + " not existent!");
        MessageDTO response = orderService.save(orderDTO);

        Assert.assertEquals(expected, response);
    }

    @Test
    public void testSaveNoCustomer() {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setRestaurant("Marty");
        orderDTO.setCustomer("ariana");
        Restaurant restaurant = new Restaurant("Marty", "Platinia", null);

        when(restaurantRepository.findByName(orderDTO.getRestaurant())).thenReturn(restaurant);
        when(customerRepository.findByUsername(orderDTO.getCustomer())).thenReturn(null);

        MessageDTO expected = new MessageDTO(false, "Customer " + orderDTO.getCustomer() + " not existent!");
        MessageDTO response = orderService.save(orderDTO);

        Assert.assertEquals(expected, response);
    }
}
