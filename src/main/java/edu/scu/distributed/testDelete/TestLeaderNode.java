package edu.scu.distributed.testDelete;

import edu.scu.distributed.server.leaderelection.Empty;
import edu.scu.distributed.server.leaderelection.Leader;
import edu.scu.distributed.server.leaderelection.LeaderElectionServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import java.util.concurrent.TimeUnit;

public class TestLeaderNode {

  public static void main(String[] args) {
    //
    ManagedChannel channel =
        ManagedChannelBuilder.forAddress("10.0.0.131", 6000).usePlaintext().build();
    LeaderElectionServiceGrpc.LeaderElectionServiceBlockingStub stub =
        LeaderElectionServiceGrpc.newBlockingStub(channel).withDeadlineAfter(2, TimeUnit.SECONDS);
    Leader leader = stub.leader(Empty.newBuilder().build());
    System.out.println(leader);
  }
}
