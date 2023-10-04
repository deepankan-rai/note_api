package com.example.spring.controller;


import com.example.spring.entity.UserDtls;
import com.example.spring.services.UserDtlsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserDtlsController {

	@Autowired
    private  UserDtlsService userService;

    
    public UserDtlsController(UserDtlsService userService) {
        this.userService = userService;
    }

    @PostMapping("/add")
    public ResponseEntity<UserDtls> addUser(@RequestBody UserDtls user) {
        UserDtls newUser = userService.addUser(user);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    @PutMapping("/update/{userId}")
    public ResponseEntity<UserDtls> updateUser(@PathVariable int userId, @RequestBody UserDtls user) {
        user.setId(userId); // Ensure the user ID is set
        UserDtls updatedUser = userService.updateUser(user);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @DeleteMapping("/remove/{userId}")
    public ResponseEntity<String> removeUser(@PathVariable int userId) {
        userService.removeUser(userId);
        return new ResponseEntity<>("User with ID " + userId + " has been removed.", HttpStatus.NO_CONTENT);
    }
}
