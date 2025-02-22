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

public class Payment {

    private int paymentId;
    private Order order;
    private LocalDate paymentDate;
    private double amount;
    private String paymentMethod;
    private String paymentStatus;

    @Override
    public String toString() {
        // Lấy fullName của khách hàng từ order
        String customerFullName = (order != null && order.getCustomer() != null) ? order.getCustomer().getFullName() : "Unknown";

        return "Payment{"
                + "paymentId=" + paymentId
                + ", paymentDate=" + paymentDate
                + ", amount=" + amount
                + ", paymentMethod='" + paymentMethod + '\''
                + ", paymentStatus='" + paymentStatus + '\''
                + ", customerFullName='" + customerFullName + '\''
                + // Thêm fullName vào chuỗi trả về
                '}';
    }

    public Payment() {
    }

    public Payment(int paymentId, Order order, LocalDate paymentDate, double amount, String paymentMethod, String paymentStatus) {
        this.paymentId = paymentId;
        this.order = order;
        this.paymentDate = paymentDate;
        this.amount = amount;
        this.paymentMethod = paymentMethod;
        this.paymentStatus = paymentStatus;
    }

    public int getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

}
