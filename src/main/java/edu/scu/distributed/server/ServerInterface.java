package edu.scu.distributed.server;

import edu.scu.distributed.models.Node;
import edu.scu.distributed.server.services.TopicService;
import java.io.IOException;

public interface ServerInterface {

  // read config file for all the nodes, initialize the nodes
  public void init() throws IOException;

  // create server. maybe move to init
  public void createServer() throws IOException, InterruptedException;

  // overall cluster leader node;
  public Node getLeaderNode();

  // this nodes info
  public Node getCurrentNode();

  public TopicService getTopicService();
}
