package controller.Messages;


import models.MessageDAO;
import entity.Message;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

@WebServlet("/MessageServlet")
public class MessageServlet extends HttpServlet {
        private MessageDAO messageDAO = new MessageDAO();


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String messageText = request.getParameter("message");
        String receiver = request.getParameter("receiver");
        int customerId = (int) request.getSession().getAttribute("customerId");
        int shopId = receiver.equals("AI") ? 0 : Integer.parseInt(receiver); // AI không có shopId

        Message message = new Message(0, customerId, shopId, receiver, messageText, "Sent", java.time.LocalDateTime.now());
        messageDAO.saveMessage(message);

        // Trả lời tự động nếu AI
        if (receiver.equals("AI")) {
            String botReply = "Xin chào! Bạn cần hỗ trợ gì?";
            Message botMessage = new Message(0, customerId, 0, "AI", botReply, "Sent", java.time.LocalDateTime.now());
            messageDAO.saveMessage(botMessage);
        }

        response.setContentType("application/json");
        response.getWriter().write("{\"status\": \"success\"}");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String receiver = request.getParameter("receiver");
        int customerId = (int) request.getSession().getAttribute("customerId");

        List<Message> messages = messageDAO.getMessages(customerId, receiver.equals("AI") ? 0 : Integer.parseInt(receiver));
        JSONArray jsonArray = new JSONArray();
        for (Message msg : messages) {
            jsonArray.put(new JSONObject()
                    .put("sender", msg.getReceiverType())
                    .put("text", msg.getMessageText())
                    .put("time", msg.getCreatedAt().toString()));
        }

        response.setContentType("application/json");
        response.getWriter().write(jsonArray.toString());
    }
}
