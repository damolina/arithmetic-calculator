package com.dmolina.arithmeticcalculator.response;

public class RandomStringResponse {
    private String hash;

    public RandomStringResponse(String hash) {
        this.hash = hash;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }
}
