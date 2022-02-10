package com.company.assets.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping
public class UserController {

    @Autowired
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/users")
    public List<UserEntity> getAll(){
        return userService.getAllUsers();
    }

    @GetMapping("/users/get/{id}")
    public UserEntity getUser(
            @PathVariable(value = "id") int id)
    {
        return  userService.getUser(id);
    }

    @PostMapping("/users/add")
    public ResponseEntity<UserEntity> createUser(
            @Valid @RequestBody UserEntity newUser){
        return userService.createUser(newUser);
    }

    @PutMapping("/users/update")
    public ResponseEntity<UserEntity> updateUser(
            @RequestBody UserEntity newUser) {
        return userService.updateUser(newUser);
    }


    @DeleteMapping("/users/remove/{id}")
    public ResponseEntity<String> removeUser(
            @PathVariable(value = "id") int id) {
        return userService.removeUser(id);
    }



    }
