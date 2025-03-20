package conctroller;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.*;
import org.json.JSONObject;
@WebServlet(name = "ChatServlet", urlPatterns = {"/chat"})

public class ChatServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Thiết lập encoding
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=UTF-8");

        // Lấy tin nhắn từ người dùng
        String userMessage = request.getParameter("message");

        // Tạo đối tượng JSON để gửi phản hồi
        JSONObject jsonResponse = new JSONObject();
        
        if (userMessage == null || userMessage.trim().isEmpty()) {
            jsonResponse.put("error", "Tin nhắn không hợp lệ.");
            response.getWriter().write(jsonResponse.toString());
            return;
        }

        // Xử lý tin nhắn người dùng
        if (userMessage.toLowerCase().contains("shop")) {
            // Giả sử khi người dùng nói "shop", hệ thống sẽ trả lời với các cửa hàng có sẵn
            jsonResponse.put("response", "Dưới đây là các cửa hàng bạn có thể trò chuyện:");
            jsonResponse.put("shops", getShops());
        } else {
            // Nếu không phải "shop", trả lời mặc định từ bot
            jsonResponse.put("response", "Xin chào! Tôi là trợ lý ảo. Bạn cần tôi giúp gì?");
        }

        // Gửi phản hồi về phía client
        response.getWriter().write(jsonResponse.toString());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Khi GET, trả về danh sách các cửa hàng
        JSONObject jsonResponse = new JSONObject();
        jsonResponse.put("shops", getShops());
        response.setContentType("application/json");
        response.getWriter().write(jsonResponse.toString());
    }

    // Hàm giả lập lấy danh sách cửa hàng
    private JSONObject getShops() {
        JSONObject shops = new JSONObject();
        // Thêm cửa hàng vào danh sách
        shops.put("shop1", "Cửa hàng số 1");
        shops.put("shop2", "Cửa hàng số 2");
        shops.put("shop3", "Cửa hàng số 3");
        return shops;
    }
}
