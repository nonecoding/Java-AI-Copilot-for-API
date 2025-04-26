package org.ai.aicopilotforapi.common.config;

import org.springframework.web.socket.*;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.Map;

public class WebSocketSessionManager {

    // store sessions by an ID
    private final Map<String, WebSocketSession> sessions = new ConcurrentHashMap<>();

    /** Register a session (keyed by session.getId()) */
    public void register(WebSocketSession session) {
        sessions.put(session.getId(), session);
    }

    /** Unregister on close or error */
    public void unregister(WebSocketSession session) {
        sessions.remove(session.getId());
    }

    /** Send a text message to one session */
    public void sendTo(String sessionId, String payload) throws IOException {
        WebSocketSession sess = sessions.get(sessionId);
        if (sess != null && sess.isOpen()) {
            sess.sendMessage(new TextMessage(payload));
        }
    }

    /** Broadcast to all connected sessions */
    public void broadcast(String payload) {
        TextMessage msg = new TextMessage(payload);
        sessions.values().forEach(sess -> {
            if (sess.isOpen()) {
                try { sess.sendMessage(msg); }
                catch (IOException e) { /* log error */ }
            }
        });
    }
}
