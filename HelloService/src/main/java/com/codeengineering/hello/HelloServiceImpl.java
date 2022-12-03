package com.codeengineering.hello;

import com.codeengineering.hello.protobuf.HelloRequest;
import com.codeengineering.hello.protobuf.HelloResponse;
import com.codeengineering.hello.protobuf.HelloServiceGrpc;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class HelloServiceImpl extends HelloServiceGrpc.HelloServiceImplBase {
    @Override
    public void greetings(HelloRequest request, StreamObserver<HelloResponse> responseObserver) {
        responseObserver.onNext(HelloResponse.newBuilder()
            .setMessage("Hi I am a Server, you said: " + request.getMessage())
            .build());
        responseObserver.onCompleted();
    }

}
