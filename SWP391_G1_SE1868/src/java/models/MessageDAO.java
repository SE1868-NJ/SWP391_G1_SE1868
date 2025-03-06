package models;

import dbcontext.GiangDBcontext;
import entity.Message;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MessageDAO extends GiangDBcontext{
    public void saveMessage(Message message) {
        String sql = "INSERT INTO Messages (CustomerID, ShopID, ReceiverType, MessageText, Status, CreatedAt) VALUES (?, ?, ?, ?, 'Sent', NOW())";
        try (Connection conn =getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, message.getCustomerId());
            stmt.setInt(2, message.getShopId());
            stmt.setString(3, message.getReceiverType());
            stmt.setString(4, message.getMessageText());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Message> getMessages(int customerId, int shopId) {
        List<Message> messages = new ArrayList<>();
        String sql = "SELECT * FROM Messages WHERE CustomerID = ? AND ShopID = ? ORDER BY CreatedAt ASC";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, customerId);
            stmt.setInt(2, shopId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                messages.add(new Message(
                    rs.getInt("MessageID"),
                    rs.getInt("CustomerID"),
                    rs.getInt("ShopID"),
                    rs.getString("ReceiverType"),
                    rs.getString("MessageText"),
                    rs.getString("Status"),
                    rs.getTimestamp("CreatedAt").toLocalDateTime()
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return messages;
    }
}
