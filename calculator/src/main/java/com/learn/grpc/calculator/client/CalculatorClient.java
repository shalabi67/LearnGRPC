package com.learn.grpc.calculator.client;

import com.learn.grpc.calculator.CalculatorRequest;
import com.learn.grpc.calculator.CalculatorResponse;
import com.learn.grpc.calculator.CalculatorServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class CalculatorClient {
    public static void main(String[] args) {
        System.out.println("Greeting Client");

        ManagedChannel channel = ManagedChannelBuilder
                .forAddress("localhost", 5551)
                .usePlaintext()
                .build();

        CalculatorRequest calculatorRequest = CalculatorRequest.newBuilder()
                .setFirstNumber(10)
                .setSecondNumber(20)
                .build();

        CalculatorServiceGrpc.CalculatorServiceBlockingStub calculatorService = CalculatorServiceGrpc.newBlockingStub(channel);
        CalculatorResponse response = calculatorService.sum(calculatorRequest);

        System.out.println(response.getResult());
    }
}
