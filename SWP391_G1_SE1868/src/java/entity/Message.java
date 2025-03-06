package entity;

import java.time.LocalDateTime;

public class Message {
    private int messageId;
    private int customerId;
    private int shopId;
    private String receiverType;
    private String messageText;
    private String status;
    private LocalDateTime createdAt;

    public Message() {}

    public Message(int messageId, int customerId, int shopId, String receiverType, String messageText, String status, LocalDateTime createdAt) {
        this.messageId = messageId;
        this.customerId = customerId;
        this.shopId = shopId;
        this.receiverType = receiverType;
        this.messageText = messageText;
        this.status = status;
        this.createdAt = createdAt;
    }

    public int getMessageId() { return messageId; }
    public void setMessageId(int messageId) { this.messageId = messageId; }

    public int getCustomerId() { return customerId; }
    public void setCustomerId(int customerId) { this.customerId = customerId; }

    public int getShopId() { return shopId; }
    public void setShopId(int shopId) { this.shopId = shopId; }

    public String getReceiverType() { return receiverType; }
    public void setReceiverType(String receiverType) { this.receiverType = receiverType; }

    public String getMessageText() { return messageText; }
    public void setMessageText(String messageText) { this.messageText = messageText; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
