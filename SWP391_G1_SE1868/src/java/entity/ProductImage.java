/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.time.LocalDateTime;

/**
 *
 * @author Đạt
 */
public class ProductImage {
     private int productImageId;
    private Product product;
    private String imageUrl;
    private LocalDateTime createdAt;

    @Override
    public String toString() {
        return "ProductImage{" +
                "productImageId=" + productImageId +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }

    public ProductImage() {
    }

    public ProductImage(int productImageId, Product product, String imageUrl, LocalDateTime createdAt) {
        this.productImageId = productImageId;
        this.product = product;
        this.imageUrl = imageUrl;
        this.createdAt = createdAt;
    }

    public int getProductImageId() {
        return productImageId;
    }

    public void setProductImageId(int productImageId) {
        this.productImageId = productImageId;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    
}
