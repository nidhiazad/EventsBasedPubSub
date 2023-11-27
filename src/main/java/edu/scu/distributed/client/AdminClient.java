package edu.scu.distributed.client;

import java.net.InetSocketAddress;

public interface AdminClient {

  // Bootstrap servers and topic-name. Get leader node details and get topic meta-details(like
  // offset)
  public void init(InetSocketAddress address);

  // Parse args to decide and execute action
  public void action(String[] args);

  public boolean createTopic(String topicName);

  public boolean deleteTopic(String topicName);

  public enum ADMIN_ACTIONS {
    CREATE_TOPIC,
    DELETE_TOPIC
  }
}
