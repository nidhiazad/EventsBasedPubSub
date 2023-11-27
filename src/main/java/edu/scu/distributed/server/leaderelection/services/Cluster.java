package edu.scu.distributed.server.leaderelection.services;

import edu.scu.distributed.models.Node;
import edu.scu.distributed.server.leaderelection.*;
import edu.scu.distributed.server.services.Status;
import edu.scu.distributed.server.services.SubscribeRequest;
import edu.scu.distributed.server.services.TopicServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Cluster {
  private static final Logger log = LoggerFactory.getLogger(Cluster.class);
  private final Map<String, ManagedChannel> channelMap; // {node, ManagedChannel} {1, } {2, }
  private final int TIMEOUT = 5;

  public Cluster() {
    this.channelMap = new HashMap<>();
  }

  public HeartbeatResponse requestHeartbeat(Node node, HeartbeatRequest request) {
    ManagedChannel channel = getChannel(node);
    LeaderElectionServiceGrpc.LeaderElectionServiceBlockingStub stub =
        LeaderElectionServiceGrpc.newBlockingStub(channel);
    // .withDeadlineAfter(TIMEOUT, TimeUnit.SECONDS);
    HeartbeatResponse response = null;
    try {
      response = stub.onHeartbeat(request);
    } catch (Exception e) {
      // log.error(e.getMessage(), e);
      channelMap.get(node.getId()).shutdown();
      channelMap.remove(node.getId());
    }
    return response;
  }

  public VoteResponse requestVote(Node node, VoteRequest request) {

    ManagedChannel channel = getChannel(node);
    LeaderElectionServiceGrpc.LeaderElectionServiceBlockingStub stub =
        LeaderElectionServiceGrpc.newBlockingStub(channel);
    // .withDeadlineAfter(TIMEOUT, TimeUnit.SECONDS);
    VoteResponse response = null;
    try {
      response = stub.onRequestVote(request);
    } catch (Exception e) {
      // log.error(e.getMessage(), e);
      channelMap.get(node.getId()).shutdown();
      channelMap.remove(node.getId());
    }

    return response;
  }

  public Status subscribeToTopic(Node node, SubscribeRequest request) {

    ManagedChannel channel = getChannel(node);
    TopicServiceGrpc.TopicServiceBlockingStub stub = TopicServiceGrpc.newBlockingStub(channel);
    // .withDeadlineAfter(TIMEOUT, TimeUnit.SECONDS);
    Status response = null;
    try {
      response = stub.subscribeToTopic(request);
    } catch (Exception e) {
      // log.error(e.getMessage(), e);
      channelMap.get(node.getId()).shutdown();
      channelMap.remove(node.getId());
    }

    return response;
  }

  private ManagedChannel getChannel(Node node) {
    if (!channelMap.containsKey(node.getId())) {
      ManagedChannel channel =
          ManagedChannelBuilder.forAddress(node.getIp(), node.getPort()).usePlaintext().build();
      channelMap.put(node.getId(), channel);
    }
    return channelMap.get(node.getId());
  }

  public void close() {
    if (channelMap.isEmpty()) return;
    channelMap.values().forEach(ManagedChannel::shutdown);
  }
}
