package entity;

import java.util.Date;

public class Message {
    private int messageId;
    private int conversationId;
    private String senderType; // "Customer" or "Shop"
    private int senderId;
    private String content;
    private int isRead;
    private Date createdAt;

    // Constructor, getters, and setters
    public Message(int messageId, int conversationId, String senderType, int senderId, String content, int isRead, Date createdAt) {
        this.messageId = messageId;
        this.conversationId = conversationId;
        this.senderType = senderType;
        this.senderId = senderId;
        this.content = content;
        this.isRead = isRead;
        this.createdAt = createdAt;
    }

    // Getters and Setters
    public int getMessageId() { return messageId; }
    public void setMessageId(int messageId) { this.messageId = messageId; }

    public int getConversationId() { return conversationId; }
    public void setConversationId(int conversationId) { this.conversationId = conversationId; }

    public String getSenderType() { return senderType; }
    public void setSenderType(String senderType) { this.senderType = senderType; }

    public int getSenderId() { return senderId; }
    public void setSenderId(int senderId) { this.senderId = senderId; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public int getIsRead() { return isRead; }
    public void setIsRead(int isRead) { this.isRead = isRead; }

    public Date getCreatedAt() { return createdAt; }
    public void setCreatedAt(Date createdAt) { this.createdAt = createdAt; }
}
