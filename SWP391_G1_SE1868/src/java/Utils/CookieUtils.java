/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utils;

import com.google.gson.Gson;
import entity.Customer;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 *
 * @author Đạt
 */
public class CookieUtils {

    // Thêm đối tượng vào cookie, sử dụng Gson để chuyển đối tượng thành JSON
    public static void addObjectToCookie(HttpServletResponse response, String name, Object object, int maxAge) {
        try {
            // Sử dụng Gson để chuyển đối tượng thành JSON
            Gson gson = new Gson();
            String json = gson.toJson(object);

            // Mã hóa giá trị JSON để tránh ký tự đặc biệt
            json = URLEncoder.encode(json, "UTF-8");

            // Tạo cookie mới và thiết lập giá trị là chuỗi JSON
            Cookie cookie = new Cookie(name, json);
            cookie.setMaxAge(maxAge); // Thiết lập thời gian sống của cookie (tính bằng giây)
            cookie.setPath("/"); // Đảm bảo cookie có thể được truy cập trên toàn bộ ứng dụng

            // Thêm cookie vào response
            response.addCookie(cookie);
        } catch (Exception e) {
            e.printStackTrace();  // Log lỗi nếu có
        }
    }

    public static void deleteCookie(HttpServletResponse response, String name) {
        try {
            // Tạo một cookie với cùng tên và giá trị trống
            Cookie cookie = new Cookie(name, "");
            cookie.setMaxAge(0);  // Thiết lập thời gian sống của cookie về 0, yêu cầu xóa cookie
            cookie.setPath("/");  // Đảm bảo cookie có thể được truy cập trên toàn bộ ứng dụng

            // Thêm cookie vào response để yêu cầu trình duyệt xóa cookie
            response.addCookie(cookie);
        } catch (Exception e) {
            e.printStackTrace();  // Log lỗi nếu có
        }

    }

    public static Customer getCustomerFromCookie(HttpServletRequest request) {
        try {
            // Lấy tất cả các cookie
            Cookie[] cookies = request.getCookies();

            if (cookies != null) {
                // Duyệt qua tất cả cookie
                for (Cookie cookie : cookies) {
                    // Kiểm tra xem cookie có tên "user" không
                    if (cookie.getName().equals("user")) {
                        // Giải mã giá trị cookie
                        String json = URLDecoder.decode(cookie.getValue(), "UTF-8");

                        // Dùng Gson để chuyển chuỗi JSON thành đối tượng Customer
                        Gson gson = new Gson();
                        return gson.fromJson(json, Customer.class);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();  // Log lỗi nếu có
        }
        return null;  // Trả về null nếu không tìm thấy cookie hoặc có lỗi
    }
}
