package app.foodpanda.service;

import app.foodpanda.model.DeliveryZone;
import app.foodpanda.repository.ZoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ZoneService {

    @Autowired
    private ZoneRepository zoneRepository;

    public DeliveryZone save(DeliveryZone deliveryZone) {
        return zoneRepository.save(deliveryZone);
    }

    public List<String> findAll() {
        List<String> zones = new ArrayList<>();
        for(DeliveryZone zone: zoneRepository.findAll()) {
            zones.add(zone.getName());
        }
        return zones;
    }
}
