package app.foodpanda.controller;

import app.foodpanda.dto.CategoryDTO;
import app.foodpanda.dto.MessageDTO;
import app.foodpanda.dto.RestaurantDTO;
import app.foodpanda.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    @PostMapping(value = "/restaurants")
    MessageDTO newRestaurant(@RequestBody RestaurantDTO newRestaurant){
        return restaurantService.save(newRestaurant);
    }

    @GetMapping("/restaurants")
    List<RestaurantDTO> findAll(){
        return restaurantService.findAll();
    }

    @PutMapping("/restaurants/addCategory/{restaurantName}")
    private MessageDTO addCategory(@PathVariable String restaurantName, @RequestBody CategoryDTO categoryDTO) {
        return restaurantService.addCategory(restaurantName, categoryDTO);
    }
}
