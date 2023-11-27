package edu.scu.distributed.models;

import java.util.List;
import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Configuration {
  String app;
  String version;
  String topicDir;
  List<Host> nodes;

  @Getter
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  public static class Host {
    String ip;
    int port;
  }
}
