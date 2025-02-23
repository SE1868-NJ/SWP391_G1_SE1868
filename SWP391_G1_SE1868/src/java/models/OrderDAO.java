/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import dbcontext.DBContext;
import entity.Cart;
import entity.Category;
import entity.Customer;
import entity.Order;
import entity.OrderDetail;
import entity.Product;
import entity.ProductReview;
import java.sql.PreparedStatement;
import java.sql.ResultSet;   // tap ban ghi 
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.SQLException;
import java.sql.Date;

/**
 *
 * @author Đạt
 */
public class OrderDAO extends DBContext {

    public boolean updateOrder(Order order) {
        String sql = "UPDATE Orders SET customerId = ?, orderDate = ?, totalAmount = ?, status = ?, shippingAddress = ?, updatedAt = ? WHERE orderId = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            // Cập nhật thông tin của order
            stmt.setInt(1, order.getCustomer().getCustomerId()); // Giả sử có getter cho customerId
            stmt.setDate(2, Date.valueOf(order.getOrderDate()));
            stmt.setDouble(3, order.getTotalAmount());
            stmt.setString(4, order.getStatus());
            stmt.setString(5, order.getShippingAddress());
            stmt.setDate(6, Date.valueOf(order.getUpdatedAt()));
            stmt.setInt(7, order.getOrderId());

            // Thực thi câu lệnh UPDATE
            int rowsUpdated = stmt.executeUpdate();

            // Nếu có ít nhất một dòng bị ảnh hưởng, trả về true
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Nếu có lỗi xảy ra, trả về false
        }
    }

    // get byId
    public Order getOrderById(int orderId) {

        String sql = "SELECT * FROM Orders WHERE orderId = ?"; // Truy vấn đơn hàng theo orderId

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, orderId);  // Thiết lập orderId cho câu lệnh truy vấn

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    // Tạo đối tượng Order từ kết quả truy vấn
                    Order order = new Order();
                    order.setOrderId(rs.getInt("orderId"));
                    order.setOrderDate(rs.getDate("orderDate").toLocalDate());
                    order.setTotalAmount(rs.getDouble("totalAmount"));
                    order.setStatus(rs.getString("status"));
                    order.setShippingAddress(rs.getString("shippingAddress"));
                    order.setCreatedAt(rs.getDate("createdAt").toLocalDate());
                    order.setUpdatedAt(rs.getDate("updatedAt").toLocalDate());
       
                    return order;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    // thêm đơn hàng
    public boolean addOrder(Order order) {
        String sql = "INSERT INTO Orders (customerId, orderDate, totalAmount, status, shippingAddress, createdAt, updatedAt) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            // Thêm thông tin của order vào câu lệnh SQL
            stmt.setInt(1, order.getCustomer().getCustomerId()); 
            stmt.setDate(2, Date.valueOf(order.getOrderDate()));  
            stmt.setDouble(3, order.getTotalAmount());
            stmt.setString(4, order.getStatus());
            stmt.setString(5, order.getShippingAddress());
            stmt.setDate(6, Date.valueOf(order.getCreatedAt())); 
            stmt.setDate(7, Date.valueOf(order.getUpdatedAt())); 

            // Thực thi câu lệnh INSERT
            int rowsInserted = stmt.executeUpdate();

            // Nếu có ít nhất một dòng bị ảnh hưởng, trả về true
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Nếu có lỗi xảy ra, trả về false
        }
    }

    // Xóa tất cả CartItems có cartId tương ứng
    public boolean deleteCartBycustomerID(int customerId) {
        String sql = "DELETE FROM Carts WHERE customerId = ?"; // Xóa tất cả CartItems có cartId tương ứng

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, customerId);  // Thiết lập cartId cho câu lệnh truy vấn

            // Thực thi câu lệnh DELETE
            int rowsDeleted = stmt.executeUpdate();

            // Nếu có ít nhất một dòng bị xóa, trả về true
            return rowsDeleted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Nếu có lỗi xảy ra, trả về false
        }
    }


    public static void main(String[] args) {
        OrderDAO orderDAO = new OrderDAO();

       
    }
}
