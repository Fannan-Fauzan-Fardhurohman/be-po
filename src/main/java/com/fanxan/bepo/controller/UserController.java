package com.fanxan.bepo.controller;

import com.fanxan.bepo.exception.ResourceNotFoundException;
import com.fanxan.bepo.models.WebResponse;
import com.fanxan.bepo.models.entity.Users;
import com.fanxan.bepo.models.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public WebResponse<List<Users>> getAllUsers() {
        return new WebResponse<>(true, "Success", userService.getAllUsers());
    }

    @GetMapping("/{id}")
    public WebResponse<Users> getUserById(@PathVariable Integer id) {
        Users user = userService.getUserById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
        return new WebResponse<>(true, "Success", user);
    }

    @PostMapping
    public WebResponse<Users> createUser(@RequestBody Users user) {
        Users createdUser = userService.createUser(user);
        return new WebResponse<>(true, "User created successfully", createdUser);
    }

    @PutMapping("/{id}")
    public WebResponse<Users> updateUser(@PathVariable Integer id, @RequestBody Users userDetails) {
        Users updatedUser = userService.updateUser(id, userDetails)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
        return new WebResponse<>(true, "User updated successfully", updatedUser);
    }

    @DeleteMapping("/{id}")
    public WebResponse<Void> deleteUser(@PathVariable Integer id) {
        if (!userService.deleteUser(id)) {
            throw new ResourceNotFoundException("User not found with id: " + id);
        }
        return new WebResponse<>(true, "User deleted successfully", null);
    }
}