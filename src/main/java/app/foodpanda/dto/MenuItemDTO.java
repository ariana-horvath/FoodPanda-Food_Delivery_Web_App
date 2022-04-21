package app.foodpanda.dto;

import java.util.List;

public class MenuItemDTO {

    private String category;
    private List<FoodDTO> foods;

    public MenuItemDTO(String category, List<FoodDTO> foods) {
        this.category = category;
        this.foods = foods;
    }

    public MenuItemDTO() {
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public List<FoodDTO> getFoods() {
        return foods;
    }

    public void setFoods(List<FoodDTO> foods) {
        this.foods = foods;
    }
}
