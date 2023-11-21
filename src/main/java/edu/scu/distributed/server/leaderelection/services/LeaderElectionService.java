package edu.scu.distributed.server.leaderelection.services;

import edu.scu.distributed.server.leaderelection.*;
import io.grpc.stub.StreamObserver;

public class LeaderElectionService extends LeaderElectionServiceGrpc.LeaderElectionServiceImplBase {

  private final RaftLeaderElection leaderElection;

  public LeaderElectionService(RaftLeaderElection leaderElection) {
    this.leaderElection = leaderElection;
  }

  @Override
  public void onHeartbeat(
      HeartbeatRequest request, StreamObserver<HeartbeatResponse> responseObserver) {
    // super.onHeartbeat(request, responseObserver);
    HeartbeatResponse response = leaderElection.onHeartbeat(request);
    responseObserver.onNext(response);
    responseObserver.onCompleted();
  }

  @Override
  public void onRequestVote(VoteRequest request, StreamObserver<VoteResponse> responseObserver) {
    // super.onRequestVote(request, responseObserver);
    VoteResponse response = leaderElection.onRequestVote(request);
    responseObserver.onNext(response);
    responseObserver.onCompleted();
  }

  @Override
  public void leader(Empty request, StreamObserver<Leader> responseObserver) {
    Leader leader = leaderElection.leader();
    responseObserver.onNext(leader);
    responseObserver.onCompleted();
  }
}
