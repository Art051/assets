package com.company.assets.users;

import com.company.assets.exceptions.ApiRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public String invalidQuery(String entity, String field) {
        return "This " + entity + " " + field + " does not exist, please enter a valid query.";
    }


    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }

    public UserEntity getUserByID(int id) {
        if (!userRepository.existsById(id)) {
            throw new ApiRequestException(invalidQuery("user", "ID"));
        }
        else {
            return userRepository.findById(id).get();
        }
    }

    public List<UserEntity> getUsersByFirstName(String firstName) {
        if (userRepository.findByFirstNameIgnoreCase(firstName).isEmpty()) {
            throw new ApiRequestException(invalidQuery("user", "first name"));
        }
        else {
            return userRepository.findByFirstNameIgnoreCase(firstName);
        }
    }

    public ResponseEntity<UserEntity> createUser(UserEntity newUser) {
        if (userRepository.existsById(newUser.getUserID())) {
            throw new ApiRequestException(invalidQuery("user","ID"));
        }
        if (newUser.getFirstName().isBlank()) {
            throw new ApiRequestException("First name must be entered.");
        }
        else {
            userRepository.save(newUser);
            return new ResponseEntity<>(newUser, HttpStatus.CREATED);
        }
    }

    public ResponseEntity<UserEntity> updateUser(
            UserEntity newUser) {
        if (!userRepository.existsById(newUser.getUserID())) {
            throw new ApiRequestException(invalidQuery("user","ID"));
        }
        else {
            newUser.setFirstName(newUser.getFirstName());
            newUser.setFirstName(newUser.getFirstName());
            final UserEntity updatedUser = userRepository.save(newUser);
            return ResponseEntity.ok(updatedUser);
        }
    }

    public ResponseEntity<String> removeUser(int id) {
        String userDesc = getUserByID(id).getFirstName();
        if (!userRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        else {
            userRepository.delete(getUserByID(id));
            return ResponseEntity.status(HttpStatus.OK)
                    .contentType(MediaType.TEXT_PLAIN)
                    .body("User " + id + ": " + "\"" + userDesc +  "\"" + " has been deleted.");
        }
    }
}