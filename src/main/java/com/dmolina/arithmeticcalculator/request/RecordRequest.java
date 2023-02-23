package com.dmolina.arithmeticcalculator.request;

public class RecordRequest {
    private Integer operationId;
    private String operationCalculated;
    private Integer userId;
    private Double amount;
    private Double userBalance;
    private String operationResponse;

    public RecordRequest(Integer operationId, String operation, Integer userId, Double amount, Double userBalance, String operationResponse) {
        this.operationId = operationId;
        this.operationCalculated = operation;
        this.userId = userId;
        this.amount = amount;
        this.userBalance = userBalance;
        this.operationResponse = operationResponse;
    }

    public Integer getOperationId() {
        return operationId;
    }

    public void setOperationId(Integer operationId) {
        this.operationId = operationId;
    }

    public String getOperationCalculated() {
        return operationCalculated;
    }

    public void setOperationCalculated(String operationCalculated) {
        this.operationCalculated = operationCalculated;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Double getUserBalance() {
        return userBalance;
    }

    public void setUserBalance(Double userBalance) {
        this.userBalance = userBalance;
    }

    public String getOperationResponse() {
        return operationResponse;
    }

    public void setOperationResponse(String operationResponse) {
        this.operationResponse = operationResponse;
    }
}
