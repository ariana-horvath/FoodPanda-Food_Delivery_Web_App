package app.foodpanda.model;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "admin")
public class Admin extends User{

    @OneToOne(mappedBy = "admin")
    private Restaurant restaurant;

    public Admin(String username, String password) {
        super(username, password);
    }

    public Admin() {
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }
}
