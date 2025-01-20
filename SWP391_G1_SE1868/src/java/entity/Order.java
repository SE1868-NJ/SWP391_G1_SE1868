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
public class Order {
   private Integer orderId;
    private Customer customer;
    private LocalDateTime orderDate;
    private Double totalAmount;
    private String status;
    private String shippingAddress;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private Shipper shipper;
    private List<OrderDetail> orderDetails;
    private Payment payment;

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", orderDate=" + orderDate +
                ", totalAmount=" + totalAmount +
                ", status='" + status + '\'' +
                ", customer=" + (customer != null ? customer.getFullName() : "null") +
                ", shipper=" + (shipper != null ? shipper.getFullName() : "null") +
                '}';
    }
}
