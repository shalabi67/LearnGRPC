package com.learn.grpc.crud.blog.server;

import com.learn.grpc.crud.blog.Connection;
import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;

public class BlogServer {
    public static void main(String[] args) {
        System.out.println("Blog server");
        int port = Connection.PORT;

        Server server = ServerBuilder
                .forPort(port)
                .addService(new BlogService())
                .build();

        try {
            server.start();
            System.out.println("Blog server started on port " + port);

            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                System.out.println("Receive shutdown request");

                server.shutdown();

                System.out.println("Blog server stopped");
            }));

            server.awaitTermination();
        } catch (IOException e) {
            System.out.println("Could not start Blog server on port " + port);
        } catch (InterruptedException e) {
            System.out.println("Could terminate Blog server started on port " + port);
        }
    }
}
