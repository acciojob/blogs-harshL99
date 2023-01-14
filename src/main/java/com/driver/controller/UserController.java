package com.driver.controller;

import com.driver.models.User;
import com.driver.repositories.UserRepository;
import com.driver.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping("/create")
    public ResponseEntity<Void> createUser(@RequestBody User user) {
        service.createUser(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable int userId) {
        service.deleteUser(userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/update")
    public ResponseEntity<Void> updateUser(@RequestBody User user) {
        service.updateUser(user);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/find/{username}")
    public ResponseEntity<User> findUserByUsername(@PathVariable String username) {
        User user=service.findUserByUsername(username);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
