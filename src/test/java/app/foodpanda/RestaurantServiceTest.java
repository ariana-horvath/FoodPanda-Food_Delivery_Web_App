package app.foodpanda;

import app.foodpanda.dto.CategoryDTO;
import app.foodpanda.dto.MessageDTO;
import app.foodpanda.dto.RestaurantDTO;
import app.foodpanda.model.Admin;
import app.foodpanda.model.Category;
import app.foodpanda.model.DeliveryZone;
import app.foodpanda.model.Restaurant;
import app.foodpanda.repository.AdminRepository;
import app.foodpanda.repository.CategoryRepository;
import app.foodpanda.repository.RestaurantRepository;
import app.foodpanda.repository.ZoneRepository;
import app.foodpanda.service.RestaurantService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RestaurantServiceTest {

    @InjectMocks
    private RestaurantService restaurantService;

    @Mock
    private RestaurantRepository restaurantRepository;

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private AdminRepository adminRepository;

    @Mock
    private ZoneRepository zoneRepository;

    @Test
    public void testFindAll() {
        List<DeliveryZone> deliveryZones = new ArrayList<>(Arrays.asList(new DeliveryZone("Manastur"),
                new DeliveryZone("Centru"),
                new DeliveryZone("Marasti"),
                new DeliveryZone("Floresti")));

        List<Restaurant> restaurants = new ArrayList<>(Arrays.asList(
                new Restaurant("Subway", "Iulius Mall", new Admin("", ""), deliveryZones),
                new Restaurant("Marty", "Platinia", new Admin("", ""), deliveryZones),
                new Restaurant("Irish Pub", "Str. Horea 13", new Admin("", ""), deliveryZones)));

        when(restaurantRepository.findAll()).thenReturn(restaurants);

        List<RestaurantDTO> restaurantDTOS = restaurantService.findAll();

        restaurants.sort(Comparator.comparing(Restaurant::getName));
        restaurantDTOS.sort(Comparator.comparing(RestaurantDTO::getName));
        boolean equals = true;

        if (restaurants.size() != restaurantDTOS.size())
            equals = false;
        else {
            for (int i = 0; i < restaurants.size(); i++) {
                if (restaurants.get(i).getName().compareTo(restaurantDTOS.get(i).getName()) != 0 ||
                    restaurants.get(i).getLocation().compareTo(restaurantDTOS.get(i).getLocation()) != 0)
                    equals = false;
            }
        }

        Assert.assertTrue(equals);
    }

    @Test
    public void testAddCategoryNoRestaurant() {
        Restaurant restaurant = new Restaurant("Marty", "Platinia", null);

        when(restaurantRepository.findByName("Marty")).thenReturn(null);

        MessageDTO expected = new MessageDTO(false, "Restaurant " + restaurant.getName() + " not existent!");
        MessageDTO response = restaurantService.addCategory("Marty", new CategoryDTO("Dessert"));

        Assert.assertEquals(expected, response);
    }

    @Test
    public void testAddCategoryNoCategory() {
        Category category = new Category("Desserts");
        Restaurant restaurant = new Restaurant("Marty", "Platinia", null);

        when(restaurantRepository.findByName("Marty")).thenReturn(restaurant);
        when(categoryRepository.findByName("Desserts")).thenReturn(null);

        MessageDTO expected = new MessageDTO(false, "Category " + category.getName() + " not existent!");
        MessageDTO response = restaurantService.addCategory("Marty", new CategoryDTO(category.getName()));

        Assert.assertEquals(expected, response);
    }

    @Test
    public void testAddCategoryOk() {
        Category category = new Category("Desserts");
        List<Category> categories = new ArrayList<>(Arrays.asList(new Category("Breakfast"),
                new Category("Lunch"),
                new Category("Dinner")));
        Restaurant restaurant = new Restaurant("Marty", "Platinia", null);
        restaurant.setCategories(categories);

        when(restaurantRepository.findByName("Marty")).thenReturn(restaurant);
        when(categoryRepository.findByName("Desserts")).thenReturn(category);

        MessageDTO expected = new MessageDTO(true, "Category successfully added to restaurant "
                + restaurant.getName());
        MessageDTO response = restaurantService.addCategory("Marty", new CategoryDTO(category.getName()));

        Assert.assertEquals(expected, response);
    }

    @Test
    public void testSaveExistentRestaurant() {
        Restaurant restaurant = new Restaurant("Marty", "Platinia", null);
        when(restaurantRepository.findByName(restaurant.getName())).thenReturn(restaurant);

        RestaurantDTO restaurantDTO = new RestaurantDTO("Marty", "Platinia", null, null);
        MessageDTO expected = new MessageDTO(false, "Restaurant name already existent! " +
                "Please choose another one.");
        MessageDTO response = restaurantService.save(restaurantDTO);

        Assert.assertEquals(expected, response);
    }

    @Test
    public void testSaveAdminWithRestaurant() {
        RestaurantDTO restaurantDTO = new RestaurantDTO("Marty", "Platinia", null, "admin1");
        when(restaurantRepository.findByName(restaurantDTO.getName())).thenReturn(null);

        Admin admin = new Admin("admin1", "");
        Restaurant restaurant = new Restaurant("Subway", "Iulius", admin);
        admin.setRestaurant(restaurant);

        when(adminRepository.findByUsername(restaurantDTO.getAdminUsername())).thenReturn(admin);

        MessageDTO expected = new MessageDTO(false, "This admin already has a restaurant!");
        MessageDTO response = restaurantService.save(restaurantDTO);

        Assert.assertEquals(expected, response);
    }

    @Test
    public void testSaveOk() {
        List<String> deliveryZones = new ArrayList<>(Arrays.asList("Manastur", "Centru", "Marasti", "Floresti"));
        RestaurantDTO restaurantDTO = new RestaurantDTO("Marty", "Platinia", deliveryZones, "admin1");
        when(restaurantRepository.findByName(restaurantDTO.getName())).thenReturn(null);

        Admin admin = new Admin("admin1", "");
        when(adminRepository.findByUsername(restaurantDTO.getAdminUsername())).thenReturn(admin);

        for (String zone : deliveryZones) {
            when(zoneRepository.findByName(zone)).thenReturn(new DeliveryZone(zone));
        }

        MessageDTO expected = new MessageDTO(true, "Restaurant successfully added!");
        MessageDTO response = restaurantService.save(restaurantDTO);

        Assert.assertEquals(expected, response);
    }

    @Test
    public void testSaveNoZone() {
        List<String> deliveryZones = new ArrayList<>(Arrays.asList("Manastur", "Centru", "Marasti", "Floresti"));
        RestaurantDTO restaurantDTO = new RestaurantDTO("Marty", "Platinia", deliveryZones, "admin1");
        when(restaurantRepository.findByName(restaurantDTO.getName())).thenReturn(null);

        Admin admin = new Admin("admin1", "");
        when(adminRepository.findByUsername(restaurantDTO.getAdminUsername())).thenReturn(admin);

        when(zoneRepository.findByName("Manastur")).thenReturn(null);

        MessageDTO expected = new MessageDTO(false, "Delivery zone "+ "Manastur" +" is not existent!");
        MessageDTO response = restaurantService.save(restaurantDTO);

        Assert.assertEquals(expected, response);
    }
}
