package com.dmolina.arithmeticcalculator.service;

import com.dmolina.arithmeticcalculator.exception.ResourceNotFoundException;
import com.dmolina.arithmeticcalculator.model.Balance;
import com.dmolina.arithmeticcalculator.model.User;
import com.dmolina.arithmeticcalculator.repository.BalanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BalanceServiceImpl implements BalanceService{

    @Autowired
    private BalanceRepository balanceRepository;

    @Autowired
    private UserService userService;

    @Override
    public Balance getBalanceByUserId(Integer userId) throws ResourceNotFoundException {
        User user = userService.findById(userId);
        return balanceRepository.findByUser(user);
    }

    @Override
    public Balance updateUserBalance(Integer userId, Double credit) throws ResourceNotFoundException {
        User user = userService.findById(userId);
        Balance balance = balanceRepository.findByUser(user);
        balance.setCredit(credit);
        return balanceRepository.save(balance);
    }
}
