package org.ai.aicopilotforapi.common.handler;

import org.ai.aicopilotforapi.common.config.WebSocketSessionManager;
import org.springframework.web.socket.*;
import org.springframework.web.socket.handler.TextWebSocketHandler;


public class ChatWebSocketHandler extends TextWebSocketHandler {

    private final WebSocketSessionManager sessionManager = new WebSocketSessionManager();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        sessionManager.register(session);
        // Optionally notify all
        sessionManager.broadcast("User " + session.getId() + " joined the chat");
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String text = message.getPayload();
        // echo back to sender
        sessionManager.sendTo(session.getId(), "You said: " + text);
        // broadcast to others
        sessionManager.broadcast("User " + session.getId() + ": " + text);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        sessionManager.unregister(session);
        sessionManager.broadcast("User " + session.getId() + " left the chat");
    }
}
