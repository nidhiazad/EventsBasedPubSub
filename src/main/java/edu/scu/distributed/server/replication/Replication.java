package edu.scu.distributed.server.replication;

public interface Replication {

  // copy all the topics, states and necessary logs
  public void copyAndReEstablishNode();

  // validate node with other nodes in cluster
  public void validateNode();

  // maintain queue for pending messages
  public void bufferMsgQueue(String topicName, String msg);
}
