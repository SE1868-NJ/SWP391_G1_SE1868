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
public class ShipperReview {
    private int reviewId;
    private Shipper shipper;
    private Customer customer;
    private Order order;
    private int rating;
    private String comment;
    private LocalDateTime createdAt;

    @Override
    public String toString() {
        return "ShipperReview{" +
                "reviewId=" + reviewId +
                ", rating=" + rating +
                ", comment='" + comment + '\'' +
                ", shipper=" + (shipper != null ? shipper.getFullName() : "null") +
                ", customer=" + (customer != null ? customer.getFullName() : "null") +
                '}';
    }

    public ShipperReview() {
    }

    public ShipperReview(int reviewId, Shipper shipper, Customer customer, Order order, int rating, String comment, LocalDateTime createdAt) {
        this.reviewId = reviewId;
        this.shipper = shipper;
        this.customer = customer;
        this.order = order;
        this.rating = rating;
        this.comment = comment;
        this.createdAt = createdAt;
    }

    public int getReviewId() {
        return reviewId;
    }

    public void setReviewId(int reviewId) {
        this.reviewId = reviewId;
    }

    public Shipper getShipper() {
        return shipper;
    }

    public void setShipper(Shipper shipper) {
        this.shipper = shipper;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    

}
