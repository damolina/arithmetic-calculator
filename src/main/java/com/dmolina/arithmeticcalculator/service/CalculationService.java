package com.dmolina.arithmeticcalculator.service;

import com.dmolina.arithmeticcalculator.exception.ResourceNotFoundException;
import com.dmolina.arithmeticcalculator.request.CalculatorRequest;
import com.dmolina.arithmeticcalculator.response.CalculatorResponse;
import com.dmolina.arithmeticcalculator.response.RandomStringResponse;

public interface CalculationService {

    CalculatorResponse calculate(CalculatorRequest calculation) throws ResourceNotFoundException;

    RandomStringResponse generateRandomString(Integer userId) throws ResourceNotFoundException;
}
