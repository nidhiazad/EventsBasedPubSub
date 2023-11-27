package edu.scu.distributed.server.services;

import edu.scu.distributed.models.Node;
import java.util.List;

public interface WriteService {

  // return response with offset-id
  public String writeMsg(String topicName, String msg);

  public String writeMsg(String topicName, String msg, boolean replica);

  // retry in case of failure
  public boolean callReplication(
      List<Node> withoutCurrentNode, String topicName, String msg, String offset);
}
