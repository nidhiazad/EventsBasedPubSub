package edu.scu.distributed.server.services;

import edu.scu.distributed.models.Node;
import edu.scu.distributed.models.Topics;
import edu.scu.distributed.server.commons.NodeSelector;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import java.util.List;

public class TopicService extends TopicServiceGrpc.TopicServiceImplBase {

  private final Topics topics;

  private final NodeSelector nodeSelector;

  public TopicService(NodeSelector selector) {
    nodeSelector = selector;
    this.topics = new Topics("", 0);
  }

  @Override
  public void subscribeToTopic(SubscribeRequest request, StreamObserver<Status> responseObserver) {
    // super.subscribeToTopic(request, responseObserver);
    // SubscribeTo_localhost_8070_/topic1
    String result =
        topics.addSubscriberOperation(
            "SubscribeTo_"
                + request.getIpAddress()
                + "_"
                + request.getPort()
                + "_"
                + request.getTopic());
    Status response = Status.newBuilder().setValue(result).build();
    responseObserver.onNext(response);
    responseObserver.onCompleted();
  }

  @Override
  public void publishMessage(Message request, StreamObserver<Status> responseObserver) {
    edu.scu.distributed.models.Message message =
        new edu.scu.distributed.models.Message(
            request.getTopic() + "_" + request.getData(), request.getSender());
    System.out.println("input message: " + message.message + ", sender: " + message.sender);
    String result = topics.publishMessageOperation(message);
    Status response = Status.newBuilder().setValue(result).build();
    responseObserver.onNext(response);
    int port = request.getReceiverPort();
    if (request.getReplicate()) {
      Message newReq = Message.newBuilder(request).setReplicate(false).build();
      replicate(port, newReq);
    }

    responseObserver.onCompleted();
  }

  public void replicate(int port, Message request) {

    List<Node> nodes = this.nodeSelector.getClusterNodes();

    for (Node node : nodes) {
      if (node.port != port) {
        ManagedChannel channel = null;
        try {
          channel =
              ManagedChannelBuilder.forAddress(node.getIp(), node.getPort()).usePlaintext().build();
          TopicServiceGrpc.TopicServiceBlockingStub stub =
              TopicServiceGrpc.newBlockingStub(channel);
          Status response = stub.publishMessage(request);
        } catch (Exception ignored) {

        } finally {
          if (channel != null) channel.shutdown();
        }
      }
    }
  }

  @Override
  public void getMessages(GetMessageRequest request, StreamObserver<Status> responseObserver) {
    // super.getMessages(request, responseObserver);
    // GetMessages_localhost_8070_/topic1
    // request.getName() - topic name
    String result =
        topics.getMessagesOperation(
            new edu.scu.distributed.models.Message(
                "GetMessages_"
                    + request.getName()
                    + "_"
                    + request.getIpAddress()
                    + "_"
                    + request.getPort(),
                ""));
    Status response = Status.newBuilder().setValue(result).build();
    responseObserver.onNext(response);
    responseObserver.onCompleted();
  }
}
