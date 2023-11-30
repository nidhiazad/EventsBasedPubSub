package edu.scu.distributed.server;

import edu.scu.distributed.models.Configuration;
import edu.scu.distributed.models.Events;
import edu.scu.distributed.models.Node;
import edu.scu.distributed.server.commons.NodeSelector;
import edu.scu.distributed.server.commons.Util;
import edu.scu.distributed.server.leaderelection.JobScheduler;
import edu.scu.distributed.server.leaderelection.RaftLeaderElection;
import edu.scu.distributed.server.leaderelection.services.LeaderElectionService;
import edu.scu.distributed.server.services.TopicService;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import java.io.IOException;
import java.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PubSubServerImpl implements PubSubServer {

  private final RaftLeaderElection leaderElection;
  private final int port;

  private final Logger log = LoggerFactory.getLogger(PubSubServerImpl.class);

  JobScheduler scheduler;

  private NodeSelector selector;

  public PubSubServerImpl(NodeSelector nodeSelector, int port) {
    leaderElection = new RaftLeaderElection(nodeSelector);
    this.port = port;
    selector = nodeSelector;
  }

  public static void main(String[] args) throws IOException {
    // TODO: make standard arg with option
    String confFilename = args[0];
    int port = Integer.parseInt(args[1]);

    System.out.println("Given port: " + port);
    System.out.println("Given config_file: " + confFilename);
    Configuration configuration = Util.getConfiguration(confFilename);
    // Topics.ROOT_FOLDER_NAME = configuration.getTopicDir();
    Events.ROOT_FOLDER_NAME = args[2];
    NodeSelector nodeSelector = Util.createCluster(configuration, port);
    PubSubServerImpl server = new PubSubServerImpl(nodeSelector, port);
    try {
      server.init();
      //  server start
      server.createServer();

    } catch (IOException | InterruptedException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public void init() throws IOException {}

  public void start() {

    this.leaderElection.start();
  }

  @Override
  public void createServer() throws IOException, InterruptedException {
    Server server =
        ServerBuilder.forPort(this.port)
            .addService(new LeaderElectionService(this.leaderElection))
            .addService(new TopicService(this.selector))
            .build();

    server.start();
    this.start();

    log.info(
        String.format("Server started: %s", Arrays.toString(server.getListenSockets().toArray())));
    server.awaitTermination();
  }

  @Override
  public Node getLeaderNode() {
    return this.leaderElection.getLeaderNode();
  }

  @Override
  public Node getCurrentNode() {
    return this.leaderElection.getCurrentNode();
  }

  @Override
  public TopicService getTopicService() {
    return null;
  }
}
