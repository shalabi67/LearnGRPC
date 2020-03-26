package com.ps.grpc.server;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.ServerInterceptors;
import io.grpc.ServerServiceDefinition;
import java.io.File;

public class EmployeeServiceServer {
    public static void main(String[] args) {
        try {
            EmployeeServiceServer employeeServiceServer = new EmployeeServiceServer();
            employeeServiceServer.start();
        } catch (Exception e) {
            System.err.println(e);
        }
    }
    
    private Server server;
    
    private void start() throws Exception {
        final int port = 9000;
    
        File cert = new File("certs/cert.pem");
        File key = new File("certs/key.pem");
        
        EmployeeService employeeService = new EmployeeService();
        
        ServerServiceDefinition serviceDef = 
                ServerInterceptors.interceptForward(employeeService, 
                        new HeaderServerInterceptor());
        
        server = ServerBuilder.forPort(port)
                .useTransportSecurity(cert, key)
                .addService(serviceDef)
                .build()
                .start();
        System.out.println("Listening on port " + port);
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                System.out.println("Shutting down server");
                EmployeeServiceServer.this.stop();
            }
        });
        
        server.awaitTermination();
        
    }
    
    private void stop() {
        if (server != null) {
            server.shutdown();
        }
    }
}
