package edu.scu.distributed.models;

import com.google.gson.Gson;
import java.io.*;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Event extends Node {
  public Map<String, String> subscribersToLatestMessageIdSent;

  public List<Message> messages;

  public String name;

  public Event(String name, String ipAddr, int port) {
    super("", ipAddr, port);
    messages = new ArrayList<>();
    subscribersToLatestMessageIdSent = new HashMap<>();
    this.name = name;
  }

  public void writeMetadata() {
    try {
      Gson gson = new Gson();
      String data = gson.toJson(subscribersToLatestMessageIdSent);
      File yourFile = new File(Events.ROOT_FOLDER_NAME + name + "/metadata.json");
      yourFile.createNewFile();
      FileOutputStream oFile = new FileOutputStream(yourFile, false);
      System.out.println("writing to metadata: " + data);
      oFile.write(data.getBytes());
      oFile.close();
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  public void readMetadata() {
    try {
      Gson gson = new Gson();
      Files.createDirectories(Paths.get(Events.ROOT_FOLDER_NAME + name));
      File yourFile = new File(Events.ROOT_FOLDER_NAME + name + "/metadata.json");
      yourFile.createNewFile();
      FileInputStream iFile = new FileInputStream(yourFile);
      String data = new String(iFile.readAllBytes());
      System.out.println("[readMetadata] " + data);
      subscribersToLatestMessageIdSent =
          gson.fromJson(data, subscribersToLatestMessageIdSent.getClass());
      if (subscribersToLatestMessageIdSent == null)
        subscribersToLatestMessageIdSent = new HashMap<>();
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  public int noOfMessagesForTopic() {
    return new File(Events.ROOT_FOLDER_NAME + name).list().length;
  }

  public void addMessage(Message m) {
    try {
      Files.createDirectories(Paths.get(Events.ROOT_FOLDER_NAME + name));
      File yourFile1 = new File(Events.ROOT_FOLDER_NAME + name + "/metadata.json");
      yourFile1.createNewFile();
      int newMessageId = noOfMessagesForTopic();
      System.out.println(
          "file location: " + Events.ROOT_FOLDER_NAME + name + "/" + newMessageId + ".json");
      File yourFile = new File(Events.ROOT_FOLDER_NAME + name + "/" + newMessageId + ".json");
      yourFile.createNewFile();
      FileOutputStream oFile = new FileOutputStream(yourFile, false);
      Gson gson = new Gson();
      String data = gson.toJson(m);
      oFile.write(data.getBytes());
      System.out.println("writing message to file: " + data);
      oFile.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Override
  public void handleCommand(String command) {
    // print all messages
    // remove message at index
    String[] arr = command.split(" ");
    if (arr[0].equals("print")) {
      System.out.println();
      for (int i = 0; i < messages.size(); i++) {
        System.out.println("[" + messages.get(i).sender + "]" + messages.get(i).message);
      }
    }
    if (arr[0].equals("remove")) {
      System.out.println();
      int index = Integer.parseInt(arr[1]);
      messages.remove(index);
      System.out.println("message removed successfully");
    }
  }

  public void AddSubscriber(Subscriber s) {
    subscribersToLatestMessageIdSent.put(s.ip + ":" + s.port, 0 + "");
  }

  public void SendToAll(Message m) {
    // no need to send. subscribers will poll for data
    // for(int i=0;i<subscribers.size();i++){
    //    SendToSubscriberTask task = new SendToSubscriberTask()
    // }
  }

  public List<Message> getMessagesSubList(int from) {
    List<Message> messages1 = new ArrayList<>();
    try {
      Gson gson = new Gson();
      int id = from == 0 ? 1 : from;
      while (true) {
        File yourFile = new File(Events.ROOT_FOLDER_NAME + name + "/" + id + ".json");
        FileInputStream iFile = new FileInputStream(yourFile);
        String data = new String(iFile.readAllBytes());
        System.out.println("[getMessagesSubList] " + data);
        Message newMessage = gson.fromJson(data, Message.class);
        messages1.add(newMessage);
        id++;
      }
    } catch (Exception ex) {
      ex.printStackTrace();
    }
    return messages1;
  }

  public String fetchCommand(Subscriber s) {
    readMetadata();
    String result = "";
    System.out.println(
        "[topic.fetchCommand] total subscribers- "
            + subscribersToLatestMessageIdSent.keySet().size());
    Subscriber foundSub = null;
    int latestIndex = 0;
    if (!subscribersToLatestMessageIdSent.containsKey(s.ip + ":" + s.port)) {
      System.out.println("invalid subscriber");
      return "";
    }
    System.out.println("found subscriber: " + s.ip + ", " + s.port);
    latestIndex = Integer.parseInt(subscribersToLatestMessageIdSent.get(s.ip + ":" + s.port));
    // int latestIndex = subscribersToLatestMessageIdSent.get(s);
    System.out.println("latestIndex- " + latestIndex);
    if (latestIndex < 0) {
      System.out.println("invalid index");
      return "";
    }
    // List<Message> messagesToSend = messages.subList(latestIndex, messages.size());
    List<Message> messagesToSend = getMessagesSubList(latestIndex);
    Gson gson = new Gson();
    result = gson.toJson(messagesToSend);
    subscribersToLatestMessageIdSent.put(s.ip + ":" + s.port, messages.size() + "");
    writeMetadata();
    return result;
  }

  @Override
  public String toString() {
    return "Topic{"
        + "subscribersToLatestMessageIdSent="
        + subscribersToLatestMessageIdSent
        + ", messages="
        + messages
        + ", name='"
        + name
        + '\''
        + ", id='"
        + id
        + '\''
        + ", ip='"
        + ip
        + '\''
        + ", port="
        + port
        + '}';
  }

  class SendMessagesTask extends Thread {
    Subscriber sub;
    String message;

    public SendMessagesTask(Subscriber s, String m) {
      sub = s;
      this.message = m;
    }

    public void run() {
      Socket socket;
      try {
        socket = new Socket(sub.ip, sub.port);
        PrintWriter output = new PrintWriter(socket.getOutputStream());
        BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String messageTXT = message;
        System.out.println("Sending message with text:" + messageTXT);
        output.write("<header>\n" + messageTXT + "\n");
        output.flush();
        output.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }
}
