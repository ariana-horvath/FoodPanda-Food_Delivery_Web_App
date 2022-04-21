package app.foodpanda.controller;

import app.foodpanda.dto.AdminDTO;
import app.foodpanda.dto.MessageDTO;
import app.foodpanda.model.Admin;
import app.foodpanda.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PostMapping(value = "/admins")
    MessageDTO registerAdmin(@RequestBody Admin newAdmin) {
        return adminService.save(newAdmin);
    }

    @GetMapping("/admins")
    List<Admin> findAll(){
        return adminService.findAll();
    }

    @GetMapping("/admins/{username}")
    AdminDTO findByUsername(@PathVariable String username) {
        return adminService.findByUsername(username);
    }

    @PostMapping("/admin")
    MessageDTO logInAdmin(@RequestBody Admin admin) {
        return adminService.logInAdmin(admin);
    }
}
