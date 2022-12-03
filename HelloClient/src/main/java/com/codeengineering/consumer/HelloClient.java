package com.codeengineering.consumer;

import com.codeengineering.hello.protobuf.HelloRequest;
import com.codeengineering.hello.protobuf.HelloResponse;
import com.codeengineering.hello.protobuf.HelloServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.util.concurrent.TimeUnit;

public class HelloClient {
    public static void main(String[] args) {
        final ManagedChannel channel = ManagedChannelBuilder
            .forAddress("localhost", 8080)
            .usePlaintext()
            .build();

        HelloResponse response = newBlockingStub(channel)
            .greetings(HelloRequest.newBuilder().setMessage("Hi I am a Client").build());
        System.out.println("From server 01: " + response.getMessage());

        response = newBlockingStub(channel)
            .greetings(HelloRequest.newBuilder().setMessage("Hi I am a Client").build());
        System.out.println("From server 02: " + response.getMessage());
    }

    private static HelloServiceGrpc.HelloServiceBlockingStub newBlockingStub(final ManagedChannel channel) {
        return HelloServiceGrpc.newBlockingStub(channel).withDeadlineAfter(3000, TimeUnit.MILLISECONDS);
    }
}
