package com.codeengineering.hello;

import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;

public class GRPCServer {
    public static void main(String[] args) throws IOException, InterruptedException {
        final Server server = ServerBuilder.forPort(8080)
            .addService(new HelloServiceImpl())
            .build();

        server.start();
        server.awaitTermination();
    }
}
