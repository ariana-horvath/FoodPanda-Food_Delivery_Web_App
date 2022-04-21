package app.foodpanda.service;

import app.foodpanda.dto.CategoryDTO;
import app.foodpanda.dto.FoodDTO;
import app.foodpanda.dto.MenuItemDTO;
import app.foodpanda.dto.MessageDTO;
import app.foodpanda.model.Category;
import app.foodpanda.model.Food;
import app.foodpanda.model.Restaurant;
import app.foodpanda.repository.CategoryRepository;
import app.foodpanda.repository.FoodRepository;
import app.foodpanda.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FoodService {

    @Autowired
    private FoodRepository foodRepository;
    @Autowired
    private RestaurantRepository restaurantRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private RestaurantService restaurantService;

    public MessageDTO save(FoodDTO foodDTO) {
        Restaurant restaurant = restaurantRepository.findByName(foodDTO.getRestaurant());
        if (restaurant == null)
            return new MessageDTO(false, "Restaurant "+ foodDTO.getRestaurant() + " not existent!");

        Category category = categoryRepository.findByName(foodDTO.getCategory());
        if (category == null)
            return new MessageDTO(false, "Category "+ foodDTO.getCategory() + "not existent!");

        restaurantService.addCategory(foodDTO.getRestaurant(), new CategoryDTO(foodDTO.getCategory()));

        Food food = new Food(foodDTO.getName(), foodDTO.getPrice(), foodDTO.getDescription(), category, restaurant);
        foodRepository.save(food);
        return new MessageDTO(true, "Food successfully added!");
    }

    public FoodDTO foodToDTO(Food food) {
        return new FoodDTO(food.getName(), food.getDescription(), food.getPrice(), food.getCategory().getName(), food.getRestaurant().getName());
    }

    public List<MenuItemDTO> generateMenu(String restaurantName) {
        Restaurant restaurant = restaurantRepository.findByName(restaurantName);
        List<MenuItemDTO> menuItemDTOS = new ArrayList<>();
        for(Category category : restaurant.getCategories()) {
            List<FoodDTO> foodDTOs = foodRepository.findAllByRestaurantAndCategory(restaurant, category)
                    .stream()
                    .map(this::foodToDTO)
                    .collect(Collectors.toList());
            if(foodDTOs.size() != 0)
                menuItemDTOS.add(new MenuItemDTO(category.getName(), foodDTOs));
        }
        return menuItemDTOS;
    }
}
