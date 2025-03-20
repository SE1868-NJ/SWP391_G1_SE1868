package entity;
import java.sql.*;

public class Notification {
    private int notificationID;
    private int customerID;
    private String message;
    private String status;
    private Timestamp createdAt;
    private String imageURL;

    
    
    
    // Getters and Setters
    public int getNotificationID() {
        return notificationID;
    }

    public void setNotificationID(int notificationID) {
        this.notificationID = notificationID;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
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

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }
    @Override
public String toString() {
    return "Notification{" +
           "notificationID=" + notificationID +
           ", customerID=" + customerID +
           ", message='" + message + '\'' +
           ", status='" + status + '\'' +
           ", createdAt=" + createdAt +
           ", imageURL='" + imageURL + '\'' +
           '}';
}

}
