/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

/**
 *
 * @author Giang123
 */


import dbcontext.DBConnection;
import entity.Notification;
import java.sql.*;
import java.util.*;

public class NotificationDAO {

    // Lấy danh sách thông báo của khách hàng với phân loại (all, orderUpdates, promotions)
    public static List<Notification> getNotifications(int customerId, String type) {
        List<Notification> notifications = new ArrayList<>();
        
        String sql = "SELECT NotificationID, Message, Status, CreatedAt " +
                     "FROM Notifications WHERE CustomerID = ? ";

        // Nếu type không phải "all", lọc theo loại thông báo
        if ("orderUpdates".equals(type)) {
            sql += "AND Message LIKE '%Cập Nhật Đơn Hàng%'";
        } else if ("promotions".equals(type)) {
            sql += "AND Message LIKE '%Khuyến Mãi%'";
        }

        sql += "ORDER BY CreatedAt DESC"; // Sắp xếp theo thời gian

        try (Connection conn = DBConnection.getConnection(); 
             PreparedStatement ps = conn.prepareStatement(sql)) {
             
            ps.setInt(1, customerId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Notification notification = new Notification();
                notification.setNotificationId(rs.getInt("NotificationID"));
                notification.setMessage(rs.getString("Message"));
                notification.setStatus(rs.getString("Status"));
                notification.setCreatedAt(rs.getTimestamp("CreatedAt"));
                notifications.add(notification);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return notifications;
    }

    // Gửi thông báo trạng thái đơn hàng
    public static boolean sendOrderStatusNotification(int orderId, int customerId) {
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "SELECT c.Email, o.Status FROM Orders o " +
                         "JOIN Customers c ON o.CustomerID = c.CustomerID " +
                         "WHERE o.OrderID = ? AND o.CustomerID = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, orderId);
            ps.setInt(2, customerId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String email = rs.getString("Email");
                String status = rs.getString("Status");
                
                // Logic gửi email
                sendEmail(email, "Order Status Update", "Your order status is: " + status);
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Gửi thông báo khuyến mãi
    public static boolean sendPromotionNotification(int customerId, String promotionMessage) {
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "SELECT c.Email FROM Customers c WHERE c.CustomerID = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, customerId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String email = rs.getString("Email");
                
                // Logic gửi email
                sendEmail(email, "Promotion", promotionMessage);
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Gửi thông báo khi sản phẩm về kho
    public static boolean sendBackInStockNotification(int productId, int customerId) {
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "SELECT p.Name, c.Email FROM Products p " +
                         "JOIN FavoritesDetails fd ON p.ProductID = fd.ProductID " +
                         "JOIN Customers c ON fd.CustomerID = c.CustomerID " +
                         "WHERE p.ProductID = ? AND c.CustomerID = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, productId);
            ps.setInt(2, customerId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String email = rs.getString("Email");
                String productName = rs.getString("Name");
                
                // Kiểm tra nếu sản phẩm có hàng lại
                sendEmail(email, "Product Back in Stock", "The product " + productName + " is back in stock!");
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private static void sendEmail(String toEmail, String subject, String body) {
        // Logic gửi email (Sử dụng JavaMail hoặc thư viện tương tự)
    }
}
