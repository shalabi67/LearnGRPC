package com.learn.bidirectional.streaming.server;

import com.learn.grpc.greeting.GreetingRequest;
import com.learn.grpc.greeting.GreetingResponse;
import com.learn.grpc.greeting.GreetingServiceGrpc;
import com.learn.grpc.greeting.Person;
import io.grpc.stub.StreamObserver;

public class GreetingService extends GreetingServiceGrpc.GreetingServiceImplBase {
    @Override
    public StreamObserver<GreetingRequest> greet(StreamObserver<GreetingResponse> responseObserver) {
        StreamObserver<GreetingRequest> streamObserver = new StreamObserver<GreetingRequest>() {
            @Override
            public void onNext(GreetingRequest greetingRequest) {
                // client sends message
                Person person = greetingRequest.getPerson();
                System.out.println("Person " + person.getFirstName());

                GreetingResponse greetingResponse = GreetingResponse.newBuilder()
                        .setGreeting("Hello "+ person.getFirstName())
                        .build();
                responseObserver.onNext(greetingResponse);
            }

            @Override
            public void onError(Throwable throwable) {
                // client sends an error
            }

            @Override
            public void onCompleted() {
                // client is done.
                System.out.println("done");
                responseObserver.onCompleted();
            }
        };

        return streamObserver;
    }
}
