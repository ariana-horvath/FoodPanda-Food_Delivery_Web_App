package app.foodpanda;

import app.foodpanda.dto.CategoryDTO;
import app.foodpanda.dto.FoodDTO;
import app.foodpanda.dto.MessageDTO;
import app.foodpanda.model.Category;
import app.foodpanda.model.Restaurant;
import app.foodpanda.repository.CategoryRepository;
import app.foodpanda.repository.FoodRepository;
import app.foodpanda.repository.RestaurantRepository;
import app.foodpanda.service.FoodService;
import app.foodpanda.service.RestaurantService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class FoodServiceTest {

    @InjectMocks
    private FoodService foodService;

    @Mock
    private RestaurantRepository restaurantRepository;

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private RestaurantService restaurantService;

    @Test
    public void testSaveNoRestaurant() {
        FoodDTO food = new FoodDTO("Pizza Diavola", "", 25.99, "Pizza", "Marty");

        when(restaurantRepository.findByName(food.getRestaurant())).thenReturn(null);

        MessageDTO expected = new MessageDTO(false, "Restaurant " + food.getRestaurant() + " not existent!");
        MessageDTO response = foodService.save(food);

        Assert.assertEquals(expected, response);
    }

    @Test
    public void testSaveNoCategory() {
        FoodDTO food = new FoodDTO("Pizza Diavola", "", 25.99, "Pizza", "Marty");
        Restaurant restaurant = new Restaurant("Marty", "Platinia", null);

        when(restaurantRepository.findByName(food.getRestaurant())).thenReturn(restaurant);
        when(categoryRepository.findByName(food.getCategory())).thenReturn(null);

        MessageDTO expected = new MessageDTO(false, "Category " + food.getCategory() + "not existent!");
        MessageDTO response = foodService.save(food);

        Assert.assertEquals(expected, response);
    }

    @Test
    public void testSaveOk() {
        FoodDTO food = new FoodDTO("Pizza Diavola", "", 25.99, "Pizza", "Marty");
        Restaurant restaurant = new Restaurant("Marty", "Platinia", null);
        Category category = new Category("Pizza");

        when(restaurantRepository.findByName(food.getRestaurant())).thenReturn(restaurant);
        when(categoryRepository.findByName(food.getCategory())).thenReturn(category);

        MessageDTO expected = new MessageDTO(true, "Food successfully added!");
        MessageDTO response = foodService.save(food);

        Assert.assertEquals(expected, response);
    }
}
