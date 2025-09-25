package com.ast.task2.controller;

import com.ast.task2.domain.User;
import com.ast.task2.service.PollManager;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@CrossOrigin(origins = "*") 
@RestController
@RequestMapping("/users")
public class UserController {

    private final PollManager manager;

    public UserController(PollManager manager) {
        this.manager = manager;
    }

    @GetMapping
    public Collection<User> getAllUsers() {
        return manager.getAllUsers();
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable long id) {
        return manager.getUserById(id).orElse(null);
    }

    // --- POST mit JSON ---
    @PostMapping
    public User createUser(@RequestBody User user) {
        return manager.createUser(user.getUsername(), user.getEmail());
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable long id,
                           @RequestBody User user) {
        return manager.updateUser(id, user.getUsername(), user.getEmail());
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable long id) {
        manager.deleteUser(id);
    }
}
