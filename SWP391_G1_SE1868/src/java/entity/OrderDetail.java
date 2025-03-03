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

    private int orderDetailId;
    private Order order;
    private Product product;
    private int quantity;
    private double unitPrice;
    private double subTotal;

    @Override
    public String toString() {
        // Lấy totalAmount của Order từ đối tượng order
        double totalAmount = (order != null) ? order.getTotalAmount() : 0.0;

        // Lấy tên sản phẩm từ đối tượng product
        String productName = (product != null) ? product.getName() : "Unknown";

        return "OrderDetail{"
                + "orderDetailId=" + orderDetailId
                + ", quantity=" + quantity
                + ", unitPrice=" + unitPrice
                + ", subTotal=" + subTotal
                + ", totalAmount=" + totalAmount
                + // Hiển thị totalAmount của đơn hàng
                ", product=" + productName
                + // Hiển thị tên sản phẩm
                '}';
    }

    public OrderDetail() {
    }

    public OrderDetail(int orderDetailId, Order order, Product product, int quantity, double unitPrice, double subTotal) {
        this.orderDetailId = orderDetailId;
        this.order = order;
        this.product = product;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.subTotal = subTotal;
    }

    public int getOrderDetailId() {
        return orderDetailId;
    }

    public void setOrderDetailId(int orderDetailId) {
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }

}
