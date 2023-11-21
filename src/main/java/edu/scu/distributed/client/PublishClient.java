package edu.scu.distributed.client;

import edu.scu.distributed.models.Node;
import java.net.InetSocketAddress;

public interface PublishClient {

  /*
   * 1. parse args for bootstrap servers and topic name
   * 2. get topic meta information from server by leader node
   * 3. Run while for publishing messages by reading input from command line
   * */
  // setup bootstrap servers and topic name and get leader node details. if not throw exception with
  // detailed error
  public void init(InetSocketAddress address, String topicName);

  // keeps reading input messages;
  public void run();

  // return type should be decided later e.g. OffsetInfo
  public String publishMessage(Node leaderNode, String topicName, String msg);
}
