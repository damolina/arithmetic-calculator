package com.dmolina.arithmeticcalculator.controller;

import com.dmolina.arithmeticcalculator.exception.ResourceNotFoundException;
import com.dmolina.arithmeticcalculator.model.User;
import com.dmolina.arithmeticcalculator.request.UserRequest;
import com.dmolina.arithmeticcalculator.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/api/v1")
public class UserController {

    Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @PostMapping("/user")
    public User createUser(@RequestBody UserRequest userRequest) throws ResourceNotFoundException {
        logger.info("UserController::createUser");
        return userService.saveUser(userRequest);
    }
    @CrossOrigin(origins = "https://calculator-frontend.herokuapp.com", allowedHeaders = "Requestor-Type")
    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody UserRequest userRequest) throws ResourceNotFoundException {
        logger.info("UserController::login");
        User user = userService.findByUsernameAndPassword(userRequest);
        if(user != null){
            return ResponseEntity.ok().body(user);
        }else {
            return ResponseEntity.notFound().build();
        }
    }
}