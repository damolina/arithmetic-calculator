package com.dmolina.arithmeticcalculator;

import com.dmolina.arithmeticcalculator.request.BalanceRequest;
import com.dmolina.arithmeticcalculator.request.CalculatorRequest;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CalculatorControllerTest extends AbstractTest{
    String uri = "/api/v1/";
    int userId = 3;
    Double initialUserBalance = 100.0;
    String criteriaResponse = "Enough User balance";

    @Override
    @Before
    public void setUp() {
        super.setUp();
    }

    @Test
    @Before
    public void updateUserBalanceTest() throws Exception {
        String path = uri.concat("/update-user-balance");
        BalanceRequest balanceRequest = new BalanceRequest();
        balanceRequest.setUserId(userId);
        balanceRequest.setCredit(initialUserBalance);

        String inputJson = super.mapToJson(balanceRequest);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(path)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
    }

    @Test
    public void calculateEnoughUserBalanceTest() throws Exception {
        String path = uri.concat("calculation");
        CalculatorRequest calculatorRequest = new CalculatorRequest();
        calculatorRequest.setUserId(userId);
        calculatorRequest.setCalculation("3 + 5");

        String inputJson = super.mapToJson(calculatorRequest);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(path)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        Boolean hasEnoughUserBalance = content.contains(criteriaResponse);
        assertTrue(hasEnoughUserBalance);
    }

    @Test
    public void calculateNotEnoughUserBalanceTest() throws Exception {
        String path = uri.concat("calculation");
        CalculatorRequest calculatorRequest = new CalculatorRequest();
        calculatorRequest.setUserId(userId);
        calculatorRequest.setCalculation("1 + 2 + 3 + 4 + 5 + 6 + 7 + 8 + 9 + 10");

        String inputJson = super.mapToJson(calculatorRequest);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(path)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        Boolean hasEnoughUserBalance = content.contains(criteriaResponse);
        assertTrue(!hasEnoughUserBalance);
    }

    @Test
    public void generateRandomStringTest() throws Exception {
        String path = uri.concat("/generate-random-string/" + userId);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(path)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
    }
}
