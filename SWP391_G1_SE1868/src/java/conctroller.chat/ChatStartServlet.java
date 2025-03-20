package conctroller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.UUID;

/**
 * Servlet xử lý yêu cầu bắt đầu chat
 */
@WebServlet(name = "ChatStartServlet", urlPatterns = {"/chat/start"})
public class ChatStartServlet extends HttpServlet {
    private final Gson gson = new Gson();
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Tạo ID phiên chat mới
            HttpSession session = request.getSession(true);
            String sessionId = session.getId();
            
            // Tạo tin nhắn chào mừng
            String welcomeMessage = "Xin chào! Tôi là trợ lý ảo. Tôi có thể giúp gì cho bạn hôm nay?";
            
            // Tạo phản hồi JSON
            JsonObject jsonResponse = new JsonObject();
            jsonResponse.addProperty("sessionId", sessionId);
            jsonResponse.addProperty("message", welcomeMessage);
            
            // Gửi phản hồi
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(gson.toJson(jsonResponse));
            
        } catch (Exception e) {
            log("Error starting chat: " + e.getMessage(), e);
            sendErrorResponse(response, "Không thể bắt đầu phiên chat mới");
        }
    }
    
    /**
     * Gửi thông báo lỗi về client
     */
    private void sendErrorResponse(HttpServletResponse response, String errorMessage) throws IOException {
        JsonObject jsonResponse = new JsonObject();
        jsonResponse.addProperty("error", errorMessage);
        
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(gson.toJson(jsonResponse));
    }
}