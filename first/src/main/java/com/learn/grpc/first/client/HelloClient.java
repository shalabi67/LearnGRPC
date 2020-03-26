package com.learn.grpc.first.client;

import com.learn.grpc.first.hello.HelloServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class HelloClient {
    public static void main(String[] args) {
        System.out.println("Hello Client");

        ManagedChannel channel = ManagedChannelBuilder
                .forAddress("localhost", 5555)
                .usePlaintext()
                .build();


        //create sync client
        HelloServiceGrpc.HelloServiceBlockingStub syncHelloClient = HelloServiceGrpc.newBlockingStub(channel);

        //create async client
        HelloServiceGrpc.HelloServiceFutureStub asyncHelloClient = HelloServiceGrpc.newFutureStub(channel);

        // do something with the client


        channel.shutdown();
        System.out.println("Hello Client shutdown");
    }
}
