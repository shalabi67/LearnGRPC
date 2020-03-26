package com.learn.streaming.client;

import com.learn.grpc.greeting.GreetingRequest;
import com.learn.grpc.greeting.GreetingResponse;
import com.learn.grpc.greeting.GreetingServiceGrpc;
import com.learn.grpc.greeting.Person;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class GreetingClient {
    public static void main(String[] args) {
        System.out.println("Greeting Client");

        ManagedChannel channel = ManagedChannelBuilder
                .forAddress("localhost", 5552)
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

        greetingService.greet(greetingRequest)
                .forEachRemaining(greetingResponse ->  System.out.println(greetingResponse.getGreeting()));

        channel.shutdown();
    }
}
