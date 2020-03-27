package com.learn.ssl.greeting.client;

import com.learn.grpc.greeting.GreetingRequest;
import com.learn.grpc.greeting.GreetingResponse;
import com.learn.grpc.greeting.GreetingServiceGrpc;
import com.learn.grpc.greeting.Person;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.netty.shaded.io.grpc.netty.GrpcSslContexts;
import io.grpc.netty.shaded.io.grpc.netty.NettyChannelBuilder;
import io.grpc.netty.shaded.io.netty.handler.ssl.SslContext;

import javax.net.ssl.SSLException;
import java.io.File;

public class GreetingClient {
    public static void main(String[] args) throws SSLException {
        System.out.println("Greeting Client");

        String path = "H:\\Learn\\LearnGRPC\\https\\certificate\\";
        File clientCrt = new File(path + "ca.crt");

        SslContext sslContext = GrpcSslContexts
                .forClient()
                .trustManager(clientCrt)
                .build();

        ManagedChannel channel = NettyChannelBuilder
                .forAddress("localhost", 5558)
                .sslContext(sslContext)
                .build();

        GreetingServiceGrpc.GreetingServiceBlockingStub greetingService = GreetingServiceGrpc.newBlockingStub(channel);
        Person person = Person.newBuilder()
                .setFirstName("Mohammad")
                .setLastName("shalabi")
                .build();

        GreetingRequest greetingRequest = GreetingRequest.newBuilder()
                .setPerson(person)
                .build();
        GreetingResponse greetingResponse = greetingService.greet(greetingRequest);
        System.out.println(greetingResponse.getGreeting());

        channel.shutdown();
    }
}
