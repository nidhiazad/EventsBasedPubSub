package edu.scu.distributed.client;

import edu.scu.distributed.models.Events;
import edu.scu.distributed.models.Node;
import edu.scu.distributed.models.Publisher;
import edu.scu.distributed.models.Subscriber;
import edu.scu.distributed.server.services.TopicServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import java.util.Scanner;

public class ClientService {
  public static String serverIPAddress;
  public static int serverPort;

  public static TopicServiceGrpc.TopicServiceBlockingStub getStub() {
    ManagedChannel channel =
        ManagedChannelBuilder.forAddress(serverIPAddress, serverPort).usePlaintext().build();
    TopicServiceGrpc.TopicServiceBlockingStub stub = TopicServiceGrpc.newBlockingStub(channel);
    return stub;
  }

  public static void main(String[] args) {

    serverIPAddress = args[3]; // "10.0.0.131";
    serverPort = Integer.parseInt(args[4]); // 6000;

    String nodeType = args[0];
    String ipAddr = args[1];
    String port = args[2];
    System.out.println("nodeType:" + nodeType + ", ipAddr:" + ipAddr + ", port:" + port);

    Node node = null;
    if (nodeType.equals("publisher")) {
      node = new Publisher("", ipAddr, Integer.parseInt(port));
    } else if (nodeType.equals("subscriber")) {
      node = new Subscriber("", ipAddr, Integer.parseInt(port));
    } else if (nodeType.equals("topics")) {
      node = new Events(ipAddr, Integer.parseInt(port));
    } else {
      System.out.println("invalid node type. Exiting..");
      return;
    }

    Scanner sc = new Scanner(System.in);
    while (true) {
      System.out.print("\n" + nodeType + ": ");
      String command = sc.nextLine();
      node.handleCommand(command);
    }
  }
}
