package controller.Messages;

import jakarta.websocket.*;
import jakarta.websocket.server.PathParam;
import jakarta.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@ServerEndpoint("/chat/{userId}")
public class ChatWebSocket {
    private static final Map<String, Session> sessions = new ConcurrentHashMap<>();

    @OnOpen
    public void onOpen(Session session, @PathParam("userId") String userId) {
        if (userId != null && !userId.trim().isEmpty()) {
            sessions.put(userId, session);
            System.out.println("WebSocket opened for user: " + userId);
        }
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        try {
            // Broadcast message to relevant sessions
            for (Session s : sessions.values()) {
                if (s.isOpen()) {
                    s.getBasicRemote().sendText(message);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @OnClose
    public void onClose(Session session, @PathParam("userId") String userId) {
        if (userId != null) {
            sessions.remove(userId);
            System.out.println("WebSocket closed for user: " + userId);
        }
    }

    @OnError
    public void onError(Throwable error) {
        error.printStackTrace();
    }

    public static void sendMessage(String userId, String message) {
        Session session = sessions.get(userId);
        if (session != null && session.isOpen()) {
            try {
                session.getBasicRemote().sendText(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}