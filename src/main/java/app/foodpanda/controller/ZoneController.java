package app.foodpanda.controller;

import app.foodpanda.model.DeliveryZone;
import app.foodpanda.service.ZoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class ZoneController {

    @Autowired
    private ZoneService zoneService;

    @PostMapping(value = "/zones")
    DeliveryZone newZone(@RequestBody DeliveryZone newZone){
        return zoneService.save(newZone);
    }

    @GetMapping("/zones")
    List<String> findAll() {
        return zoneService.findAll();
    }
}
