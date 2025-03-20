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
import java.util.HashMap;
import java.util.Map;

/**
 * Servlet xử lý yêu cầu bắt đầu chat với cửa hàng
 */
@WebServlet(name = "ChatShopStartServlet", urlPatterns = {"/chat/start-shop"})
public class ChatShopStartServlet extends HttpServlet {
    private final Gson gson = new Gson();
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Lấy ID cửa hàng từ yêu cầu
        String shopId = request.getParameter("shopId");
        
        // Kiểm tra ID cửa hàng
        if (shopId == null || shopId.trim().isEmpty()) {
            sendErrorResponse(response, "ID cửa hàng không được để trống");
            return;
        }
        
        try {
            // Danh sách tên cửa hàng
            Map<String, String> shopNames = new HashMap<>();
            shopNames.put("1", "Cửa hàng Điện tử");
            shopNames.put("2", "Cửa hàng Thời trang");
            shopNames.put("3", "Siêu thị Thực phẩm");
            
            // Lấy tên cửa hàng
            String shopName = shopNames.getOrDefault(shopId, "cửa hàng");
            
            // Tạo tin nhắn chào mừng
            String welcomeMessage = "Chào mừng bạn đến với " + shopName + "! Chúng tôi có thể giúp gì cho bạn hôm nay?";
            
            // Lấy ID phiên
            HttpSession session = request.getSession(true);
            String sessionId = session.getId() + "_shop_" + shopId;
            
            // Tạo phản hồi JSON
            JsonObject jsonResponse = new JsonObject();
            jsonResponse.addProperty("sessionId", sessionId);
            jsonResponse.addProperty("message", welcomeMessage);
            
            // Gửi phản hồi
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(gson.toJson(jsonResponse));
            
        } catch (Exception e) {
            log("Error starting shop chat: " + e.getMessage(), e);
            sendErrorResponse(response, "Không thể bắt đầu phiên chat với cửa hàng");
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