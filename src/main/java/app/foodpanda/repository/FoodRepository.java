package app.foodpanda.repository;

import app.foodpanda.model.Category;
import app.foodpanda.model.Food;
import app.foodpanda.model.Restaurant;

import java.util.List;

public interface FoodRepository extends AbstractRepository<Food> {

    List<Food> findAllByRestaurantAndCategory(Restaurant restaurant, Category category);

    Food findByRestaurantAndName(Restaurant restaurant, String name);
}
