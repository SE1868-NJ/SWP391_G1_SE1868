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

public class Shipper {

    private int shipperId;
    private String fullName;
    private String email;
    private String phoneNumber;
    private String status;
    private LocalDate createdAt;
    private LocalDate updatedAt;

    private List<Order> orders;

    @Override
    public String toString() {
        StringBuilder ordersInfo = new StringBuilder();

        // Kiểm tra xem danh sách đơn hàng có rỗng hay không
        if (orders != null && !orders.isEmpty()) {
            for (Order order : orders) {
                ordersInfo.append("\n    Order ID: ").append(order.getOrderId())
                        .append(", Order Date: ").append(order.getOrderDate())
                        .append(", Total Amount: ").append(order.getTotalAmount());

                // Thêm thông tin chi tiết đơn hàng nếu có
                if (order.getOrderDetails() != null && !order.getOrderDetails().isEmpty()) {
                    ordersInfo.append("\n     Order Details:");
                    for (OrderDetail detail : order.getOrderDetails()) {
                        ordersInfo.append("\n  - Product: ").append(detail.getProduct().getName())
                                .append(", Quantity: ").append(detail.getQuantity())
                                .append(", Unit Price: ").append(detail.getUnitPrice());
                    }
                } else {
                    ordersInfo.append("\n     No Order Details.");
                }
            }
        } else {
            ordersInfo.append("\n    No Orders.");
        }

        // Trả về chuỗi thông tin của Shipper
        return "Shipper{"
                + "shipperId=" + shipperId
                + ", fullName='" + fullName + '\''
                + ", email='" + email + '\''
                + ", phoneNumber='" + phoneNumber + '\''
                + ", status='" + status + '\''
                + ", createdAt=" + createdAt
                + ", updatedAt=" + updatedAt
                + ", orders=" + ordersInfo.toString()
                + "\n}";
    }

    public Shipper(int shipperId, String fullName, String email, String phoneNumber, String status, LocalDate createdAt, LocalDate updatedAt, List<Order> orders) {
        this.shipperId = shipperId;
        this.fullName = fullName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.orders = orders;
    }

    public Shipper() {
    }

    public int getShipperId() {
        return shipperId;
    }

    public void setShipperId(int shipperId) {
        this.shipperId = shipperId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDate getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDate updatedAt) {
        this.updatedAt = updatedAt;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

}
