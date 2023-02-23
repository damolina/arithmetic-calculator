package com.dmolina.arithmeticcalculator.controller;

import com.dmolina.arithmeticcalculator.exception.ResourceNotFoundException;
import com.dmolina.arithmeticcalculator.model.Record;
import com.dmolina.arithmeticcalculator.service.RecordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class RecordController {

    Logger logger = LoggerFactory.getLogger(RecordController.class);

    @Autowired
    private RecordService recordService;

    @GetMapping("/records")
    public List<Record> getAllRecords(){
        logger.info("RecordController::getAllRecords");
        return recordService.getRecords();
    }

    @GetMapping("/records/{id}")
    public List<Record> getAllRecordsByUserId(@PathVariable(value="id") Integer userId) throws ResourceNotFoundException {
        logger.info("RecordController::getAllRecordsByUserId");
        return recordService.getRecordsByUserId(userId);
    }

}
