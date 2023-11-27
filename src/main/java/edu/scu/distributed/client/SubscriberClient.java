package edu.scu.distributed.client;

import java.net.InetSocketAddress;

public interface SubscriberClient {

  // TODO add class variables for topic and leader node

  // Bootstrap servers and topic-name. Get leader node details and get topic meta-details(like
  // offset)
  public void init(InetSocketAddress address, String topic);

  // Poll new messages offsets  from topic for every POLL_TIME_MS
  public void run();

  // current offset: 10, new_offset:13 -> loop to get all new messages in sequence
  public void getMessages(int tillOffset);
}
