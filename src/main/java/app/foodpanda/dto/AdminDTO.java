package app.foodpanda.dto;

public class AdminDTO {

    private String username;
    private String restaurantName;

    public AdminDTO(String username, String restaurantName) {
        this.username = username;
        this.restaurantName = restaurantName;
    }

    public AdminDTO() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }
}
