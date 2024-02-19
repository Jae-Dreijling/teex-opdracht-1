package nl.han.se.pizzanu;

import com.google.gson.Gson;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

@Service
public class SocketHandler extends TextWebSocketHandler {

    List<WebSocketSession> sessions = new CopyOnWriteArrayList<>();

    public void pingAll() {
        List<WebSocketSession> sessionsToSendTo = new ArrayList<>(sessions);
        for(WebSocketSession webSocketSession : sessionsToSendTo) {
            try {
                webSocketSession.sendMessage(new TextMessage("Ping"));
            } catch (IOException e) {
                System.out.println("Something foobar happened with a websockets session when pinging: " + e.getMessage());
                try {
                    webSocketSession.close();
                    sessions.remove(webSocketSession);
                } catch (IOException ex) {
                    System.out.println("Unable to close wssession: " + e.getMessage());
                }
            }
        }
    }

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) {
        for(WebSocketSession webSocketSession : sessions) {
            Map value = new Gson().fromJson(message.getPayload(), Map.class);
            System.out.println("message: " + value.get("name"));
            try {
                webSocketSession.sendMessage(new TextMessage("Hello " + value.get("name") + " !"));
            } catch (IOException | IllegalStateException e) {
                System.out.println("Oopsie happened with a websockets session [ " + session.getId() + " } when handling a message: " + e.getMessage());

                sessions.remove(webSocketSession);
            }
        }
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        //the messages will be broadcasted to all users.
        System.out.println("new session");
        sessions.add(session);
    }
}
