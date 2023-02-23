package com.dmolina.arithmeticcalculator.controller;

import com.dmolina.arithmeticcalculator.exception.ResourceNotFoundException;
import com.dmolina.arithmeticcalculator.request.CalculatorRequest;
import com.dmolina.arithmeticcalculator.response.CalculatorResponse;
import com.dmolina.arithmeticcalculator.response.RandomStringResponse;
import com.dmolina.arithmeticcalculator.service.CalculationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class CalculatorController {

    Logger logger = LoggerFactory.getLogger(CalculatorController.class);

    @Autowired
    private CalculationService calculationService;

    @PostMapping("/calculation")
    public CalculatorResponse calculate(@RequestBody CalculatorRequest calculatorRequest) throws ResourceNotFoundException {
        logger.info("CalculatorController::calculate - calculation [{}] for userId: [{}]",
                calculatorRequest.getCalculation(),
                calculatorRequest.getUserId());
        return calculationService.calculate(calculatorRequest);
    }

    @GetMapping("/generate-random-string/{id}")
    public RandomStringResponse generateRandomString(@PathVariable(value="id") Integer userId) throws ResourceNotFoundException {
        logger.info("CalculatorController::generateRandomString for userId: [{}]", userId);
        return calculationService.generateRandomString(userId);
    }
}
