package app.foodpanda.repository;

import app.foodpanda.model.Admin;

public interface AdminRepository extends  AbstractRepository<Admin>{

    Admin findByUsername(String username);
}
