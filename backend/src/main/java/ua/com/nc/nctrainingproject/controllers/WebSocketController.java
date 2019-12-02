package ua.com.nc.nctrainingproject.controllers;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import ua.com.nc.nctrainingproject.models.Message;

@Controller
public class WebSocketController {

    @MessageMapping("/send")
    @SendTo("/notifications/get")
    public Message greeting(Message message) throws Exception {
//        Thread.sleep(1000); // simulated delay
        return message;
    }
}
