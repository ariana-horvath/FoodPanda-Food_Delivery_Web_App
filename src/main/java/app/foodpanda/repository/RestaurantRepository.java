package app.foodpanda.repository;

import app.foodpanda.model.Restaurant;

public interface RestaurantRepository extends AbstractRepository<Restaurant> {

    Restaurant findByName(String name);
}
