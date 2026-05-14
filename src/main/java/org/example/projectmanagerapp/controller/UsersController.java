package org.example.projectmanagerapp.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.projectmanagerapp.entity.Users;
import org.example.projectmanagerapp.repository.UserRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/users")
@Tag(name = "Users", description = "Operations for managing users")
public class UsersController {
    private final UserRepository userRepository;

    public UsersController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    @Operation(summary = "Get all users", description = "Returns a list of all users")
    public List<Users> getAllUsers() {
        return userRepository.findAll();
    }

    @PostMapping
    @Operation(summary = "Create a user", description = "Adds a new user to the database")
    public Users createUser(@RequestBody Users user) {
        return userRepository.save(user);
    }
}