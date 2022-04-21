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

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    public MessageDTO save(Admin admin) {
        if (adminRepository.findByUsername(admin.getUsername()) != null) {
            return new MessageDTO(false,"Admin with this username already existent! Please choose another one.");
        }
        else {
            admin.setPassword(BCrypt.hashpw(admin.getPassword(), BCrypt.gensalt()));
            adminRepository.save(admin);
            return new MessageDTO(true,"Admin succesfully added");
        }
    }

    public List<Admin> findAll() {
        List<Admin> admins = new ArrayList<>();
        adminRepository.findAll().forEach(admins::add);
        return admins;
    }

    public MessageDTO logInAdmin(Admin admin) {
        Admin existentAdmin = adminRepository.findByUsername(admin.getUsername());
        if (existentAdmin == null)
            return new MessageDTO(false, "Admin not existent!");
        else
        if (!BCrypt.checkpw(admin.getPassword(), existentAdmin.getPassword()))
            return new MessageDTO(false, "Incorrect Password!");
        else
            return new MessageDTO(true, "Log in successfully");
    }

    public AdminDTO findByUsername(String username) {
        Admin admin = adminRepository.findByUsername(username);
        if (admin.getRestaurant() != null)
            return new AdminDTO(admin.getUsername(), admin.getRestaurant().getName());
        else
            return new AdminDTO(admin.getUsername(), "");
    }
}
