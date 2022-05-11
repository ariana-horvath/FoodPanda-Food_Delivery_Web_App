package app.foodpanda.controller;

import app.foodpanda.dto.AdminDTO;
import app.foodpanda.dto.MessageDTO;
import app.foodpanda.model.Admin;
import app.foodpanda.service.AdminService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class AdminController {

    @Autowired
    private AdminService adminService;

    private static Logger logger = LogManager.getLogger(AdminController.class);


    @PostMapping(value = "/admins")
    MessageDTO registerAdmin(@RequestBody Admin newAdmin) {
        logger.info("Executing the post request for an admin.");
        return adminService.save(newAdmin);
    }

    @GetMapping("/admins/{username}")
    AdminDTO findByUsername(@PathVariable String username) {
        logger.info("Executing the get request for admin "+ username);
        return adminService.findByUsername(username);
    }

    @PostMapping("/admin")
    MessageDTO logInAdmin(@RequestBody Admin admin) {
        logger.info("Executing the log in for admin "+ admin.getUsername());
        return adminService.logInAdmin(admin);
    }
}
