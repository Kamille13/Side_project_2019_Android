package com.sideproject2019.Side_Project_2019.controller;

import com.sideproject2019.Side_Project_2019.model.User;
import com.sideproject2019.Side_Project_2019.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserRepository userRepository;

    @PostMapping("/users/create")
    public User createUser(@RequestBody User user) {
        return userRepository.save(user);
    }

    @PostMapping("/users/login")
    public User login(@RequestBody User user) {
        return userRepository.findByEmailAndPassword(user.getEmail(), user.getPassword());
    }

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
