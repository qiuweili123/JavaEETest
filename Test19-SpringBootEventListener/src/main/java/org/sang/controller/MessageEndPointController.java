package org.sang.controller;

import org.sang.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Callable;

@RestController
@RequestMapping("/event")
public class MessageEndPointController {

  @Autowired
  private MessageService messageService;

  /**
   * 同步调用
   * @param message
   * @return
   */
  @RequestMapping(value = "/api/say", method = RequestMethod.GET,
      produces = "application/json; charset=UTF-8")
  public String say(String message) {
    messageService.say(message);
    messageService.asyncSay(message);
    return message;
  }

  /**
   * 异步调用
   * @param message
   * @return
   */
  @RequestMapping(value = "/api/call", method = RequestMethod.GET,
      produces = "application/json; charset=UTF-8")
  public Callable<String> call(String message) {
    messageService.say(message);
    return new Callable<String>() {
      @Override
      public String call() throws Exception {
        messageService.asyncSay(message);
        return message;
      }
    };
  }
}