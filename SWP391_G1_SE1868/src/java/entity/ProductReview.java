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

public class ProductReview {

    private int reviewId;
    private Product product;
    private Customer customer;
    private int rating;
    private String comment;
    private String ProductReviewsImage;
    private LocalDate createdAt;
    private LocalDate updatedAt;

    @Override
    public String toString() {
        return "ProductReview{"
                + "reviewId=" + reviewId
                + ", rating=" + rating
                + ", comment='" + comment + '\''
                + ", product=" + (product != null ? product.getName() : "null")
                + ", customer=" + (customer != null ? customer.getFullName() : "null")
                + '}';
    }

    public ProductReview() {
    }

    public ProductReview(int reviewId, Product product, Customer customer, int rating, String comment, String ProductReviewsImage, LocalDate createdAt, LocalDate updatedAt) {
        this.reviewId = reviewId;
        this.product = product;
        this.customer = customer;
        this.rating = rating;
        this.comment = comment;
        this.ProductReviewsImage = ProductReviewsImage;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public String getProductReviewsImage() {
        return ProductReviewsImage;
    }

    public void setProductReviewsImage(String ProductReviewsImage) {
        this.ProductReviewsImage = ProductReviewsImage;
    }

    public LocalDate getupdatedAt() {
        return updatedAt;
    }

    public void setupdatedAt(LocalDate updatedAt) {
        this.updatedAt = updatedAt;
    }

    

    public int getReviewId() {
        return reviewId;
    }

    public void setReviewId(int reviewId) {
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

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

}
