package edu.scu.distributed.models;

public class Message {
  public String message;
  public String sender;

  public Message(String m, String sender) {
    message = m;
    this.sender = sender;
  }

  @Override
  public String toString() {
    return "Message{" + "message='" + message + '\'' + ", sender='" + sender + '\'' + '}';
  }
}
