package com.dmolina.arithmeticcalculator.response;

public class BalanceResponse {
    Double credit;

    public BalanceResponse(Double credit) {
        this.credit = credit;
    }

    public Double getCredit() {
        return credit;
    }

    public void setCredit(Double credit) {
        this.credit = credit;
    }
}
