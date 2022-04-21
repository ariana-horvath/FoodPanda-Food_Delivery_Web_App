package app.foodpanda.dto;

import java.util.List;

public class RestaurantDTO {

    private String name;
    private String location;
    private List<String> deliveryZonesStrings;
    private String adminUsername;

    public RestaurantDTO(String name, String location, List<String> deliveryZones, String admin) {
        this.name = name;
        this.location = location;
        this.deliveryZonesStrings = deliveryZones;
        this.adminUsername = admin;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<String> getDeliveryZonesStrings() {
        return deliveryZonesStrings;
    }

    public void setDeliveryZonesStrings(List<String> deliveryZonesStrings) {
        this.deliveryZonesStrings = deliveryZonesStrings;
    }

    public String getAdminUsername() {
        return adminUsername;
    }

    public void setAdminUsername(String adminUsername) {
        this.adminUsername = adminUsername;
    }
}
