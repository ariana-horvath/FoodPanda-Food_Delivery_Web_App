package app.foodpanda.service;

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
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The type Restaurant service. Used for saving a restaurant, finding all restaurants or adding a category for a
 * restaurant.
 *
 * @author Ariana Horvath
 */
@Service
public class RestaurantService {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private ZoneRepository zoneRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    private static Logger logger = LogManager.getLogger(RestaurantService.class);

    /**
     * Save a restaurant in the database.
     *
     * @param restaurantDTO the restaurant dto
     * @return message dto, containing success boolean and a message
     */
    public MessageDTO save(RestaurantDTO restaurantDTO) {
        Restaurant existentRestaurant = restaurantRepository.findByName(restaurantDTO.getName());
        if (existentRestaurant != null) {
            logger.error("Restaurant name "+ existentRestaurant.getName() + " already existent.");
            return new MessageDTO(false, "Restaurant name already existent! Please choose another one.");
        }

        Restaurant restaurant = new Restaurant();
        Admin admin = adminRepository.findByUsername(restaurantDTO.getAdminUsername());

        if(admin.getRestaurant() != null) {
            logger.error("Admin " + admin.getUsername() + " already has a restaurant.");
            return new MessageDTO(false, "This admin already has a restaurant!");
        }

        List<DeliveryZone> deliveryZones = new ArrayList<>();

        for (String name : restaurantDTO.getDeliveryZonesStrings()){
            DeliveryZone zone = zoneRepository.findByName(name);
            if (zone != null)
                deliveryZones.add(zone);
            else {
                logger.error("Delivery zone "+ name +" is not existent.");
                return new MessageDTO(false, "Delivery zone "+ name +" is not existent!");
            }
        }

        restaurant.setName(restaurantDTO.getName());
        restaurant.setLocation(restaurantDTO.getLocation());
        restaurant.setDeliveryZones(deliveryZones);
        restaurant.setAdmin(admin);

        restaurantRepository.save(restaurant);
        logger.info("Restaurant  " + restaurant.getName() + " successfully added.");
        return new MessageDTO(true, "Restaurant successfully added!");
    }

    /**
     * Find all restaurants from the database.
     *
     * @return list of restaurant DTOs
     */
    public List<RestaurantDTO> findAll() {
        List<RestaurantDTO> restaurants = new ArrayList<>();
        for(Restaurant r : restaurantRepository.findAll()) {
            List<String> deliveryZones = r.getDeliveryZones()
                    .stream()
                    .map(e -> e.getName())
                    .collect(Collectors.toList());
            restaurants.add(new RestaurantDTO(r.getName(), r.getLocation(), deliveryZones, r.getAdmin().getUsername()));
        }
        return restaurants;
    }

    /**
     * Add a new category to an existing restaurant.
     *
     * @param restaurantName the restaurant name
     * @param categoryDTO    the category dto
     * @return message dto, containing success boolean and a message
     */
    public MessageDTO addCategory(String restaurantName, CategoryDTO categoryDTO) {
        Restaurant restaurant = restaurantRepository.findByName(restaurantName);
        if (restaurant == null) {
            logger.error("Restaurant " + restaurantName + " not existent!");
            return new MessageDTO(false, "Restaurant " + restaurantName + " not existent!");
        }

        Category category = categoryRepository.findByName(categoryDTO.getName());
        if (category == null) {
            logger.error("Category " + categoryDTO.getName() + " not existent!");
            return new MessageDTO(false, "Category " + categoryDTO.getName() + " not existent!");
        }

        restaurant.getCategories().add(category);
        restaurantRepository.save(restaurant);
        logger.info("Category "+category.getName()+" successfully added to restaurant" + restaurantName);
        return new MessageDTO(true, "Category successfully added to restaurant "+restaurantName);
    }
}
