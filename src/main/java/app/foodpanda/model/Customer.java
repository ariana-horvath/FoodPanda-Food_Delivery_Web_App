package app.foodpanda.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "customer")
public class Customer extends User{

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Order> orders;

    public Customer(String username, String password) {
        super(username, password);
    }

    public Customer() {
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
