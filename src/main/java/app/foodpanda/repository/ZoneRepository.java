package app.foodpanda.repository;

import app.foodpanda.model.DeliveryZone;

public interface ZoneRepository extends AbstractRepository<DeliveryZone> {

    DeliveryZone findByName(String name);
}
