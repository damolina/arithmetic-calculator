package com.dmolina.arithmeticcalculator.service;

import com.dmolina.arithmeticcalculator.exception.ResourceNotFoundException;
import com.dmolina.arithmeticcalculator.model.Operation;
import com.dmolina.arithmeticcalculator.repository.OperationRepository;
import com.dmolina.arithmeticcalculator.request.OperationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OperationServiceImpl implements OperationService{

    @Autowired
    private OperationRepository operationRepository;

    @Override
    public List<Operation> getOperations() {
        return operationRepository.findAll();
    }

    @Override
    public Operation saveOperation(OperationRequest operationRequest) {
        Operation operation = new Operation(operationRequest.getType(), operationRequest.getCost());
        return operationRepository.save(operation);
    }

    @Override
    public Operation getOperationById(Integer operationId) throws ResourceNotFoundException {
        return operationRepository.findById(operationId).orElseThrow(
                ()-> new ResourceNotFoundException("Operation not found for this id :: " + operationId));
    }

    @Override
    public Operation getOperationByType(String type) throws ResourceNotFoundException {
        return operationRepository.findOperationByType(type);
    }
}
