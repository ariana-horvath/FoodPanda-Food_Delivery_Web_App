package app.foodpanda.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "delivery_zone")
public class DeliveryZone {

    @Id
    @Column(name = "zone_id", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long zoneId;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @ManyToMany(mappedBy = "deliveryZones", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Restaurant> restaurants;

    public DeliveryZone(String name) {
        this.name = name;
    }

    public DeliveryZone() {
    }

    public Long getZoneId() {
        return zoneId;
    }

    public void setZoneId(Long zoneId) {
        this.zoneId = zoneId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Restaurant> getRestaurants() {
        return restaurants;
    }

    public void setRestaurants(List<Restaurant> restaurants) {
        this.restaurants = restaurants;
    }
}
