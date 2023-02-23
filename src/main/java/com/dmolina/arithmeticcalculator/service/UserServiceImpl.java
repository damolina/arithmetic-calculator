package com.dmolina.arithmeticcalculator.service;

import com.dmolina.arithmeticcalculator.exception.ResourceNotFoundException;
import com.dmolina.arithmeticcalculator.model.User;
import com.dmolina.arithmeticcalculator.repository.UserRepository;
import com.dmolina.arithmeticcalculator.request.UserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    private static String USER_ACTIVE = "active";
    private static String USER_INACTIVE = "inactive";

    @Autowired
    private UserRepository userRepository;

    @Override
    public User saveUser(UserRequest userRequest) {
        User user = new User(userRequest.getUsername(),userRequest.getPassword());
        user.setStatus(USER_ACTIVE);
        return userRepository.save(user);
    }

    @Override
    public User findByUsernameAndPassword(UserRequest userRequest) throws ResourceNotFoundException {
        User user = new User(userRequest.getUsername(),userRequest.getPassword());
        return userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
    }

    public User findById(Integer userId) throws ResourceNotFoundException {
        return userRepository.findById(userId).orElseThrow(
                ()-> new ResourceNotFoundException("User not found for this id :: " + userId));
    }
}
