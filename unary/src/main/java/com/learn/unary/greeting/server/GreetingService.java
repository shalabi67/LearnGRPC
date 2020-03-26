package com.learn.unary.greeting.server;


import com.learn.grpc.greeting.GreetingRequest;
import com.learn.grpc.greeting.GreetingResponse;
import com.learn.grpc.greeting.GreetingServiceGrpc;
import com.learn.grpc.greeting.Person;
import io.grpc.stub.StreamObserver;

public class GreetingService extends GreetingServiceGrpc.GreetingServiceImplBase {
    @Override
    public void greet(GreetingRequest request, StreamObserver<GreetingResponse> responseObserver) {
        Person person = request.getPerson();
        System.out.println("Person " + person.getFirstName() + " " +person.getLastName());

        String greeting = "Hello " + person.getFirstName();

        GreetingResponse greetingResponse = GreetingResponse.newBuilder()
                .setGreeting(greeting)
                .build();

        responseObserver.onNext(greetingResponse);
        responseObserver.onCompleted();
    }
}
