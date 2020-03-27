package com.learn.deadlkines.greeting.client;

import com.learn.grpc.greeting.GreetingRequest;
import com.learn.grpc.greeting.GreetingResponse;
import com.learn.grpc.greeting.GreetingServiceGrpc;
import com.learn.grpc.greeting.Person;
import io.grpc.*;

import java.util.concurrent.TimeUnit;

public class GreetingClient {
    public static void main(String[] args) {
        System.out.println("Greeting Client");

        ManagedChannel channel = ManagedChannelBuilder
                .forAddress("localhost", 5557)
                .usePlaintext()
                .build();

        GreetingServiceGrpc.GreetingServiceBlockingStub greetingService = GreetingServiceGrpc.newBlockingStub(channel);
        Person person = Person.newBuilder()
                .setFirstName("Mohammad")
                .setLastName("shalabi")
                .build();

        GreetingRequest greetingRequest = GreetingRequest.newBuilder()
                .setPerson(person)
                .build();


        try {
            System.out.println("deadline with 4000 ms should succeed");
            GreetingResponse greetingResponse = greetingService
                    .withDeadline(Deadline.after(4000, TimeUnit.MILLISECONDS))
                    .greet(greetingRequest);
            System.out.println(greetingResponse.getGreeting());

            System.out.println("deadline with 200 ms should fail with exception");
            greetingResponse = greetingService
                    .withDeadline(Deadline.after(200, TimeUnit.MILLISECONDS))
                    .greet(greetingRequest);
            System.out.println(greetingResponse.getGreeting());
        }catch(StatusRuntimeException e) {
            if(e.getStatus() == Status.DEADLINE_EXCEEDED) {
                System.out.println("deadline exceeded");
            } else {
                e.printStackTrace();
            }
        }

        channel.shutdown();
    }
}
