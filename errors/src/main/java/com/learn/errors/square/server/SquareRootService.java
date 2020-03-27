package com.learn.errors.square.server;

import com.learn.grpc.calculator.CalculatorRequest;
import com.learn.grpc.calculator.CalculatorResponse;
import com.learn.grpc.calculator.CalculatorServiceGrpc;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;

public class SquareRootService extends CalculatorServiceGrpc.CalculatorServiceImplBase {
    @Override
    public void squareRoot(CalculatorRequest request, StreamObserver<CalculatorResponse> responseObserver) {
        Integer number = request.getNumber();
        if(number == null || number<0) {
            responseObserver.onError(
                    Status
                        .INVALID_ARGUMENT
                        .withDescription("The number should be positive")
                        .augmentDescription("The number is " + number)
                        .asRuntimeException()
            );

            return;
        }

        double result = Math.sqrt(number);
        CalculatorResponse response = CalculatorResponse.newBuilder()
                .setResult(result)
                .build();

        responseObserver.onNext(response);

        responseObserver.onCompleted();
    }
}
