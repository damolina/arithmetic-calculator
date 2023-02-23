package com.dmolina.arithmeticcalculator.repository;

import com.dmolina.arithmeticcalculator.model.Balance;
import com.dmolina.arithmeticcalculator.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BalanceRepository extends JpaRepository<Balance, Integer> {
    Balance findByUser(User user);
}
