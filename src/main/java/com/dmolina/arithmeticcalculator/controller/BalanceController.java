package com.dmolina.arithmeticcalculator.controller;

import com.dmolina.arithmeticcalculator.exception.ResourceNotFoundException;
import com.dmolina.arithmeticcalculator.model.Balance;
import com.dmolina.arithmeticcalculator.request.BalanceRequest;
import com.dmolina.arithmeticcalculator.response.BalanceResponse;
import com.dmolina.arithmeticcalculator.service.BalanceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
@RequestMapping("/api/v1")
public class BalanceController {

    Logger logger = LoggerFactory.getLogger(BalanceController.class);

    @Autowired
    private BalanceService balanceService;

    @PostMapping("/update-user-balance")
    public BalanceResponse updateUserBalance(@RequestBody BalanceRequest balanceRequest) throws ResourceNotFoundException {
        logger.info("BalanceController::updateUserBalance");
        Balance balance = balanceService.updateUserBalance(balanceRequest.getUserId(),balanceRequest.getCredit());
        BalanceResponse balanceResponse = new BalanceResponse(balance.getCredit());
        return balanceResponse;
    }

}
