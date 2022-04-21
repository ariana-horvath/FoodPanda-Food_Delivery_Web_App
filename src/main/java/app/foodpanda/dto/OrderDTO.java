package app.foodpanda.dto;

import java.util.List;

public class OrderDTO {

    private long orderId;
    private String customer;
    private String restaurant;
    private List<FoodDTO> foods;
    private double price;
    private String date;
    private String time;
    private String status;

    public OrderDTO(long orderId, String customer, String restaurant, List<FoodDTO> foods, double price, String date, String time, String status) {
        this.orderId = orderId;
        this.customer = customer;
        this.restaurant = restaurant;
        this.foods = foods;
        this.price = price;
        this.date = date;
        this.time = time;
        this.status = status;
    }

    public OrderDTO() {
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(String restaurant) {
        this.restaurant = restaurant;
    }

    public List<FoodDTO> getFoods() {
        return foods;
    }

    public void setFoods(List<FoodDTO> foods) {
        this.foods = foods;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
