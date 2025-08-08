package com.example.appointment.controllers;

import com.example.appointment.entities.User;
import com.example.appointment.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import org.springframework.data.domain.Pageable;


@RestController
@RequestMapping("/api/users")
@Validated
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping
    public Page<User> getAllUsers(@PageableDefault(size = 10) Pageable pageable) {
        return userService.getAllUsers(pageable);
    }

//    @GetMapping
//    public List<User> getAllUsers() {
//        return userService.getAllUsers();
//    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody @Valid User user) {
        User created = userService.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }
}
