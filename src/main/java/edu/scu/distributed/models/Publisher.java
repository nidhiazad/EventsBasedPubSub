package edu.scu.distributed.models;

import edu.scu.distributed.client.ClientService;
import edu.scu.distributed.server.services.Status;

public class Publisher extends Node {
  public String name;

  public Publisher(String id, String ipAddr, int port) {
    super(id, ipAddr, port);
  }

  @Override
  public void handleCommand(String command) {
    // publish string message
    String[] arr = command.split(" ");
    int count = 4;
    if (arr[0].equals("send")) {
      String topicIp = arr[1];
      int topicPort = Integer.parseInt(arr[2]);
      String topicName = arr[3];
      String message = "";
      for (int i = count; i < arr.length; i++) {
        System.out.println(message);
        message = message + " " + arr[i];
      }
      //  String message = arr[4];
      SendMessage(new Event(topicName, topicIp, topicPort), new Message(message, this.name));
    }
  }

  public void SendMessage(Event t, Message m) {
    System.out.println("message is " + m);
    edu.scu.distributed.server.services.Message request =
        edu.scu.distributed.server.services.Message.newBuilder()
            .setTopic(t.name)
            .setData(m.message)
            .setSender(ip + ":" + port)
            .setReplicate(true)
            .setReceiverPort(ClientService.serverPort)
            .build();
    Status response = ClientService.getStub().publishMessage(request);
    System.out.println("response: " + response.getValue());
  }
}
