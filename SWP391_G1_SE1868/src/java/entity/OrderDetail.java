/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.util.Date;

/**
 *
 * @author DUCDUY2003
 */
public class OrderDetail {

   private int orderDetailID;
    private int orderID;
    private int productID;
    private int categoryID;
    private String categoryName;
    private String productName;
    private String productDescription;
    private String categoryDescription;
    private Date orderDate;
    private double price;
    private double totalAmount;
    private String status;
    private String shippingAddress;

    public OrderDetail(int orderDetailID, int orderID, int productID, int categoryID, String categoryName, String productName, String productDescription, String categoryDescription, Date orderDate, double price, double totalAmount, String status, String shippingAddress) {
        this.orderDetailID = orderDetailID;
        this.orderID = orderID;
        this.productID = productID;
        this.categoryID = categoryID;
        this.categoryName = categoryName;
        this.productName = productName;
        this.productDescription = productDescription;
        this.categoryDescription = categoryDescription;
        this.orderDate = orderDate;
        this.price = price;
        this.totalAmount = totalAmount;
        this.status = status;
        this.shippingAddress = shippingAddress;
    }

    public int getOrderDetailID() {
        return orderDetailID;
    }

    public void setOrderDetailID(int orderDetailID) {
        this.orderDetailID = orderDetailID;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public String getCategoryDescription() {
        return categoryDescription;
    }

    public void setCategoryDescription(String categoryDescription) {
        this.categoryDescription = categoryDescription;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
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

    @Override
    public String toString() {
        return "OrderDetail{" + "orderDetailID=" + orderDetailID + ", orderID=" + orderID + ", productID=" + productID + ", categoryID=" + categoryID + ", categoryName=" + categoryName + ", productName=" + productName + ", productDescription=" + productDescription + ", categoryDescription=" + categoryDescription + ", orderDate=" + orderDate + ", price=" + price + ", totalAmount=" + totalAmount + ", status=" + status + ", shippingAddress=" + shippingAddress + '}';
    }

 
    
    
}
