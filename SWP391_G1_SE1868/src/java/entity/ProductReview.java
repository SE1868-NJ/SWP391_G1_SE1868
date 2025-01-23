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
public class ProductReview {
   private Integer reviewId;
    private Product product;
    private Customer customer;
    private Integer rating;
    private String comment;
    private LocalDateTime createdAt;

    @Override
    public String toString() {
        return "ProductReview{" +
                "reviewId=" + reviewId +
                ", rating=" + rating +
                ", comment='" + comment + '\'' +
                ", product=" + (product != null ? product.getName() : "null") +
                ", customer=" + (customer != null ? customer.getFullName() : "null") +
                '}';
    }

    public ProductReview() {
    }

    public ProductReview(Integer reviewId, Product product, Customer customer, Integer rating, String comment, LocalDateTime createdAt) {
        this.reviewId = reviewId;
        this.product = product;
        this.customer = customer;
        this.rating = rating;
        this.comment = comment;
        this.createdAt = createdAt;
    }

    public Integer getReviewId() {
        return reviewId;
    }

    public void setReviewId(Integer reviewId) {
        this.reviewId = reviewId;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    

}
