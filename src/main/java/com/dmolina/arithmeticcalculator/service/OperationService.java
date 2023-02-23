package com.dmolina.arithmeticcalculator.service;
import com.dmolina.arithmeticcalculator.exception.ResourceNotFoundException;
import com.dmolina.arithmeticcalculator.model.Operation;
import com.dmolina.arithmeticcalculator.request.OperationRequest;

import java.util.List;

public interface OperationService {

    List<Operation> getOperations();

    Operation saveOperation(OperationRequest operation)  throws ResourceNotFoundException;

    Operation getOperationById(Integer operationId) throws ResourceNotFoundException;

    Operation getOperationByType(String type) throws ResourceNotFoundException;
}
