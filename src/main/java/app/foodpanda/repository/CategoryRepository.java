package app.foodpanda.repository;

import app.foodpanda.model.Category;

public interface CategoryRepository extends AbstractRepository<Category> {

    Category findByName(String name);
}
