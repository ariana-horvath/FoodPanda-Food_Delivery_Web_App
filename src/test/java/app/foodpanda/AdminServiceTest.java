package app.foodpanda;

import app.foodpanda.dto.AdminDTO;
import app.foodpanda.dto.MessageDTO;
import app.foodpanda.model.Admin;
import app.foodpanda.model.Restaurant;
import app.foodpanda.repository.AdminRepository;
import app.foodpanda.service.AdminService;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.junit.runner.RunWith;
import org.springframework.security.crypto.bcrypt.BCrypt;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AdminServiceTest {

    @InjectMocks
    private AdminService adminService;

    @Mock
    private AdminRepository adminRepository;

    @Test
    public void testSaveError() {
        Admin admin = new Admin("admin1", "admin1");
        when(adminRepository.findByUsername("admin1")).thenReturn(admin);

        MessageDTO expectedResponse = new MessageDTO(false, "Admin with this username already existent! " +
                "Please choose another one.");
        MessageDTO response = adminService.save(admin);
        Assert.assertEquals(expectedResponse, response);

    }

    @Test
    public void testSaveOk() {
        Admin admin = new Admin("admin", "admin");
        when(adminRepository.findByUsername("admin")).thenReturn(null);

        MessageDTO expectedResponse = new MessageDTO(true, "Admin successfully added.");
        MessageDTO response = adminService.save(admin);
        Assert.assertEquals(expectedResponse, response);
    }

    @Test
    public void testLogInError() {
        Admin admin = new Admin();
        admin.setUsername("admin");
        admin.setPassword(BCrypt.hashpw("admin", BCrypt.gensalt()));
        when(adminRepository.findByUsername("admin")).thenReturn(null);

        MessageDTO expectedResponse = new MessageDTO(false, "Admin not existent!");
        MessageDTO response = adminService.logInAdmin(admin);
        Assert.assertEquals(expectedResponse, response);
    }

    @Test
    public void testLogInError2() {
        Admin admin = new Admin("admin1", "blablabla");

        Admin existentAdmin = new Admin();
        existentAdmin.setUsername("admin1");
        existentAdmin.setPassword(BCrypt.hashpw("admin1", BCrypt.gensalt()));

        when(adminRepository.findByUsername("admin1")).thenReturn(existentAdmin);

        MessageDTO expectedResponse = new MessageDTO(false, "Incorrect Password!");
        MessageDTO response = adminService.logInAdmin(admin);
        Assert.assertEquals(expectedResponse, response);
    }

    @Test
    public void testLogInOk() {
        Admin admin = new Admin("admin1", "admin1");

        Admin existentAdmin = new Admin();
        existentAdmin.setUsername("admin1");
        existentAdmin.setPassword(BCrypt.hashpw("admin1", BCrypt.gensalt()));

        when(adminRepository.findByUsername("admin1")).thenReturn(existentAdmin);

        MessageDTO expectedResponse = new MessageDTO(true, "Admin logged in successfully.");
        MessageDTO response = adminService.logInAdmin(admin);
        Assert.assertEquals(expectedResponse, response);
    }

    @Test
    public void testFindByUsername() {
        Admin admin = new Admin("admin1", "admin1");
        Restaurant restaurant = new Restaurant();
        restaurant.setName("Marty Platinia");
        admin.setRestaurant(restaurant);

        when(adminRepository.findByUsername("admin1")).thenReturn(admin);

        AdminDTO expectedAdmin = new AdminDTO("admin1", "Marty Platinia");
        AdminDTO returnedAdmin = adminService.findByUsername("admin1");

        Assert.assertEquals(expectedAdmin, returnedAdmin);
    }

    @Test
    public void testFindByUsernameWithoutRestaurant() {
        Admin admin = new Admin("admin1", "admin1");

        when(adminRepository.findByUsername("admin1")).thenReturn(admin);

        AdminDTO expectedAdmin = new AdminDTO("admin1", "");
        AdminDTO returnedAdmin = adminService.findByUsername("admin1");

        Assert.assertEquals(expectedAdmin, returnedAdmin);
    }
}
