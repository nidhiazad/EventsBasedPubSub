package edu.scu.distributed.testDelete;

import edu.scu.distributed.GreeterGrpc;
import edu.scu.distributed.HelloReply;
import edu.scu.distributed.HelloRequest;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class HelloWorldClient {
  public static void main(String[] args) {
    ManagedChannel channel =
        ManagedChannelBuilder.forAddress("0.0.0.0", 6003).usePlaintext().build();

    GreeterGrpc.GreeterBlockingStub stub = GreeterGrpc.newBlockingStub(channel);

    HelloReply helloResponse = stub.sayHello(HelloRequest.newBuilder().setName("Dharahas").build());
    System.out.println(helloResponse.getMessage());
    channel.shutdown();
  }
}
