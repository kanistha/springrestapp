package com.service;

import com.model.User;
import com.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User getUser(long id) {
        return userRepository.findOne(id);
    }

    public User save(User user) {
        User userToSave = user;
        userToSave.setLastName("Kanistha");
        return userRepository.save(user);
    }

    public void delete(long id) {

    }
}
