package edu.scu.distributed.testDelete;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import java.io.IOException;
import java.util.Arrays;

public class Main {
  public static void main(String[] args) throws IOException, InterruptedException {

    Server server = ServerBuilder.forPort(6000).addService(new GreeterService()).build();

    server.start();

    System.out.println(Arrays.toString(server.getListenSockets().toArray()));
    server.awaitTermination();
  }
}
