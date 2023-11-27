package edu.scu.distributed.models;

import edu.scu.distributed.client.UserClient;
import edu.scu.distributed.server.services.GetMessageRequest;
import edu.scu.distributed.server.services.Status;
import edu.scu.distributed.server.services.SubscribeRequest;

public class Subscriber extends Node {
  String name;

  public Subscriber(String id, String ipAddr, int port) {
    super(id, ipAddr, port);
  }

  @Override
  public void handleCommand(String command) {
    String[] arr = command.split(" ");
    if (arr[0].equals("subscribe")) {
      if (arr.length == 4) {
        String ip = arr[1];
        int port = Integer.parseInt(arr[2]);
        String topicName = arr[3];
        Topic t = new Topic(topicName, ip, port);
        System.out.println("Starting subscribe Task");
        subscribeTo(t);
      }
    } else if (arr[0].equals("unsubscribe")) {
      /*if(arr.length==3){
          String ip = arr[1];
          int port = Integer.parseInt(arr[2]);
          String topicName = arr[3];
          Topic t = new Topic(topicName, ip, port);
          subscribeTo(t, topicName);
      }*/
    } else if (arr[0].equals("getMessages")) {
      if (arr.length == 4) {
        String ip = arr[1];
        int port = Integer.parseInt(arr[2]);
        String topicName = arr[3];
        Topic t = new Topic(topicName, ip, port);
        System.out.println("Starting getMessages Task");
        getMessages(t);
      }
    }
  }

  public void getMessages(Topic t) {
    // ReceiveTask task = new ReceiveTask(t, topicName);
    // task.start();
    GetMessageRequest request =
        GetMessageRequest.newBuilder().setName(t.name).setIpAddress(ip).setPort(port + "").build();
    Status response = UserClient.getStub().getMessages(request);
    System.out.println("response: " + response.getValue());
  }

  public void subscribeTo(Topic t) {
    // SubscribeTask task = new SubscribeTask(t, new
    // Message("SubscribeTo_"+ipAddr+"_"+port+"_"+topicName,""));
    // task.start();
    System.out.println("topic name " + t.name);
    System.out.println("topic ip " + t.ip);
    System.out.println("topic port " + t.port);

    SubscribeRequest request =
        SubscribeRequest.newBuilder().setTopic(t.name).setIpAddress(ip).setPort(port + "").build();
    Status response = UserClient.getStub().subscribeToTopic(request);
    System.out.println("response: " + response.getValue());
  }
}
