/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author Đạt
 */
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class Shop {

    private int shopId;
    private String name;
    private String logo;
    private String location;
    private String owner;
    private LocalDate createdAt;
    private LocalDate updatedAt;
    private List<Product> products;

    // Constructor mặc định
    public Shop() {
    }

    // Constructor có tham số

    public Shop(int shopId, String name, String logo, String location, String owner, LocalDate createdAt, LocalDate updatedAt, List<Product> products) {
        this.shopId = shopId;
        this.name = name;
        this.logo = logo;
        this.location = location;
        this.owner = owner;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.products = products;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
    

    // Getter và Setter
    public int getShopId() {
        return shopId;
    }

    public void setShopId(int shopId) {
        this.shopId = shopId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDate getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDate updatedAt) {
        this.updatedAt = updatedAt;
    }

    // toString() method
    @Override
    public String toString() {
        return "Shop{"
                + "shopID=" + shopId
                + ", name='" + name + '\''
                + ", logo='" + logo + '\''
                + ", location='" + location + '\''
                + ", owner='" + owner + '\''
                + ", createdAt=" + createdAt
                + ", updatedAt=" + updatedAt
                + '}';
    }
}
