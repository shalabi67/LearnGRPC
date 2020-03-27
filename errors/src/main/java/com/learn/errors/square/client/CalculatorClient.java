package com.learn.errors.square.client;

import com.learn.grpc.calculator.CalculatorRequest;
import com.learn.grpc.calculator.CalculatorResponse;
import com.learn.grpc.calculator.CalculatorServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;

public class CalculatorClient {
    public static void main(String[] args) {
        System.out.println("Greeting Client");

        ManagedChannel channel = ManagedChannelBuilder
                .forAddress("localhost", 5554)
                .usePlaintext()
                .build();


        try {
            System.out.println("The square root of 100 is " + getSquareRoot(channel, 100));
            System.out.println("The square root of -1 is " + getSquareRoot(channel,-1));
        }catch(StatusRuntimeException statusException) {
            statusException.printStackTrace();
        }

        channel.shutdown();
    }

    private static double getSquareRoot(ManagedChannel channel, int number) {
        CalculatorRequest calculatorRequest = CalculatorRequest.newBuilder()
                .setNumber(number)
                .build();

        CalculatorServiceGrpc.CalculatorServiceBlockingStub calculatorService = CalculatorServiceGrpc.newBlockingStub(channel);
        CalculatorResponse response = calculatorService.squareRoot(calculatorRequest);

        return response.getResult();
    }
}
