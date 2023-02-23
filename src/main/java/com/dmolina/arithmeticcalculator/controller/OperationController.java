package com.dmolina.arithmeticcalculator.controller;

import com.dmolina.arithmeticcalculator.exception.ResourceNotFoundException;
import com.dmolina.arithmeticcalculator.model.Operation;
import com.dmolina.arithmeticcalculator.request.OperationRequest;
import com.dmolina.arithmeticcalculator.service.OperationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class OperationController {

    Logger logger = LoggerFactory.getLogger(OperationController.class);

    @Autowired
    private OperationService operationService;

    @GetMapping("/operations")
    public List<Operation> getAllOperations(){
        return operationService.getOperations();
    }

    @PostMapping("/operation")
    public Operation saveOperation(@RequestBody OperationRequest operationRequest) throws ResourceNotFoundException {
        logger.info("OperationController::saveOperation [{}]",operationRequest.getType());
        return operationService.saveOperation(operationRequest);
    }

}
