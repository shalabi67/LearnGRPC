package com.ps.grpc.server;

import com.google.protobuf.ByteString;
import com.ps.grpc.messages.EmployeeServiceGrpc;
import com.ps.grpc.messages.Messages;
import io.grpc.stub.StreamObserver;

public class EmployeeService extends EmployeeServiceGrpc.EmployeeServiceImplBase {

    @Override
    public void getByBadgeNumber(Messages.GetByBadgeNumberRequest request, 
            StreamObserver<Messages.EmployeeResponse> responseObserver) {
        for (Messages.Employee e : Employees.getInstance()) {
            if (e.getBadgeNumber() == request.getBadgeNumber()) {
                Messages.EmployeeResponse response =
                        Messages.EmployeeResponse.newBuilder()
                        .setEmployee(e)
                        .build();
                responseObserver.onNext(response);
                responseObserver.onCompleted();
                return;
            }
            
        }
        responseObserver.onError(new Exception("Employee not found with badgeNumber: " + 
                request.getBadgeNumber()));
    }

    @Override
    public void getAll(Messages.GetAllRequest request, 
            StreamObserver<Messages.EmployeeResponse> responseObserver) {
        for (Messages.Employee e : Employees.getInstance()) {
            Messages.EmployeeResponse response =
                        Messages.EmployeeResponse.newBuilder()
                        .setEmployee(e)
                        .build();
            responseObserver.onNext(response);
        }
        
        responseObserver.onCompleted();
    }

    @Override
    public StreamObserver<Messages.AddPhotoRequest> addPhoto(
            StreamObserver<Messages.AddPhotoResponse> responseObserver) {
        return new StreamObserver<Messages.AddPhotoRequest>() {
            private ByteString result;
            
            @Override
            public void onNext(Messages.AddPhotoRequest v) {
                if (result == null) {
                    result = v.getData();
                } else {
                    result = result.concat(v.getData());
                }
                System.out.println("Received messages with " + 
                        v.getData().size() + " bytes");
            }

            @Override
            public void onError(Throwable thrwbl) {
                System.out.println(thrwbl);
            }

            @Override
            public void onCompleted() {
                System.out.println("Total bytes received: " + result.size());
                responseObserver.onNext(
                        Messages.AddPhotoResponse.newBuilder()
                        .setIsOk(true)
                        .build());
                responseObserver.onCompleted();
            }
        };
    }

    @Override
    public StreamObserver<Messages.EmployeeRequest> saveAll(
            StreamObserver<Messages.EmployeeResponse> responseObserver) {
        return new StreamObserver<Messages.EmployeeRequest>() {
            @Override
            public void onNext(Messages.EmployeeRequest v) {
                Employees.getInstance().add(v.getEmployee());
                responseObserver.onNext(
                        Messages.EmployeeResponse.newBuilder()
                        .setEmployee(v.getEmployee())
                        .build()
                );
            }

            @Override
            public void onError(Throwable thrwbl) {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public void onCompleted() {
                for (Messages.Employee e : Employees.getInstance()) {
                    System.out.println(e);
                }
                responseObserver.onCompleted();
            }
        };
    }
    
    

}
