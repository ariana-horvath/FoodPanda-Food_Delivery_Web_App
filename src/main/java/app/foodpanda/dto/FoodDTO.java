package app.foodpanda.dto;

public class FoodDTO {

    private String name;
    private String description;
    private double price;
    private String category;
    private String restaurant;

    public FoodDTO(String name, String description, double price, String category, String restaurant) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
        this.restaurant = restaurant;
    }

    public FoodDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(String restaurant) {
        this.restaurant = restaurant;
    }
}
