/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author Giang123
 */
import java.sql.Timestamp;

public class Notification {
    
    // Các thuộc tính của thông báo
    private int notificationId;
    private String message;
    private String status;
    private Timestamp createdAt;

    // Constructor mặc định
    public Notification() {
    }

    // Getter và Setter
    public int getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(int notificationId) {
        this.notificationId = notificationId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "Notification [notificationId=" + notificationId + ", message=" + message + ", status=" + status
                + ", createdAt=" + createdAt + "]";
    }
}

