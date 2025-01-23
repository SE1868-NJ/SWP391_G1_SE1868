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
    private Integer reviewId;
    private Shipper shipper;
    private Customer customer;
    private Order order;
    private Integer rating;
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

    public ShipperReview(Integer reviewId, Shipper shipper, Customer customer, Order order, Integer rating, String comment, LocalDateTime createdAt) {
        this.reviewId = reviewId;
        this.shipper = shipper;
        this.customer = customer;
        this.order = order;
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
