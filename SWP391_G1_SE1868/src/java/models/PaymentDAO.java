/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;
import dbcontext.DBContext;
import entity.Category;
import entity.Customer;
import entity.Order;
import entity.Payment;
import entity.Product;
import entity.ProductReview;
import java.sql.PreparedStatement;
import java.sql.ResultSet;   // tap ban ghi 
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.SQLException;
/**
 *
 * @author Đạt
 */
public class PaymentDAO extends DBContext{
    
    
    public Payment getPaymentByOrderId(int orderId) {
    String sql = "SELECT * FROM payment WHERE orderId = ?";
    
    try (PreparedStatement stmt = connection.prepareStatement(sql)) {
        stmt.setInt(1, orderId);
        ResultSet rs = stmt.executeQuery();
        
        if (rs.next()) {
            Payment payment = new Payment();
            payment.setPaymentId(rs.getInt("paymentId"));
            payment.setOrder(getOrderById(rs.getInt("orderId"))); // Lấy thông tin Order
            payment.setPaymentDate(rs.getDate("paymentDate").toLocalDate());
            payment.setAmount(rs.getDouble("amount"));
            payment.setPaymentMethod(rs.getString("paymentMethod"));
            payment.setPaymentStatus(rs.getString("paymentStatus"));
        
            
            return payment;
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return null;
}
    
    // hàm lấy orderById private
        private Order getOrderById(int orderId) {
        String sql = "SELECT * FROM orders WHERE orderId = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, orderId);
            ResultSet rs = stmt.executeQuery();

            // khai báo CustomerDAO
            CustomerDAO customerDAO = new CustomerDAO();
            if (rs.next()) {
                Order order = new Order();
                order.setOrderId(rs.getInt("orderId"));
                order.setOrderDate(rs.getDate("orderDate").toLocalDate());
                order.setTotalAmount(rs.getDouble("totalAmount"));
                order.setStatus(rs.getString("status"));
                order.setShippingAddress(rs.getString("shippingAddress"));
                order.setCreatedAt(rs.getDate("createdAt").toLocalDate());
                order.setUpdatedAt(rs.getDate("updatedAt").toLocalDate());
                order.setCustomer(customerDAO.getCustomerById(rs.getInt("CustomerID")));
                return order;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
        
        
        
        public static void main(String[] args) {
        PaymentDAO paymentDAO = new PaymentDAO();
        
        Payment payment = paymentDAO.getPaymentByOrderId(2);
            System.out.println(payment);
    }

}
