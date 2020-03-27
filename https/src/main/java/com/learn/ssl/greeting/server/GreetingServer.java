package com.learn.ssl.greeting.server;

import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.File;
import java.io.IOException;

public class GreetingServer {
    public static void main(String[] args) {
        System.out.println("Greeting server");
        int port = 5558;

        String path = "H:\\Learn\\LearnGRPC\\https\\certificate\\";
        File serverCrt = new File(path + "server.crt");
        File serverPem = new File(path + "server.pem");

        Server server = ServerBuilder
                .forPort(port)
                .addService(new GreetingService())
                .useTransportSecurity(serverCrt, serverPem) // this is to enable ssl
                .build();

        try {
            server.start();
            System.out.println("Greeting server started on port " + port);

            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                System.out.println("Receive shutdown request");

                server.shutdown();

                System.out.println("Greeting server stopped");
            }));

            server.awaitTermination();
        } catch (IOException e) {
            System.out.println("Could not start Greeting server on port " + port);
        } catch (InterruptedException e) {
            System.out.println("Could terminate Greeting server started on port " + port);
        }
    }
}
