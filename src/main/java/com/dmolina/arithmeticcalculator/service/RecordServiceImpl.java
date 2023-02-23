package com.dmolina.arithmeticcalculator.service;

import com.dmolina.arithmeticcalculator.exception.ResourceNotFoundException;
import com.dmolina.arithmeticcalculator.model.Operation;
import com.dmolina.arithmeticcalculator.model.Record;
import com.dmolina.arithmeticcalculator.model.User;
import com.dmolina.arithmeticcalculator.repository.RecordRepository;
import com.dmolina.arithmeticcalculator.request.RecordRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RecordServiceImpl implements RecordService{

    @Autowired
    private RecordRepository recordRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private OperationService operationService;

    @Override
    public List<Record> getRecords() {
        return recordRepository.findAll();
    }

    @Override
    public Record saveRecord(RecordRequest recordRequest) throws ResourceNotFoundException {
        User user = userService.findById(recordRequest.getUserId());
        Operation operation = operationService.getOperationById(recordRequest.getOperationId());
        Record record = new Record(operation, recordRequest.getOperationCalculated(), user,recordRequest.getAmount(),recordRequest.getUserBalance(),recordRequest.getOperationResponse());
        record.setDate(LocalDateTime.now());
        return recordRepository.save(record);
    }

    @Override
    public List<Record> getRecordsByUserId(Integer userId) throws ResourceNotFoundException {
        User user = userService.findById(userId);
        return recordRepository.findByUser(user);
    }
}
