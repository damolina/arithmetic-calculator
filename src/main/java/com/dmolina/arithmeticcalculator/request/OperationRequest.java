package com.dmolina.arithmeticcalculator.request;

public class OperationRequest {
    private String type;
    private Integer cost;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }
}
