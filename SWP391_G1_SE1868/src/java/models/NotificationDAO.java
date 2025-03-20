package models;

import static dbcontext.GiangDBcontext.getConnection;
import entity.Notification;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NotificationDAO {
    

    public List<Notification> getAllNotifications() {
        List<Notification> notifications = new ArrayList<>();
        String query = "SELECT * FROM notifications";

        try (Connection conn = getConnection();
         PreparedStatement stmt = conn.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                Notification notification = new Notification();
                notification.setNotificationID(rs.getInt("NotificationID"));
                notification.setCustomerID(rs.getInt("CustomerID"));
                notification.setMessage(rs.getString("Message"));
                notification.setStatus(rs.getString("Status"));
                notification.setCreatedAt(rs.getTimestamp("CreatedAt"));
                notification.setImageURL(rs.getString("ImageURL"));
                notifications.add(notification);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return notifications;
    }

    public void updateNotificationStatus(int notificationID, String status) {
        String query = "UPDATE notifications SET Status = ? WHERE NotificationID = ?";

         try (Connection conn = getConnection();
         PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, status);
            stmt.setInt(2, notificationID);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    // Method to get notifications for a specific customer
    public List<Notification> getNotificationsByCustomerId(int customerId) {
        List<Notification> notifications = new ArrayList<>();
        String query = "SELECT * FROM notifications WHERE CustomerID = ?";

        try (Connection conn = getConnection();
         PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, customerId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Notification notification = new Notification();
                notification.setNotificationID(rs.getInt("NotificationID"));
                notification.setCustomerID(rs.getInt("CustomerID"));
                notification.setMessage(rs.getString("Message"));
                notification.setStatus(rs.getString("Status"));
                notification.setCreatedAt(rs.getTimestamp("CreatedAt"));
                notification.setImageURL(rs.getString("ImageURL"));
                notifications.add(notification);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return notifications;
    }
}
