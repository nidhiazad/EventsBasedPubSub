package edu.scu.distributed.models;

import java.io.*;
import java.net.Socket;
import java.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Topics extends Node {

  public static String ROOT_FOLDER_NAME =
      "/Users/nidhi/Documents/project/pub-sub-project/topics/serv1";
  // public Map<Subscriber, Integer> subscribersToLatestMessageIdSent;

  private static final Logger log = LoggerFactory.getLogger(Topics.class);

  public List<Topic> topicsList;

  public Topics(String ipAddr, int port) {
    super("", ipAddr, port);
    // messages = new ArrayList<>();
    topicsList = new ArrayList<>();
  }

  @Override
  public void handleCommand(String command) {
    // print all messages
    // remove message at index
    String[] arr = command.split(" ");
    if (arr[0].equals("print")) {
      System.out.println();
      for (int t = 0; t < topicsList.size(); t++) {
        List<Message> messages = topicsList.get(t).messages;
        System.out.println("topic : " + topicsList.get(t).name);
        for (int i = 0; i < messages.size(); i++) {
          System.out.println("[" + messages.get(i).sender + "]" + messages.get(i).message);
        }
      }
    }
    if (arr[0].equals("remove")) {
      /*System.out.println();
      int index=Integer.parseInt(arr[1]);
      messages.remove(index);
      System.out.println("message removed successfully");*/
    }
  }

  public void AddSubscriber(int topicId, Subscriber s) {
    topicsList.get(topicId).AddSubscriber(s);
  }

  public void SendToAll(Message m) {
    // no need to send. subscribers will poll for data
    // for(int i=0;i<subscribers.size();i++){
    //    SendToSubscriberTask task = new SendToSubscriberTask()
    // }
  }

 public void loadTopics() {
    File rootFolder = new File(ROOT_FOLDER_NAME);

    if (!rootFolder.exists() || !rootFolder.isDirectory()) {
      System.err.println("Invalid root folder: " + ROOT_FOLDER_NAME);
      System.err.println("Exists: " + rootFolder.exists());
      System.err.println("Is directory: " + rootFolder.isDirectory());
      return;
    }

    File[] directories = rootFolder.listFiles(File::isDirectory);

    if (directories == null) {
      System.err.println("Error listing directories.");
      return;
    }

    System.out.println("Number of directories: " + directories.length);
    topicsList = new ArrayList<>();

    for (File directory : directories) {
      System.out.println("Directory name: " + directory.getName());
      topicsList.add(new Topic("/" + directory.getName(), "", 0));
    }
  }

  public int findTopic(String topicName) {
    loadTopics();

    for (int i = 0; i < topicsList.size(); i++) {
      if (topicsList.get(i).name.equals(topicName)) {
        System.out.println("Found topic ID - " + i);
        log.info("Found topic ID - {}", id);
        return i;
      }
    }
    return -1;
  }

  public String addSubscriberOperation(String operation) {
    // example - SubscribeTo_localhost_8070_/topic1
    String arr[] = operation.split("_");
    String topicName = arr[3];
    int topicId = findTopic(topicName);
    if (topicId < 0) {
      System.out.println("Topic does not exist");
      return "Topic does not exist";
    }
    topicsList.get(topicId).AddSubscriber(new Subscriber("", arr[1], Integer.parseInt(arr[2])));
    topicsList.get(topicId).writeMetadata();
    return "";
  }

  public String publishMessageOperation(Message operation) {
    String arr[] = operation.message.split("_");
    String topic = arr[0];
    int foundTopicId = findTopic(topic);
    if (foundTopicId >= 0) {
      topicsList.get(foundTopicId).messages.add(new Message(arr[1], operation.sender));

      topicsList.get(foundTopicId).addMessage(new Message(arr[1], operation.sender));
    } else {
      System.out.println("Adding new topic - " + topic);
      topicsList.add(new Topic(topic, "", 0));
      System.out.println(
          "Adding new message to topic - " + arr[1] + ", sender: " + operation.sender);
      topicsList.get(topicsList.size() - 1).messages.add(new Message(arr[1], operation.sender));
      topicsList.get(topicsList.size() - 1).readMetadata();
      topicsList.get(topicsList.size() - 1).addMessage(new Message(arr[1], operation.sender));
    }
    return "";
  }

  public String getMessagesOperation(Message m) {
    String arr[] = m.message.split("_");
    String topicName = arr[1];
    int topicId = findTopic(topicName);
    String result =
        topicsList.get(topicId).fetchCommand(new Subscriber("", arr[2], Integer.parseInt(arr[3])));
    // SendMessagesTask task=new SendMessagesTask(new Subscriber(arr[1], Integer.parseInt(arr[2])),
    // result);
    // task.start();
    return result;
  }

  // @Override
  public String operation(Message m) {
    System.out.println("[Topic.operation] recieved message - " + m.message);
    if (m.message.startsWith("SubscribeTo")) {
      addSubscriberOperation(m.message);
    } else if (m.message.startsWith("GetMessages")) {
      getMessagesOperation(m);
    } else {
      publishMessageOperation(m);
      // messages.add(m);
      // SendToAll(m);
    }
    return "";
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
