package com.learn.deadlkines.greeting.server;


import com.learn.grpc.greeting.GreetingRequest;
import com.learn.grpc.greeting.GreetingResponse;
import com.learn.grpc.greeting.GreetingServiceGrpc;
import com.learn.grpc.greeting.Person;
import io.grpc.Context;
import io.grpc.stub.StreamObserver;

public class GreetingService extends GreetingServiceGrpc.GreetingServiceImplBase {
    @Override
    public void greet(GreetingRequest request, StreamObserver<GreetingResponse> responseObserver) {
        for(int i=0;i<5; i++) {
            if(Context.current().isCancelled()) {
                System.out.println("Request is canceled");
                return;
            }

            try {
                System.out.println("sleeping for 100 ms");
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
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
