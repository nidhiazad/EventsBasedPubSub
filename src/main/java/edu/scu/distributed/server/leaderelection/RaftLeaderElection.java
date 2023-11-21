package edu.scu.distributed.server.leaderelection;

import com.google.protobuf.ByteString;
import edu.scu.distributed.models.Node;
import edu.scu.distributed.server.commons.NodeSelector;
import edu.scu.distributed.server.leaderelection.clock.LogicalClock;
import edu.scu.distributed.server.leaderelection.clock.LogicalTimestamp;
import edu.scu.distributed.server.leaderelection.services.Cluster;
import edu.scu.distributed.server.leaderelection.state.State;
import edu.scu.distributed.server.leaderelection.state.StateMachine;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RaftLeaderElection {

  private static final Logger log = LoggerFactory.getLogger(RaftLeaderElection.class);

  private final StateMachine stateMachine;

  private final LogicalClock clock = new LogicalClock();

  private final AtomicReference<LogicalTimestamp> currentTerm = new AtomicReference<>();

  private final JobScheduler timeoutScheduler;

  private final JobScheduler heartbeatScheduler;
  private final String memberId;
  private final int timeout;
  private final Config config;
  private final AtomicReference<String> currentLeader = new AtomicReference<>("");
  private final NodeSelector nodeSelector;
  private final Cluster cluster;
  private JobScheduler scheduler;

  public RaftLeaderElection(NodeSelector nodeSelector) {
    this.config = new Config();
    this.timeout =
        new Random().nextInt(config.timeout() - (config.timeout() / 2)) + (config.timeout() / 2);
    this.nodeSelector = nodeSelector;
    this.memberId = nodeSelector.getCurrentNode().getId();
    this.cluster = new Cluster();
    this.stateMachine =
        StateMachine.builder()
            .init(State.INACTIVE)
            .addTransition(State.INACTIVE, State.FOLLOWER)
            .addTransition(State.FOLLOWER, State.CANDIDATE)
            .addTransition(State.CANDIDATE, State.LEADER)
            .addTransition(State.LEADER, State.FOLLOWER)
            .build();

    this.stateMachine.on(State.FOLLOWER, becomeFollower());
    this.stateMachine.on(State.CANDIDATE, becomeCandidate());
    this.stateMachine.on(State.LEADER, becomeLeader());
    //
    this.currentTerm.set(clock.tick());
    this.timeoutScheduler = new JobScheduler(onHeartbeatNotReceived());
    this.heartbeatScheduler = new JobScheduler(sendHeartbeat());
  }

  public void start() {
    this.stateMachine.transition(State.FOLLOWER, currentTerm.get());
  }

  public String leaderId() {
    return currentLeader.get();
  }

  public Node getLeaderNode() {
    if (leaderId().equals(this.memberId)) return this.nodeSelector.getCurrentNode();
    else
      return this.nodeSelector.getClusterNodes().stream()
          .filter(node -> node.getId().equals(this.leaderId()))
          .collect(Collectors.toList())
          .get(0);
  }

  public LogicalTimestamp currentTerm() {
    return this.currentTerm.get();
  }

  public HeartbeatResponse onHeartbeat(HeartbeatRequest request) {
    log.debug("member [{}] received heartbeat request: [{}]", this.memberId, request);
    this.timeoutScheduler.reset(this.timeout);

    LogicalTimestamp term = LogicalTimestamp.fromBytes(request.getTerm().toByteArray());
    if (currentTerm.get().isBefore(term)) {
      log.info(
          "member: [{}] currentTerm: [{}] is before: [{}] setting new seen term.",
          this.memberId,
          currentTerm.get(),
          term);
      currentTerm.set(term);
    }

    if (!stateMachine.currentState().equals(State.FOLLOWER)) {

      log.debug(
          "member [{}] currentState [{}] and received heartbeat. becoming FOLLOWER.",
          this.memberId,
          stateMachine.currentState());
      stateMachine.transition(State.FOLLOWER, term);
    }

    this.currentLeader.set(request.getMemberId());

    return HeartbeatResponse.newBuilder()
        .setMemberId(this.memberId)
        .setTerm(ByteString.copyFrom(currentTerm.get().toBytes()))
        .build();
  }

  public Leader leader() {
    return Leader.newBuilder()
        .setLeaderId(this.currentLeader.get())
        .setMemberId(this.memberId)
        .build();
  }

  public VoteResponse onRequestVote(VoteRequest request) {

    LogicalTimestamp term = LogicalTimestamp.fromBytes(request.getTerm().toByteArray());

    boolean voteGranted = currentTerm.get().isBefore(term);
    log.info(
        "member [{}:{}] received vote request: [{}] voteGranted: [{}].",
        this.memberId,
        stateMachine.currentState(),
        request,
        voteGranted);

    if (currentTerm.get().isBefore(term)) {
      log.info(
          "member: [{}] currentTerm: [{}] is before: [{}] setting new seen term.",
          this.memberId,
          currentTerm.get(),
          term);
      currentTerm.set(term);
    }
    return VoteResponse.newBuilder().setGranted(voteGranted).setCandidateId(this.memberId).build();
  }

  private Consumer onHeartbeatNotReceived() {
    return toCandidate -> {
      this.timeoutScheduler.stop();
      this.currentTerm.set(clock.tick(currentTerm.get()));
      this.stateMachine.transition(State.CANDIDATE, currentTerm.get());
      log.info(
          "member: [{}] didn't receive heartbeat until timeout: [{}ms] became: [{}]",
          this.memberId,
          timeout,
          stateMachine.currentState());
    };
  }

  /**
   * find all leader election services that are remote and send current term to all of them.
   *
   * @return consumer.
   */
  private Consumer sendHeartbeat() {
    return heartbeat ->
        nodeSelector
            .getClusterNodes()
            .forEach(
                node -> {
                  log.debug(
                      "node: [{}] with address [{}:{}] sending heartbeat",
                      node.getId(),
                      node.getIp(),
                      node.getPort());
                  //  send request
                  HeartbeatRequest request =
                      HeartbeatRequest.newBuilder()
                          .setMemberId(this.memberId)
                          .setTerm(ByteString.copyFrom(this.currentTerm.get().toBytes()))
                          .build();
                  HeartbeatResponse response = this.cluster.requestHeartbeat(node, request);
                  if (response == null) {
                    log.debug(
                        "Error requesting for heartbeat response from node: {}, address: {}:{}",
                        node.getId(),
                        node.getIp(),
                        node.getPort());
                  } else {
                    LogicalTimestamp term =
                        LogicalTimestamp.fromBytes(response.getTerm().toByteArray());
                    if (currentTerm.get().isBefore(term)) {
                      log.debug(
                          "member: [{}] currentTerm: [{}] is before: [{}] setting new seen term.",
                          this.memberId,
                          currentTerm.get(),
                          term);
                      currentTerm.set(term);
                    }
                  }
                });
  }

  private void sendElectionCampaign() {
    CountDownLatch consensus =
        new CountDownLatch(((this.nodeSelector.getClusterNodes().size() + 1) / 2));
    this.nodeSelector
        .getClusterNodes()
        .forEach(
            node -> {
              log.info(
                  "member: [{}] sending vote request to: [{}:{}].",
                  this.memberId,
                  node.getIp(),
                  node.getPort());

              VoteRequest request =
                  VoteRequest.newBuilder()
                      .setTerm(ByteString.copyFrom(currentTerm.get().toBytes()))
                      .setCandidateId(node.getId())
                      .build();
              VoteResponse response = this.cluster.requestVote(node, request);
              if (response == null) {
                log.debug(
                    "Error requesting for vote response from node: {}, address: {}:{}",
                    node.getId(),
                    node.getIp(),
                    node.getPort());

              } else {
                log.debug("member: [{}] received vote response: [{}].", this.memberId, response);
                if (response.getGranted()) {
                  consensus.countDown();
                }
              }
            });
    try {
      boolean countZero = consensus.await(5, TimeUnit.SECONDS);
      stateMachine.transition(State.LEADER, currentTerm);

    } catch (InterruptedException e) {
      stateMachine.transition(State.FOLLOWER, currentTerm);
    }
  }

  /** node became follower when it initiates */
  private Consumer becomeFollower() {
    return follower -> {
      log.info("member: [{}] has become: [{}].", this.memberId, stateMachine.currentState());

      heartbeatScheduler.stop();
      timeoutScheduler.start(this.timeout);

      // spread the gossip about me as follower.
      // this.microservices.cluster().spreadGossip(newLeaderElectionGossip(State.FOLLOWER));
      CompletableFuture.runAsync(this::onBecomeFollower);
    };
  }

  public void onBecomeFollower() {
    log.info(this.memberId + " (" + this.currentTerm().toLong() + ") << Become A Follower");
    scheduler.stop();
  }

  private Consumer becomeLeader() {
    return leader -> {
      log.info("member: [{}] has become: [{}].", this.memberId, stateMachine.currentState());

      timeoutScheduler.stop();
      heartbeatScheduler.start(config.heartbeatInterval());
      this.currentLeader.set(this.memberId);

      // spread the gossip about me as a new leader.
      // this.microservices.cluster().spreadGossip(newLeaderElectionGossip(State.LEADER));
      CompletableFuture.runAsync(this::onBecomeLeader);
    };
  }

  public void onBecomeLeader() {
    log.info(
        this.memberId
            + " ("
            + this.currentTerm().toLong()
            + ") >>>>>>>    +++ Become A Leader +++");
    scheduler = new JobScheduler(leaderIsWorking());
    scheduler.start(1000);
  }

  private Consumer<Object> leaderIsWorking() {
    return doingSomeWork -> {
      log.info("{}: I am working...", this.memberId);
    };
  }

  private Consumer becomeCandidate() {
    return election -> {
      log.info("member: [{}] has become: [{}].", this.memberId, stateMachine.currentState());

      heartbeatScheduler.stop();
      currentTerm.set(clock.tick());
      sendElectionCampaign();

      // spread the gossip about me as candidate.
      // this.microservices.cluster().spreadGossip(newLeaderElectionGossip(State.CANDIDATE));
      CompletableFuture.runAsync(this::onBecomeCandidate);
    };
  }

  public void onBecomeCandidate() {
    log.info(this.memberId + " (" + this.currentTerm().toLong() + ") ?? Become A Candidate");
    scheduler.stop();
  }

  public Node getCurrentNode() {
    return this.nodeSelector.getCurrentNode();
  }
}
