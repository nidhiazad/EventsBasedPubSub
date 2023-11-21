package edu.scu.distributed.server.leaderelection;

public class Config {
  private int heartbeatInterval = 200;
  private int timeout = 800;

  public Config() {}

  public int timeout() {
    return timeout;
  }

  public int heartbeatInterval() {
    return heartbeatInterval;
  }
}
