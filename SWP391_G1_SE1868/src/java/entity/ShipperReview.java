/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.time.LocalDate;

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
    private LocalDate createdAt;
    private LocalDate UpdatedAt;

    public LocalDate getUpdatedAt() {
        return UpdatedAt;
    }

    public void setUpdatedAt(LocalDate UpdatedAt) {
        this.UpdatedAt = UpdatedAt;
    }

    @Override
    public String toString() {
        // Lấy tên khách hàng nếu có trong Order
        String customerFullName = (customer != null && customer.getFullName() != null) ? customer.getFullName() : "Unknown Customer";

        return "ShipperReview{"
                + "reviewId=" + reviewId
                + ", shipper=" + (shipper != null ? shipper.getFullName() : "null")
                + ", customer=" + customerFullName
                + ", rating=" + rating
                + ", comment='" + comment + '\''
                + ", createdAt=" + createdAt
                + ", updatedAt=" + UpdatedAt
                + '}';
    }

    public ShipperReview() {
    }

    public ShipperReview(int reviewId, Shipper shipper, Customer customer, Order order, int rating, String comment, LocalDate createdAt) {
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

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

}
