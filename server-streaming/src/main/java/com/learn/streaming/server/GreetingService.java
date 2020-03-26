package com.learn.streaming.server;

import com.learn.grpc.greeting.GreetingRequest;
import com.learn.grpc.greeting.GreetingResponse;
import com.learn.grpc.greeting.GreetingServiceGrpc;
import com.learn.grpc.greeting.Person;
import io.grpc.stub.StreamObserver;

public class GreetingService extends GreetingServiceGrpc.GreetingServiceImplBase {
    @Override
    public void greet(GreetingRequest request, StreamObserver<GreetingResponse> responseObserver) {
        Person person = request.getPerson();

        for(int i=0; i<12; i++) {
            GreetingResponse greetingResponse = GreetingResponse.newBuilder()
                    .setGreeting("Hello "+ person.getFirstName() + "  "+ i)
                    .build();

            responseObserver.onNext(greetingResponse);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        responseObserver.onCompleted();
    }
}
