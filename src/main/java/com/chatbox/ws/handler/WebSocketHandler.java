package com.chatbox.ws.handler;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;

@Component
public class WebSocketHandler extends TextWebSocketHandler {
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        System.out.println("[afterConnectionEstablished] session id" + session.getId());
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        System.out.println("[handleTextMessage]" + message.getPayload());
        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                try {
                    session.sendMessage(new TextMessage("Hello" + UUID.randomUUID()));
                }catch(IOException e) {
                    e.printStackTrace();
                }
            }
        }, 2000L, 2000L);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        System.out.println("[afterConnectionClosed] session id" + session.getId());
    }
}
