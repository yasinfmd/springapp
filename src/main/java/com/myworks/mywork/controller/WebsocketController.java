package com.myworks.mywork.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@RestController
public class WebsocketController {
    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @EventListener
    public void handleWebSocketConnectListener(SessionConnectedEvent event) {
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
        System.out.println("Received a new web socket connection: " + headerAccessor.getSessionId());
        System.out.println("Received a new web socket connection");

    }
    @EventListener
    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
        System.out.println("Web socket connection closed: " + headerAccessor.getSessionId());
        // Bağlantı kapandığında yapılacak işlemler
    }

    @MessageExceptionHandler
    @MessageMapping("/hello") //app/hello
    @SendTo("/topic/greetings")
    public String greeting(@Payload String message) throws Exception {
        Thread.sleep(1000); // simulated delay
        return message + "Yasin";
    }
    @MessageMapping("/private-message")
    public  String privateMsg(String msg){
      //  this.simpMessagingTemplate.convertAndSend("/topşc/msg","Mesajjjjj");
        //user/foo/private
        this.simpMessagingTemplate.convertAndSendToUser(msg,"/private",msg);
        return  msg.toUpperCase();
    }
}
