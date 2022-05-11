package app.foodpanda.controller;

import app.foodpanda.dto.FoodDTO;
import app.foodpanda.dto.MenuItemDTO;
import app.foodpanda.dto.MessageDTO;
import app.foodpanda.service.FoodService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class FoodController {

    @Autowired
    private FoodService foodService;
    private static Logger logger = LogManager.getLogger(FoodController.class);

    @PostMapping(value = "/foods")
    MessageDTO newFood(@RequestBody FoodDTO newFoodDTO){
        logger.info("Executing the post request for a food.");
        return foodService.save(newFoodDTO);
    }

    @GetMapping("/foods/{restaurantName}")
    List<MenuItemDTO> generateMenuForRestaurant(@PathVariable String restaurantName) {
        logger.info("Executing the get request for foods for restaurant: " + restaurantName);
        return foodService.generateMenu(restaurantName);
    }
}
