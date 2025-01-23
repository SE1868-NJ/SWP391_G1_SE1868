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
public class FavoriteDetail {
    private Favorite favorite; // Many-to-One
    private Product product; // Many-to-One
    private LocalDateTime createdAt;

    public FavoriteDetail(Favorite favorite, Product product, LocalDateTime createdAt) {
        this.favorite = favorite;
        this.product = product;
        this.createdAt = createdAt;
    }

    public FavoriteDetail() {
    }

    public Favorite getFavorite() {
        return favorite;
    }

    public void setFavorite(Favorite favorite) {
        this.favorite = favorite;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    
    
}
