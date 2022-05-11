package app.foodpanda.service;

import app.foodpanda.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * The type User service.
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
}
