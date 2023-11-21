package edu.scu.distributed.models;

public class Message {
  public String message;
  public String sender;

  public Message(String m, String sender) {
    message = m;
    this.sender = sender;
  }
}
