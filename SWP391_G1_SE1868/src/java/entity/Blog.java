package entity;

import java.time.LocalDate;
import java.util.Date;

public class Blog {
    private int id;
    private String name;
    private String description;
    private int customerId;
    private String imageUrl;
    private LocalDate createdDate;

    public Blog(int id, String name, String description, int customerId, String imageUrl, LocalDate createdDate) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.customerId = customerId;
        this.imageUrl = imageUrl;
        this.createdDate = createdDate;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public int getCustomerId() { return customerId; }
    public void setCustomerId(int customerId) { this.customerId = customerId; }

    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    // Getter phụ để dùng trong JSP
    public Date getCreatedDateAsUtilDate() {
        return createdDate != null ? java.sql.Date.valueOf(createdDate) : null;
    }
}