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


    public String invalidEntID(String entity) {
        return "This " + entity + " ID does not exist, please enter a different ID";
    }

    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }

    public UserEntity getUser(int id) {
        if (!userRepository.existsById(id)) {
            throw new ApiRequestException(invalidEntID("user"));
        }
        return userRepository.findById(id).get();
    }

    public ResponseEntity<UserEntity> createUser(UserEntity newUser) {
        if (userRepository.existsById(newUser.getUserID())) {
            throw new ApiRequestException(invalidEntID("user"));
        }
        if (newUser.getFirstName().isBlank()) {
            throw new ApiRequestException("First name must be entered.");
        }
        else userRepository.save(newUser);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    public ResponseEntity<UserEntity> updateUser(
            UserEntity newUser) {
        if (!userRepository.existsById(newUser.getUserID())) {
            throw new ApiRequestException(invalidEntID("user"));
        }
        else {
            newUser.setFirstName(newUser.getFirstName());
            newUser.setFirstName(newUser.getFirstName());
            final UserEntity updatedUser = userRepository.save(newUser);
            return ResponseEntity.ok(updatedUser);
        }
    }

    public ResponseEntity<String> removeUser(
            int id)
    {
        String userDesc = getUser(id).getFirstName();

        if (!userRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        else {
            userRepository.delete(getUser(id));
            return ResponseEntity.status(HttpStatus.OK)
                    .contentType(MediaType.TEXT_PLAIN)
                    .body("User " + id + ": " + "\"" + userDesc +  "\"" + " has been deleted.");
        }
    }
}