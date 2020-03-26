package com.learn.grpc.calculator.server;

import com.learn.grpc.calculator.CalculatorRequest;
import com.learn.grpc.calculator.CalculatorResponse;
import com.learn.grpc.calculator.CalculatorServiceGrpc;
import io.grpc.stub.StreamObserver;

public class CalculatorService extends CalculatorServiceGrpc.CalculatorServiceImplBase {

    @Override
    public void sum(CalculatorRequest request, StreamObserver<CalculatorResponse> responseObserver) {
        CalculatorResponse calculatorResponse = CalculatorResponse.newBuilder()
                .setResult(request.getFirstNumber() + request.getSecondNumber())
                .build();

        responseObserver.onNext(calculatorResponse);
        responseObserver.onCompleted();
    }
}
