package com.company.assets.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping
    public List<UserEntity> getAll() {
        return userService.getAllUsers();
    }

    @GetMapping("/get/id/{id}")
    public UserEntity getUserByID(
            @PathVariable(value = "id") int id) {
        return userService.getUserByID(id);
    }

    @GetMapping("/get/name/{first_name}")
    public List<UserEntity> getUserByFirstName(
            @PathVariable(value = "first_name") String firstName) {
        return userService.getUsersByFirstName(firstName);
    }

    @PostMapping("/add")
    public ResponseEntity<UserEntity> createUser(
            @Valid @RequestBody UserEntity newUser) {
        return userService.createUser(newUser);
    }

    @PutMapping("/update")
    public ResponseEntity<UserEntity> updateUser(
            @RequestBody UserEntity newUser) {
        return userService.updateUser(newUser);
    }

    @DeleteMapping("/remove/{id}")
    public ResponseEntity<String> removeUser(
            @PathVariable(value = "id") int id) {
        return userService.removeUser(id);
    }
}