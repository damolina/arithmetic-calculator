package com.dmolina.arithmeticcalculator.service;

import com.dmolina.arithmeticcalculator.exception.ResourceNotFoundException;
import com.dmolina.arithmeticcalculator.model.User;
import com.dmolina.arithmeticcalculator.request.UserRequest;

import java.util.Optional;

public interface UserService {
    User saveUser(UserRequest user) throws ResourceNotFoundException;

    User findByUsernameAndPassword(UserRequest user) throws ResourceNotFoundException;

    User findById(Integer userId) throws ResourceNotFoundException;
}
