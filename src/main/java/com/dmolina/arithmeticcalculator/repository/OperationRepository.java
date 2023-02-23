package com.dmolina.arithmeticcalculator.repository;

import com.dmolina.arithmeticcalculator.exception.ResourceNotFoundException;
import com.dmolina.arithmeticcalculator.model.Operation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OperationRepository extends JpaRepository<Operation,Integer> {

    Operation findOperationByType(String type) throws ResourceNotFoundException;
}
