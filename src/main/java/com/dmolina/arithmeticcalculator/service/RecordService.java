package com.dmolina.arithmeticcalculator.service;

import com.dmolina.arithmeticcalculator.exception.ResourceNotFoundException;
import com.dmolina.arithmeticcalculator.model.Record;
import com.dmolina.arithmeticcalculator.request.RecordRequest;

import java.util.List;

public interface RecordService {

    List<Record> getRecords();

    Record saveRecord(RecordRequest recordRequest) throws ResourceNotFoundException;

    List<Record> getRecordsByUserId(Integer userId) throws ResourceNotFoundException;
}
