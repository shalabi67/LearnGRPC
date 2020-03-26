package com.learn.grpc.first.server;

import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;

public class HelloServer {
    public static void main(String[] args) {
        System.out.println("Hello server");
        int port = 5555;

        Server server = ServerBuilder.forPort(port).build();

        try {
            server.start();
            System.out.println("Hello server started on port " + port);

            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                System.out.println("Receive shutdown request");

                server.shutdown();

                System.out.println("Hello server stopped");
            }));

            server.awaitTermination();
        } catch (IOException e) {
            System.out.println("Could not start Hello server on port " + port);
        } catch (InterruptedException e) {
            System.out.println("Could terminate Hello server started on port " + port);
        }
    }
}
