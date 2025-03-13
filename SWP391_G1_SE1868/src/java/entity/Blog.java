package entity;

public class Blog {
    private int id;
    private String name;
    private String description;
    private int customerId;
    private String imageUrl;

    public Blog(int id, String name, String description, int customerId, String imageUrl) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.customerId = customerId;
        this.imageUrl = imageUrl;
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
}
