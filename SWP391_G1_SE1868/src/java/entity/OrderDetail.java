/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author Đạt
 */

public class OrderDetail {
     private Integer orderDetailId;
    private Order order;
    private Product product;
    private Integer quantity;
    private Double unitPrice;
    private Double subTotal;

    @Override
    public String toString() {
        return "OrderDetail{" +
                "orderDetailId=" + orderDetailId +
                ", quantity=" + quantity +
                ", unitPrice=" + unitPrice +
                ", subTotal=" + subTotal +
                ", product=" + (product != null ? product.getName() : "null") +
                '}';
    }

    public OrderDetail() {
    }

    public OrderDetail(Integer orderDetailId, Order order, Product product, Integer quantity, Double unitPrice, Double subTotal) {
        this.orderDetailId = orderDetailId;
        this.order = order;
        this.product = product;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.subTotal = subTotal;
    }

    public Integer getOrderDetailId() {
        return orderDetailId;
    }

    public void setOrderDetailId(Integer orderDetailId) {
        this.orderDetailId = orderDetailId;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(Double subTotal) {
        this.subTotal = subTotal;
    }
    
}
