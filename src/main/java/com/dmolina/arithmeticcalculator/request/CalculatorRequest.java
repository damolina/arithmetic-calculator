package com.dmolina.arithmeticcalculator.request;

public class CalculatorRequest {
    private Integer UserId;
    private String calculation;

    public Integer getUserId() {
        return UserId;
    }

    public void setUserId(Integer userId) {
        UserId = userId;
    }

    public String getCalculation() {
        return calculation;
    }

    public void setCalculation(String calculation) {
        this.calculation = calculation;
    }
}
