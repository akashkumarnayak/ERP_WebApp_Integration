package org.integration.erp.services;

import org.integration.erp.entities.User;
import org.integration.erp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public User getUser(String username) {

        Optional<User> userOptional = userRepository.findUserByUsername(username);
        if (userOptional.isPresent()) {
            return userOptional.get();
        }
        return null;
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }
}
