package com.dmolina.arithmeticcalculator.service;

import com.dmolina.arithmeticcalculator.client.RandomStringGeneratorClient;
import com.dmolina.arithmeticcalculator.exception.ResourceNotFoundException;
import com.dmolina.arithmeticcalculator.model.Operation;
import com.dmolina.arithmeticcalculator.request.CalculatorRequest;
import com.dmolina.arithmeticcalculator.request.RecordRequest;
import com.dmolina.arithmeticcalculator.response.CalculatorResponse;
import com.dmolina.arithmeticcalculator.response.RandomStringResponse;
import feign.Feign;
import feign.Logger;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import feign.okhttp.OkHttpClient;
import feign.slf4j.Slf4jLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class CalculationServiceImpl implements CalculationService{
    Double userBalance;
    private static final DecimalFormat df = new DecimalFormat("0.00");
    static final String  ERROR_NEGATIVE_NUMBER = "ERROR_NEGATIVE_NUMBER";
    static final String SEPARATOR = " ";
    static final String ADDITION = "+";
    static final String SUBTRACTION = "-";
    static final String MULTIPLICATION = "*";
    static final String DIVISION = "/";
    static final String SQUARE_ROOT = "√";

    static final Integer COMPOUND = 14;

    Double totalAmount = 0.0;
    String operationCalculated = null;
    Double resultOperationCalculated = null;
    Double initialUserBalance = null;
    Double finalUserBalance = null;
    String message = null;

    static HashMap<String, Integer> operations;
    static {
        operations =  new HashMap<>();
        operations.put(ADDITION, 0);
        operations.put(SUBTRACTION, 0);
        operations.put(MULTIPLICATION, 0);
        operations.put(DIVISION, 0);
        operations.put(SQUARE_ROOT, 0);
    }
    static HashMap<String, Integer> countPerOperationRequest;
    static {
        countPerOperationRequest =  new HashMap<>();
        countPerOperationRequest.put(ADDITION, 0);
        countPerOperationRequest.put(SUBTRACTION, 0);
        countPerOperationRequest.put(MULTIPLICATION, 0);
        countPerOperationRequest.put(DIVISION, 0);
        countPerOperationRequest.put(SQUARE_ROOT, 0);
    }

    @Autowired
    private OperationService operationService;

    @Autowired
    private RecordService recordService;

    @Autowired
    private BalanceService balanceService;

    @Override
    public CalculatorResponse calculate(CalculatorRequest calculation) throws ResourceNotFoundException {
        Integer userId = calculation.getUserId();
        userBalance = balanceService.getBalanceByUserId(userId).getCredit();
        initializeCountPerOperationRequest();
        loadOperations();

        String formattedCalculation = Stream.of(calculation.getCalculation().split(SEPARATOR))
                .map(elem -> findOperationType(elem))
                .collect(Collectors.joining(SEPARATOR));

        if(formattedCalculation.contains(ERROR_NEGATIVE_NUMBER)){
            message = ERROR_NEGATIVE_NUMBER;
        }else{
            operationCalculated = formattedCalculation;
            resultOperationCalculated  = Double.valueOf(calculateOperation(formattedCalculation));
            initialUserBalance = userBalance;
            totalAmount = calculateTotalAmount();
            finalUserBalance = (initialUserBalance - totalAmount);
            Integer operationId = COMPOUND;
            if(isSimpleOperation(operationCalculated)){
                operationId = getOperationId(operationCalculated);
            }
            if(totalAmount <= initialUserBalance){
                RecordRequest recordRequest = new RecordRequest(operationId,calculation.getCalculation(), userId,totalAmount,finalUserBalance,resultOperationCalculated.toString());
                recordService.saveRecord(recordRequest);
                balanceService.updateUserBalance(userId,finalUserBalance);
                message = "Enough User balance " + initialUserBalance + " for Total amount to spend: " + totalAmount;
            }else{
                message = "Insufficient User balance " + initialUserBalance + " for Total amount to spend: " + totalAmount;
            }

        }
        CalculatorResponse calculatorResponse = new CalculatorResponse(operationCalculated,
                                                                        resultOperationCalculated,
                                                                        initialUserBalance,
                                                                        totalAmount,
                                                                        finalUserBalance,
                                                                        message);

        return calculatorResponse;
    }

    @Override
    public RandomStringResponse generateRandomString(Integer userId) throws ResourceNotFoundException {
        userBalance = balanceService.getBalanceByUserId(userId).getCredit();
        RandomStringResponse randomStringResponse = null;
        if(userBalance > 0) {
            Operation operation = operationService.getOperationByType("randomstring");
            if(operation.getCost() <= userBalance){
                finalUserBalance = userBalance - operation.getCost();
                String CLIENT = "https://www.random.org/strings/";
                String PATH = "?num=1&len=16&digits=on&upperalpha=on&loweralpha=on&unique=on&format=plain&rnd=new";
                RandomStringGeneratorClient randomString = Feign.builder()
                        .client(new OkHttpClient())
                        .encoder(new GsonEncoder())
                        .decoder(new GsonDecoder())
                        .logger(new Slf4jLogger(RandomStringGeneratorClient.class))
                        .logLevel(Logger.Level.FULL)
                        .target(RandomStringGeneratorClient.class, CLIENT + PATH);
                randomStringResponse = new RandomStringResponse(randomString.generateRandomString());
                balanceService.updateUserBalance(userId,finalUserBalance);
                RecordRequest recordRequest = new RecordRequest(operation.getId(),"randomstring", userId,Double.valueOf(operation.getCost()),finalUserBalance,randomStringResponse.getHash());
                recordService.saveRecord(recordRequest);
            }
        }
        return randomStringResponse;
    }

    private static Boolean isSimpleOperation(String operationCalculated){
        long spaces = operationCalculated.chars().filter(c -> c == (int)' ').count();
        return spaces == 2;
    }

    private int getOperationId(String operationCalculated) throws ResourceNotFoundException {
        int beginIndex = operationCalculated.indexOf(" ") + 1;
        int endIndex = beginIndex + 1;
        String type = operationCalculated.substring(beginIndex, endIndex);
        Operation operation = operationService.getOperationByType(type);
        return operation.getId();
    }

    private static Double calculateTotalAmount(){
        AtomicInteger totalCostOperations = new AtomicInteger();
        countPerOperationRequest.forEach(
                (key, value) -> {
                    int costOperation = operations.get(key);
                    int countOfOperationsRequest = value;
                    int costPerOperation = costOperation * countOfOperationsRequest;
                    totalCostOperations.set(totalCostOperations.get() + costPerOperation);
                }
        );
        return Double.valueOf(totalCostOperations.get());
    }

    private static void sumCountPerOperationRequest(String value){
        switch (value){
            case ADDITION:
                countPerOperationRequest.put(ADDITION, countPerOperationRequest.get(ADDITION) + 1);
            break;
            case SUBTRACTION:
                countPerOperationRequest.put(SUBTRACTION, countPerOperationRequest.get(SUBTRACTION) + 1);
            break;
            case MULTIPLICATION:
                countPerOperationRequest.put(MULTIPLICATION, countPerOperationRequest.get(MULTIPLICATION) + 1);
            break;
            case DIVISION:
                countPerOperationRequest.put(DIVISION, countPerOperationRequest.get(DIVISION) + 1);
            break;
        }
    }

    private static String findOperationType(String value){
        sumCountPerOperationRequest(value);
        return evaluateOperation(value);
    }

    private static String evaluateOperation(String value){
        if(value.contains(SQUARE_ROOT)){
            countPerOperationRequest.put(SQUARE_ROOT, countPerOperationRequest.get(SQUARE_ROOT) + 1);
            return getSquareRoot(value);
        }else{
            return value;
        }
    }

    private static String getSquareRoot(String value){
        int index = value.contains(" ") ? value.indexOf(" ") : value.length();
        int number = Integer.parseInt(value.substring(1,index));
        if(number >= 0){
            return df.format(Math.sqrt(number));
        }else{
            return ERROR_NEGATIVE_NUMBER;
        }
    }

    private static String calculateOperation(String operation){
        ExpressionParser parser = new SpelExpressionParser();
        Expression expression = parser.parseExpression(operation);
        return df.format(expression.getValue());
    }

    private void loadOperations(){
        List<Operation> operationList = operationService.getOperations();
        operationList.forEach(operation -> {
            operations.put(operation.getType(),operation.getCost());
        });
    }

    private void initializeCountPerOperationRequest(){
        countPerOperationRequest.put(ADDITION, 0);
        countPerOperationRequest.put(SUBTRACTION, 0);
        countPerOperationRequest.put(MULTIPLICATION, 0);
        countPerOperationRequest.put(DIVISION, 0);
        countPerOperationRequest.put(SQUARE_ROOT, 0);
    }
}
