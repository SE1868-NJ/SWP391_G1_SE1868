/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author DUCDUY2003
 */
public class Orders {

    String orderID, customerId, orderDate, otalAmount, status, shippingAddress, createdAt, updatedAt, shipperId;

    public Orders() {

    }

    public Orders(String orderID, String customerId, String orderDate, String otalAmount, String status, String shippingAddress, String createdAt, String updatedAt, String shipperId) {
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

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getOtalAmount() {
        return otalAmount;
    }

    public void setOtalAmount(String otalAmount) {
        this.otalAmount = otalAmount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getShipperId() {
        return shipperId;
    }

    public void setShipperId(String shipperId) {
        this.shipperId = shipperId;
    }

    @Override
    public String toString() {
        return "Orders{" + "orderID=" + orderID + ", customerId=" + customerId + ", orderDate=" + orderDate + ", otalAmount=" + otalAmount + ", status=" + status + ", shippingAddress=" + shippingAddress + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + ", shipperId=" + shipperId + '}';
    }

}
