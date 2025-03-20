package entity;

import java.sql.Timestamp;

public class ChatSession {
    private Long id;
    private Integer customerId;
    private Timestamp startTime;
    private boolean active;

    public ChatSession() {
        this.startTime = new Timestamp(System.currentTimeMillis());
        this.active = true;
    }

    public ChatSession(Integer customerId) {
        this();
        this.customerId = customerId;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
