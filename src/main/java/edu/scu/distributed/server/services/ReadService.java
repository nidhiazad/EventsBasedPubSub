package edu.scu.distributed.server.services;

public interface ReadService {

  // make sure validate offset is present in current node
  String readOffset(String topicName, int offset);
}
