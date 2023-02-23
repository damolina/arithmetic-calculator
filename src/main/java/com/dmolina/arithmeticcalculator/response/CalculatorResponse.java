package com.dmolina.arithmeticcalculator.response;

public class CalculatorResponse {
    private String operationCalculated;
    private Double resultOperationCalculated;
    private Double initialUserBalance;
    private Double totalAmountSpent;
    private Double finalUserBalance;

    private String message;

    public CalculatorResponse(String operationCalculated, Double resultOperationCalculated, Double initialUserBalance, Double totalAmountSpent, Double finalUserBalance, String message) {
        this.operationCalculated = operationCalculated;
        this.resultOperationCalculated = resultOperationCalculated;
        this.initialUserBalance = initialUserBalance;
        this.totalAmountSpent = totalAmountSpent;
        this.finalUserBalance = finalUserBalance;
        this.message = message;
    }

    public String getOperationCalculated() {
        return operationCalculated;
    }

    public void setOperationCalculated(String operationCalculated) {
        this.operationCalculated = operationCalculated;
    }

    public Double getResultOperationCalculated() {
        return resultOperationCalculated;
    }

    public void setResultOperationCalculated(Double resultOperationCalculated) {
        this.resultOperationCalculated = resultOperationCalculated;
    }

    public Double getInitialUserBalance() {
        return initialUserBalance;
    }

    public void setInitialUserBalance(Double initialUserBalance) {
        this.initialUserBalance = initialUserBalance;
    }

    public Double getTotalAmountSpent() {
        return totalAmountSpent;
    }

    public void setTotalAmountSpent(Double totalAmountSpent) {
        this.totalAmountSpent = totalAmountSpent;
    }

    public Double getFinalUserBalance() {
        return finalUserBalance;
    }

    public void setFinalUserBalance(Double finalUserBalance) {
        this.finalUserBalance = finalUserBalance;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
