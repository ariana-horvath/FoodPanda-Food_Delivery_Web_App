package app.foodpanda;

import app.foodpanda.model.DeliveryZone;
import app.foodpanda.repository.ZoneRepository;
import app.foodpanda.service.ZoneService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.*;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ZoneServiceTest {

    @InjectMocks
    private ZoneService zoneService;

    @Mock
    private ZoneRepository zoneRepository;

    @Test
    public void testSave() {
        DeliveryZone deliveryZone = new DeliveryZone("Manastur");
        when(zoneRepository.save(deliveryZone)).thenReturn(deliveryZone);

        DeliveryZone returnedZone = zoneService.save(deliveryZone);
        Assert.assertEquals(deliveryZone.getName(), returnedZone.getName());
    }

    @Test
    public void testFindAll() {
        List<DeliveryZone> deliveryZones = new ArrayList<>(Arrays.asList(new DeliveryZone("Manastur"),
                new DeliveryZone("Centru"),
                new DeliveryZone("Marasti"),
                new DeliveryZone("Floresti")));
        when(zoneRepository.findAll()).thenReturn(deliveryZones);

        List<String> zoneDTOS = zoneService.findAll();

        deliveryZones.sort(Comparator.comparing(DeliveryZone::getName));
        Collections.sort(zoneDTOS);
        boolean equals = true;

        if (deliveryZones.size() != zoneDTOS.size())
            equals = false;
        else {
            for (int i = 0; i < deliveryZones.size(); i++) {
                if (deliveryZones.get(i).getName().compareTo(zoneDTOS.get(i)) != 0)
                    equals = false;
            }
        }

        Assert.assertTrue(equals);
    }
}
