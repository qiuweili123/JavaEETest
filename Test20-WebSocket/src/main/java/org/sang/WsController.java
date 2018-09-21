package org.sang;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

/**
 * Created by sang on 16-12-22.
 */
@Controller
public class WsController {
    // @SendTo 是tempalte的简化this.simpMessagingTemplate.convertAndSend("/topic/notice", value)
    @MessageMapping("/welcome")
    @SendTo("/topic/getResponse")
    public ResponseMessage say(RequestMessage message) {
        System.out.println(message.getName());
        return new ResponseMessage("welcome," + message.getName() + " !");
    }

    // @SubscribeMapping("/init")
    public String init() {
        System.out.println("##############init#############");
        return "init";
    }
}
