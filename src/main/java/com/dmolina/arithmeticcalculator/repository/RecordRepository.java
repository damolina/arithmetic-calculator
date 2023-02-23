package com.dmolina.arithmeticcalculator.repository;

import com.dmolina.arithmeticcalculator.model.Record;
import com.dmolina.arithmeticcalculator.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecordRepository extends JpaRepository<Record, Integer> {
    List<Record> findByUser(User user);
}
