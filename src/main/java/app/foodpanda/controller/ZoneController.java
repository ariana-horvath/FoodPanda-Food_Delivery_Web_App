package app.foodpanda.controller;

import app.foodpanda.model.DeliveryZone;
import app.foodpanda.service.ZoneService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class ZoneController {

    @Autowired
    private ZoneService zoneService;
    private static Logger logger = LogManager.getLogger(ZoneController.class);

    @PostMapping(value = "/zones")
    DeliveryZone newZone(@RequestBody DeliveryZone newZone){
        logger.info("Executing the post request for a zone.");
        return zoneService.save(newZone);
    }

    @GetMapping("/zones")
    List<String> findAll() {
        logger.info("Executing the get request for zones.");
        return zoneService.findAll();
    }
}
