package edu.scu.distributed.models;

import edu.scu.distributed.client.UserClient;
import edu.scu.distributed.server.services.Status;

import java.util.Arrays;

public class Publisher extends Node {
  public String name;

  public Publisher(String id, String ipAddr, int port) {
    super(id, ipAddr, port);
  }

  @Override
  public void handleCommand(String command) {
    // publish string message
    String[] arr = command.split(" ");
    int count = 5; // Start at index 5 for the message

    if (arr[0].equals("send")) {
      String topicIp = arr[1];
      int topicPort = Integer.parseInt(arr[2]);
      String topicName = arr[3];
      String message = "";

      // Concatenate all words starting from index 5
      for (int i = count; i < arr.length; i++) {
        System.out.println(message);
        message = message + " " + arr[i];
      }

      SendMessage(new Topic(topicName, topicIp, topicPort), new Message(message.trim(), this.name));
    }
  }


  public void SendMessage(Topic t, Message m) {

    edu.scu.distributed.server.services.Message request =
        edu.scu.distributed.server.services.Message.newBuilder()
            .setTopic(t.name)
            .setData(m.message)
            .setSender(ip + ":" + port)
            .setReplicate(true)
            .setReceiverPort(UserClient.serverPort)
            .build();
    Status response = UserClient.getStub().publishMessage(request);
    System.out.println("response: " + response.getValue());
  }
}
