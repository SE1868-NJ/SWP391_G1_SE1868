
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author Giang123
 */

import java.sql.Timestamp;

public class FavoriteDetail {
    private int favoriteID;
    private int productID;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private Product product;
    
    
    
    public FavoriteDetail(int favoriteID, int productID, Timestamp createdAt, Timestamp updatedAt) {
        this.favoriteID = favoriteID;
        this.productID = productID;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    // Getters and Setters
    public int getFavoriteID() {
        return favoriteID;
    }

    public void setFavoriteID(int favoriteID) {
        this.favoriteID = favoriteID;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }
    
    public Product getProduct() {
        return product;
    }
    
    public void setProduct(Product product) {
        this.product = product;
    }
}