package edu.scu.distributed.server;

import edu.scu.distributed.models.Configuration;
import edu.scu.distributed.models.Events;
import edu.scu.distributed.models.Node;
import edu.scu.distributed.server.commons.Util;
import edu.scu.distributed.server.leaderelection.JobScheduler;
import edu.scu.distributed.server.services.TopicService;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import java.io.IOException;
import java.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TopicServiceImpl implements PubSubServer {

  private static Events events;
  private final int port;
  private TopicService topicService;

  private final Logger log = LoggerFactory.getLogger(PubSubServerImpl.class);

  JobScheduler scheduler;

  public TopicServiceImpl(Events events, int port) {
    this.events = events;
    this.port = port;
  }

  public static void main(String[] args) throws IOException {
    // TODO: make standard arg with option
    int port = Integer.parseInt(args[0]);
    System.out.println("Given port: " + port);
    String confFilename = "/Users/nidhi/Documents/project/pub-sub-project/config";
    Configuration configuration = Util.getConfiguration(confFilename);
    // NodeSelector nodeSelector = Util.createCluster(configuration, port);
    Events events1 = new Events("10.0.0.131", port);
    TopicServiceImpl server = new TopicServiceImpl(events1, port);
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

  public void start() throws InterruptedException {
    // Thread.sleep(10000);
    // this.leaderElection.start();
    // while(true){
    //   Thread.sleep(100000);
    //   log.info("leaderElection1 leader:" + leaderElection.getLeaderNode());
    // }

  }

  @Override
  public void createServer() throws IOException, InterruptedException {
    // topicService = new TopicService(this.topics);
    Server server = ServerBuilder.forPort(this.port).addService(topicService).build();

    server.start();
    this.start();

    log.info(
        String.format("Server started: %s", Arrays.toString(server.getListenSockets().toArray())));
    server.awaitTermination();
  }

  @Override
  public Node getLeaderNode() {
    return null; // this.leaderElection.getLeaderNode();
  }

  @Override
  public Node getCurrentNode() {
    return null; // this.leaderElection.getCurrentNode();
  }

  @Override
  public TopicService getTopicService() {
    return topicService;
  }
}
