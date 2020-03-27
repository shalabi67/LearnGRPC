package com.learn.streaming.client;

import com.learn.grpc.greeting.GreetingRequest;
import com.learn.grpc.greeting.GreetingResponse;
import com.learn.grpc.greeting.GreetingServiceGrpc;
import com.learn.grpc.greeting.Person;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class GreetingClient {
    public static void main(String[] args) {
        System.out.println("Greeting Client");

        latch = new CountDownLatch(1);

        ManagedChannel channel = ManagedChannelBuilder
                .forAddress("localhost", 5553)
                .usePlaintext()
                .build();

        // now this is async operation
        GreetingServiceGrpc.GreetingServiceStub greetingService = GreetingServiceGrpc.newStub(channel);

        StreamObserver<GreetingRequest> greetingRequestObserver = greetingService.greet(greetingStreamResponse);

        for(int i=0; i<12; i++) {
            Person person = Person.newBuilder()
                    .setFirstName("Mohammad " + i)
                    .setLastName("shalabi")
                    .build();

            GreetingRequest greetingRequest = GreetingRequest.newBuilder()
                    .setPerson(person)
                    .build();

            greetingRequestObserver.onNext(greetingRequest);
        }
        greetingRequestObserver.onCompleted();


        try {
            latch.await(3L, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }



        channel.shutdown();
    }

    private static CountDownLatch latch = new CountDownLatch(1);
    private static StreamObserver<GreetingResponse> greetingStreamResponse = new StreamObserver<GreetingResponse>() {

        @Override
        public void onNext(GreetingResponse greetingResponse) {
            // we get a response from server
            System.out.println(greetingResponse.getGreeting());
        }

        @Override
        public void onError(Throwable throwable) {
            // an error from server

        }

        @Override
        public void onCompleted() {
            // server complete processing
            System.out.println("done");
            latch.countDown();
        }
    };
}
