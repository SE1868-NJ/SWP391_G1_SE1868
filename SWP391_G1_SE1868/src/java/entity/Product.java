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
import java.util.List;

public class Product {

    private int productId;
    private String name;
    private String description;
    private double price;
    private int stockQuantity;
    private LocalDate createdAt;
    private LocalDate updatedAt;
    private Shop shop;
    private Category category;
    private List<ProductReview> reviews;
    private List<ProductImage> images;
    private List<OrderDetail> orderDetails;
    private List<FavoriteDetail> favoriteDetails;
   
    private double avgRating;

    @Override
    public String toString() {
        return "Product{"
                + "productId=" + productId
                + ", name='" + name + '\''
                + ", price=" + price
                 + ", createdAt=" + createdAt
                + ", stockQuantity=" + stockQuantity
                + ", category=" + (category != null ? category.getName() : "null")
                + '}';
    }

    public Product() {
    }

    public Product(int productId, String name, String description, double price, int stockQuantity, LocalDate createdAt, LocalDate updatedAt, Shop shop, Category category, List<ProductImage> images, double avgRating) {
        this.productId = productId;
        this.name = name;
        this.description = description;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.shop = shop;
        this.category = category;
        this.images = images;
        this.avgRating = avgRating;
    }

    

    public Product(int productId, String name, String description, double price, int stockQuantity, LocalDate createdAt, LocalDate updatedAt, Shop shop, Category category, List<ProductReview> reviews, List<ProductImage> images, List<OrderDetail> orderDetails, List<FavoriteDetail> favoriteDetails) {
        this.productId = productId;
        this.name = name;
        this.description = description;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.shop = shop;
        this.category = category;
        this.reviews = reviews;
        this.images = images;
        this.orderDetails = orderDetails;
        this.favoriteDetails = favoriteDetails;
    
    }

    public double getAvgRating() {
        return avgRating;
    }

    public void setAvgRating(double avgRating) {
        this.avgRating = avgRating;
    }

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<ProductReview> getReviews() {
        return reviews;
    }

    public void setReviews(List<ProductReview> reviews) {
        this.reviews = reviews;
    }

    public List<ProductImage> getImages() {
        return images;
    }

    public void setImages(List<ProductImage> images) {
        this.images = images;
    }

    public List<OrderDetail> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }

    public List<FavoriteDetail> getFavoriteDetails() {
        return favoriteDetails;
    }

    public void setFavoriteDetails(List<FavoriteDetail> favoriteDetails) {
        this.favoriteDetails = favoriteDetails;
    }



}
