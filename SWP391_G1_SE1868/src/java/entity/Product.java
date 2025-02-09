/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author Đạt
 */
import java.time.LocalDateTime;
import java.util.List;
public class Product {
    private int productId;
      private String name;
      private String description;
      private Double price;
      private int stockQuantity;
      private LocalDateTime createdAt;
      private LocalDateTime updatedAt;

      private Category category;
      private List<ProductReview> reviews;
      private List<ProductImage> images;
      private List<OrderDetail> orderDetails;
      private List<FavoriteDetail> favoriteDetails;
      private List<CartItem> cartItems;

    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", stockQuantity=" + stockQuantity +
                ", category=" + (category != null ? category.getName() : "null") +
                '}';
    }

    public Product() {
    }

    public Product(int productId, String name, String description, Double price, int stockQuantity, LocalDateTime createdAt, LocalDateTime updatedAt, Category category, List<ProductReview> reviews, List<ProductImage> images, List<OrderDetail> orderDetails, List<FavoriteDetail> favoriteDetails, List<CartItem> cartItems) {
        this.productId = productId;
        this.name = name;
        this.description = description;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.category = category;
        this.reviews = reviews;
        this.images = images;
        this.orderDetails = orderDetails;
        this.favoriteDetails = favoriteDetails;
        this.cartItems = cartItems;
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
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

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }
    
}
