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
import java.time.LocalDateTime;
import java.util.List;
public class Favorite {
   private int favoriteId;
    private Customer customer;
    private String name;
    private LocalDate createdAt;

    private List<FavoriteDetail> favoriteDetails;

    @Override
    public String toString() {
        return "Favorite{" +
                "favoriteId=" + favoriteId +
                ", name='" + name + '\'' +
                ", customer=" + (customer != null ? customer.getFullName() : "null") +
                '}';
    }

    public Favorite() {
    }

    public Favorite(int favoriteId, Customer customer, String name, LocalDate createdAt, List<FavoriteDetail> favoriteDetails) {
        this.favoriteId = favoriteId;
        this.customer = customer;
        this.name = name;
        this.createdAt = createdAt;
        this.favoriteDetails = favoriteDetails;
    }

    public int getFavoriteId() {
        return favoriteId;
    }

    public void setFavoriteId(int favoriteId) {
        this.favoriteId = favoriteId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public List<FavoriteDetail> getFavoriteDetails() {
        return favoriteDetails;
    }

    public void setFavoriteDetails(List<FavoriteDetail> favoriteDetails) {
        this.favoriteDetails = favoriteDetails;
    }
    
}
