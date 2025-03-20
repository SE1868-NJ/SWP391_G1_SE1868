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

/**
 * Servlet xử lý yêu cầu kết thúc phiên chat
 */
@WebServlet(name = "ChatEndServlet", urlPatterns = {"/chat/end"})
public class ChatEndServlet extends HttpServlet {
    private final Gson gson = new Gson();
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Trong ứng dụng thực tế, có thể cần làm sạch tài nguyên hoặc lưu thông tin phiên chat
            
            // Tạo phản hồi JSON
            JsonObject jsonResponse = new JsonObject();
            jsonResponse.addProperty("status", "success");
            
            // Gửi phản hồi
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(gson.toJson(jsonResponse));
            
        } catch (Exception e) {
            log("Error ending chat: " + e.getMessage(), e);
            
            // Tạo phản hồi lỗi
            JsonObject jsonResponse = new JsonObject();
            jsonResponse.addProperty("status", "error");
            jsonResponse.addProperty("message", "Không thể kết thúc phiên chat");
            
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(gson.toJson(jsonResponse));
        }
    }
}