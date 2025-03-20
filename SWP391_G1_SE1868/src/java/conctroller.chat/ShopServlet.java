package conctroller;

import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Servlet xử lý yêu cầu lấy danh sách cửa hàng
 */
@WebServlet(name = "ShopServlet", urlPatterns = {"/chat/shops"})
public class ShopServlet extends HttpServlet {
    private final Gson gson = new Gson();
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Chuẩn bị danh sách cửa hàng mẫu
        List<Map<String, String>> shops = new ArrayList<>();
        
        // Cửa hàng 1
        Map<String, String> shop1 = new HashMap<>();
        shop1.put("id", "1");
        shop1.put("name", "Cửa hàng Điện tử");
        shop1.put("image", "https://cdn-icons-png.flaticon.com/128/3659/3659898.png");
        shops.add(shop1);
        
        // Cửa hàng 2
        Map<String, String> shop2 = new HashMap<>();
        shop2.put("id", "2");
        shop2.put("name", "Cửa hàng Thời trang");
        shop2.put("image", "https://cdn-icons-png.flaticon.com/128/2331/2331966.png");
        shops.add(shop2);
        
        // Cửa hàng 3
        Map<String, String> shop3 = new HashMap<>();
        shop3.put("id", "3");
        shop3.put("name", "Siêu thị Thực phẩm");
        shop3.put("image", "https://cdn-icons-png.flaticon.com/128/3724/3724788.png");
        shops.add(shop3);
        
        // Chuyển danh sách thành JSON và gửi về client
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(gson.toJson(shops));
    }
}