package app.foodpanda.service;

import app.foodpanda.model.DeliveryZone;
import app.foodpanda.repository.ZoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Zone service. Used for saving a delivery zone or finding all delivery zones.
 */
@Service
public class ZoneService {

    @Autowired
    private ZoneRepository zoneRepository;

    /**
     * Save a delivery zone to the database.
     *
     * @param deliveryZone the delivery zone
     * @return the method call from repository
     */
    public DeliveryZone save(DeliveryZone deliveryZone) {

        return zoneRepository.save(deliveryZone);
    }

    /**
     * Find all delivery zones from the database.
     *
     * @return the list of delivery zones
     */
    public List<String> findAll() {
        List<String> zones = new ArrayList<>();
        for(DeliveryZone zone: zoneRepository.findAll()) {
            zones.add(zone.getName());
        }
        return zones;
    }
}
