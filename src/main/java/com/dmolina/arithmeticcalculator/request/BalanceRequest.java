package com.dmolina.arithmeticcalculator.request;

public class BalanceRequest {
    private Integer userId;
    private Double credit;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Double getCredit() {
        return credit;
    }

    public void setCredit(Double credit) {
        this.credit = credit;
    }
}
