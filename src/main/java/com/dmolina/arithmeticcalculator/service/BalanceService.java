package com.dmolina.arithmeticcalculator.service;

import com.dmolina.arithmeticcalculator.exception.ResourceNotFoundException;
import com.dmolina.arithmeticcalculator.model.Balance;

public interface BalanceService {

    Balance getBalanceByUserId(Integer userId) throws ResourceNotFoundException;

    Balance updateUserBalance(Integer userId, Double credit) throws ResourceNotFoundException;
}
