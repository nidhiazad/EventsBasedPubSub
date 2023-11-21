package edu.scu.distributed.server.commons;

import edu.scu.distributed.models.Configuration;
import edu.scu.distributed.models.Node;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Util {

  public List<Node> getAllNodes() {
    return new ArrayList<>();
  }
  ;

  // register nodes in cluster
  public void registerNode() {
    return;
  }
  ;

  // deregister node in cluster
  public void deregisterNode() {
    return;
  }

  public static InetAddress getLocalIpAddress() {
    InetAddress ipAddress;
    try (Socket socket = new Socket()) {
      socket.connect(new InetSocketAddress("google.com", 80));
      ipAddress = socket.getLocalAddress();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    return ipAddress;
  }

  public static Configuration getConfiguration(String confFilename) throws IOException {
    Config config = new Config(confFilename);
    Configuration configuration = config.getConfiguration();
    System.out.printf("App name: %s%n", configuration.getApp());
    System.out.printf("App version: %s%n", configuration.getApp());

    InetAddress hostIpAddress = Util.getLocalIpAddress();
    System.out.printf("Current node host: %s%n", hostIpAddress.getHostAddress());
    return configuration;
  }

  public static NodeSelector createCluster(Configuration configuration, int port) {
    InetAddress hostIpAddress = Util.getLocalIpAddress();

    System.out.printf("Current node host: %s%n", hostIpAddress.getHostAddress());

    // get nodes and add to nodeSelector
    List<Configuration.Host> nodes = configuration.getNodes();
    System.out.println("Nodes in configuration: " + nodes);

    Node currentNode = null, tmpNode;
    List<Node> cluster = new ArrayList<>();
    for (Configuration.Host node : nodes) {
      tmpNode =
          Node.builder()
              .id(
                  String.valueOf(
                      UUID.nameUUIDFromBytes(
                          String.format("%s:%d", node.getIp(), node.getPort()).getBytes())))
              .ip(node.getIp())
              .port(node.getPort())
              .build();
      if (node.getIp().equals(hostIpAddress.getHostAddress()) && node.getPort() == port) {
        currentNode = tmpNode;
      } else {
        cluster.add(tmpNode);
      }
    }
    if (currentNode == null)
      throw new RuntimeException("No node ip matches current running host ip");
    return NodeSelector.builder().currentNode(currentNode).clusterNodes(cluster).build();
  }
}
