package com.codeengineering.consumer;

import com.codeengineering.hello.protobuf.HelloRequest;
import com.codeengineering.hello.protobuf.HelloResponse;
import com.codeengineering.hello.protobuf.HelloServiceGrpc;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class HelloClientService {
    private final static int DEADLINE_IN_MS = 5000;

    @GrpcClient("hello-service")
    private HelloServiceGrpc.HelloServiceBlockingStub helloService;

    public String printMessage(final String input) {
        HelloResponse response = withDeadline(helloService).greetings(
            HelloRequest.newBuilder().setMessage(input).build());

        return response.getMessage();
    }

    private static HelloServiceGrpc.HelloServiceBlockingStub withDeadline(
        final HelloServiceGrpc.HelloServiceBlockingStub stub) {
        return stub.withDeadlineAfter(DEADLINE_IN_MS, TimeUnit.MILLISECONDS);
    }
}
