package com.dmolina.arithmeticcalculator.client;

import feign.RequestLine;

public interface RandomStringGeneratorClient {
    @RequestLine("GET")
    String generateRandomString();
}
