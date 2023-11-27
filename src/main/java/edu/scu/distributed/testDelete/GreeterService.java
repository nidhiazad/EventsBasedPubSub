package edu.scu.distributed.testDelete;

import edu.scu.distributed.GreeterGrpc;
import edu.scu.distributed.HelloReply;
import edu.scu.distributed.HelloRequest;
import io.grpc.stub.StreamObserver;

public class GreeterService extends GreeterGrpc.GreeterImplBase {

  @Override
  public void sayHello(HelloRequest request, StreamObserver<HelloReply> responseObserver) {
    //    super.sayHello(request, responseObserver);
    System.out.println("Received request: " + request.getName());
    HelloReply reply = HelloReply.newBuilder().setMessage(request.getName() + " , hello").build();
    responseObserver.onNext(reply);
    responseObserver.onCompleted();
  }
}
