package com.learn.streaming.server;

import com.learn.grpc.greeting.GreetingRequest;
import com.learn.grpc.greeting.GreetingResponse;
import com.learn.grpc.greeting.GreetingServiceGrpc;
import com.learn.grpc.greeting.Person;
import io.grpc.stub.StreamObserver;

public class GreetingService extends GreetingServiceGrpc.GreetingServiceImplBase {
    @Override
    public StreamObserver<GreetingRequest> greet(StreamObserver<GreetingResponse> responseObserver) {
        StreamObserver<GreetingRequest> streamObserver = new StreamObserver<GreetingRequest>() {
            private String message = "";
            @Override
            public void onNext(GreetingRequest greetingRequest) {
                // client sends message
                Person person = greetingRequest.getPerson();
                System.out.println("Person " + person.getFirstName());

                message += "Hello "+ person.getFirstName() + "\n";
            }

            @Override
            public void onError(Throwable throwable) {
                // client sends an error
            }

            @Override
            public void onCompleted() {
                // client is done.
                GreetingResponse greetingResponse = GreetingResponse.newBuilder()
                        .setGreeting(message)
                        .build();
                responseObserver.onNext(greetingResponse);

                responseObserver.onCompleted();
            }
        };

        return streamObserver;
    }
}
