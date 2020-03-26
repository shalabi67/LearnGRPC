package com.learn.grpc.calculator.server;

import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;

public class CalculatorServer {
    public static void main(String[] args) {
        System.out.println("Calculator server");
        int port = 5551;

        Server server = ServerBuilder
                .forPort(port)
                .addService(new CalculatorService())
                .build();

        try {
            server.start();
            System.out.println("Calculator server started on port " + port);

            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                System.out.println("Receive shutdown request");

                server.shutdown();

                System.out.println("Calculator server stopped");
            }));

            server.awaitTermination();
        } catch (IOException e) {
            System.out.println("Could not start Calculator server on port " + port);
        } catch (InterruptedException e) {
            System.out.println("Could terminate Calculator server started on port " + port);
        }
    }
}
