package app.foodpanda.service;

import app.foodpanda.dto.AdminDTO;
import app.foodpanda.dto.MessageDTO;
import app.foodpanda.model.Admin;
import app.foodpanda.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

/**
 * The type Admin service. Used for saving or logging in an admin, finding an admin by username or finding all admins.
 *
 * @author Ariana Horvath
 */
@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    private static Logger logger = LogManager.getLogger(AdminService.class);

    /**
     * Save a new admin to the database
     *
     * @param admin the admin to be added to the database (username and password)
     * @return message dto, containing success boolean and a message
     */
    public MessageDTO save(Admin admin) {
        if (adminRepository.findByUsername(admin.getUsername()) != null) {
            logger.error("Admin with username " + admin.getUsername() + " already existent.");
            return new MessageDTO(false,
                                  "Admin with this username already existent! Please choose another one.");
        }
        else {
            admin.setPassword(BCrypt.hashpw(admin.getPassword(), BCrypt.gensalt()));
            adminRepository.save(admin);
            logger.info("A new admin was created with username: " + admin.getUsername());
            return new MessageDTO(true,"Admin successfully added.");
        }
    }

    /**
     * Log in an admin.
     *
     * @param admin the admin to be logged in
     * @return the message DTO, containing success boolean and a message
     */
    public MessageDTO logInAdmin(Admin admin) {
        Admin existentAdmin = adminRepository.findByUsername(admin.getUsername());
        if (existentAdmin == null) {
            logger.error("Admin " + admin.getUsername() + " not existent.");
            return new MessageDTO(false, "Admin not existent!");
        }
        else
        if (!BCrypt.checkpw(admin.getPassword(), existentAdmin.getPassword())) {
            logger.error("Incorrect password for admin: " + admin.getUsername());
            return new MessageDTO(false, "Incorrect Password!");
        }
        else {
            logger.info("Admin " + admin.getUsername() + " logged in successfully.");
            return new MessageDTO(true, "Admin logged in successfully.");
        }
    }

    /**
     * Find by username the admin.
     *
     * @param username the username of the admin to be found
     * @return admin DTO, containing admin username and its restaurant's name
     */
    public AdminDTO findByUsername(String username) {
        Admin admin = adminRepository.findByUsername(username);
        if (admin.getRestaurant() != null) {
            logger.info("Admin has its restaurant.");
            return new AdminDTO(admin.getUsername(), admin.getRestaurant().getName());
        }
        else {
            logger.info("Admin has no restaurant yet.");
            return new AdminDTO(admin.getUsername(), "");
        }
    }
}
