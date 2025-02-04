/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.time.LocalDateTime;

/**
 *
 * @author DUCDUY2003
 */
public class Orders {

    private int orderID;
    private int customerId;
    private LocalDateTime orderDate;
    private double otalAmount;
    private String status;
    private String shippingAddress;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Integer shipperId;

    public Orders(int orderID, int customerId, LocalDateTime orderDate, double otalAmount, String status, String shippingAddress, LocalDateTime createdAt, LocalDateTime updatedAt, Integer shipperId) {
        this.orderID = orderID;
        this.customerId = customerId;
        this.orderDate = orderDate;
        this.otalAmount = otalAmount;
        this.status = status;
        this.shippingAddress = shippingAddress;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.shipperId = shipperId;
    }

    public int getOrderID() {
        return orderID;
    }

    public int getCustomerId() {
        return customerId;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public double getOtalAmount() {
        return otalAmount;
    }

    public String getStatus() {
        return status;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public Integer getShipperId() {
        return shipperId;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public void setOtalAmount(double otalAmount) {
        this.otalAmount = otalAmount;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public void setShipperId(Integer shipperId) {
        this.shipperId = shipperId;
    }

    @Override
    public String toString() {
        return "Orders{" + "orderID=" + orderID + ", customerId=" + customerId + ", orderDate=" + orderDate + ", otalAmount=" + otalAmount + ", status=" + status + ", shippingAddress=" + shippingAddress + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + ", shipperId=" + shipperId + '}';
    }

}
