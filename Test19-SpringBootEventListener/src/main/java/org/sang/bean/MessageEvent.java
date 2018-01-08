package org.sang.bean;

import org.springframework.context.ApplicationEvent;

public class MessageEvent extends ApplicationEvent {
  private static final long serialVersionUID = 3141376430452140647L;

  private String message;

  public MessageEvent(Object source, String message) {
    super(source);
    this.message = message;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

}