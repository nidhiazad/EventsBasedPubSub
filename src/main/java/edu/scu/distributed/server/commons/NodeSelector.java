package edu.scu.distributed.server.commons;

import edu.scu.distributed.models.Node;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class NodeSelector {

  private Node currentNode;
  private List<Node> clusterNodes;
}
