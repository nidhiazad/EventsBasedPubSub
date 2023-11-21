package edu.scu.distributed.models;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Data
@Builder
@Getter
public class Node {
  public String id;
  public String ip;
  public int port;

  public void handleCommand(String command) {}
}
