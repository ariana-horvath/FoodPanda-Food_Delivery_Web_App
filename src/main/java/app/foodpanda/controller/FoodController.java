package app.foodpanda.controller;

import app.foodpanda.dto.FoodDTO;
import app.foodpanda.dto.MenuItemDTO;
import app.foodpanda.dto.MessageDTO;
import app.foodpanda.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class FoodController {

    @Autowired
    private FoodService foodService;

    @PostMapping(value = "/foods")
    MessageDTO newFood(@RequestBody FoodDTO newFoodDTO){
        return foodService.save(newFoodDTO);
    }

    @GetMapping("/foods/{restaurantName}")
    List<MenuItemDTO> generateMenuForRestaurant(@PathVariable String restaurantName) {
        return foodService.generateMenu(restaurantName);
    }
}
