package models;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import entity.Message;
import static dbcontext.GiangDBcontext.getConnection;

public class MessageDAO {

    // Lưu tin nhắn vào cơ sở dữ liệu
    public boolean saveMessage(int conversationId, String senderType, int senderId, String content) {
        String sql = "INSERT INTO messages (ConversationID, SenderType, SenderID, Content) VALUES (?, ?, ?, ?)";

        try (Connection conn = getConnection(); 
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, conversationId);
            stmt.setString(2, senderType);
            stmt.setInt(3, senderId);
            stmt.setString(4, content);

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0; // Trả về true nếu lưu thành công
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Lỗi khi lưu
        }
    }

    // Lấy tin nhắn của một cuộc trò chuyện
    public List<Message> getMessagesByConversationId(int conversationId) {
        List<Message> messages = new ArrayList<>();
        String sql = "SELECT * FROM messages WHERE ConversationID = ? ORDER BY CreatedAt ASC";

        try (Connection conn = getConnection(); 
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, conversationId);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Message message = new Message(
                    rs.getInt("MessageID"),
                    rs.getInt("ConversationID"),
                    rs.getString("SenderType"),
                    rs.getInt("SenderID"),
                    rs.getString("Content"),
                    rs.getInt("IsRead"),
                    rs.getTimestamp("CreatedAt")
                );
                messages.add(message);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return messages;
    }
}
