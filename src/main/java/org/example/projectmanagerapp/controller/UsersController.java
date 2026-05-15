package org.example.projectmanagerapp.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.projectmanagerapp.model.Users;
import org.example.projectmanagerapp.service.UserService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/users")
@Tag(name = "User Controller", description = "Zarządzanie użytkowniikami")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    @Operation(summary = "Pobierz użytkowników", description = "Zwraca pełną listę z bazy")
    public List<Users> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping
    @Operation(summary = "Dodaj użytkownika", description = "Tworzy nowego użytkownika")
    public Users createUser(@RequestBody Users user) {
        return userService.saveUser(user);
    }
}