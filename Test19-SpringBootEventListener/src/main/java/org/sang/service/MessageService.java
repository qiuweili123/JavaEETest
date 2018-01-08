package org.sang.service;

public interface MessageService {
  void somniloquy(String message);

  void say(String message);

  void asyncSay(String message);
}