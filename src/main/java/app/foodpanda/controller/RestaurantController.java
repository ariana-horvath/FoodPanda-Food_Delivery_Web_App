package app.foodpanda.controller;

import app.foodpanda.dto.CategoryDTO;
import app.foodpanda.dto.MessageDTO;
import app.foodpanda.dto.RestaurantDTO;
import app.foodpanda.service.RestaurantService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;
    private static Logger logger = LogManager.getLogger(RestaurantController.class);

    @PostMapping(value = "/restaurants")
    MessageDTO newRestaurant(@RequestBody RestaurantDTO newRestaurant){
        logger.info("Executing the post request for a restaurant.");
        return restaurantService.save(newRestaurant);
    }

    @GetMapping("/restaurants")
    List<RestaurantDTO> findAll(){
        logger.info("Executing the get request for restaurants.");
        return restaurantService.findAll();
    }

    @PutMapping("/restaurants/addCategory/{restaurantName}")
    private MessageDTO addCategory(@PathVariable String restaurantName, @RequestBody CategoryDTO categoryDTO) {
        logger.info("Executing the put request for a category of restaurant " + restaurantName);
        return restaurantService.addCategory(restaurantName, categoryDTO);
    }
}
