/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import dbcontext.DBContext;
import entity.Category;
import entity.Customer;
import entity.Order;
import entity.Product;
import entity.ProductReview;
import entity.Shipper;
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
public class ShipperDAO extends DBContext{
    
    public Shipper getShipperById(int shipperId) {
    String sql = "SELECT * FROM shipper WHERE shipperId = ?";
    try (PreparedStatement stmt = connection.prepareStatement(sql)) {
        stmt.setInt(1, shipperId);
        ResultSet rs = stmt.executeQuery();
        
        if (rs.next()) {
            Shipper shipper = new Shipper();
            
            shipper.setShipperId(rs.getInt("shipperId"));
            shipper.setFullName(rs.getString("fullName"));
            shipper.setEmail(rs.getString("email"));
            shipper.setPhoneNumber(rs.getString("phoneNumber"));
            shipper.setStatus(rs.getString("status"));
            shipper.setCreatedAt(rs.getDate("createdAt").toLocalDate());
            shipper.setUpdatedAt(rs.getDate("updatedAt").toLocalDate());
            
            // Xử lí lấy danh sách các đơn hàng (Orders) của Shipper  ở đây
            
            shipper.setOrders(getOrdersByShipperId(shipperId));
            
            return shipper;
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return null;
}
    
    // lấy list oder theo shipperId
       private List<Order> getOrdersByShipperId(int shipperId) {
        List<Order> orders = new ArrayList<>();
        String sql = "SELECT * FROM Orders WHERE shipperId = ?"; // Truy vấn đơn hàng theo shipperId

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, shipperId);  // Set shipperId vào câu lệnh truy vấn
            
            // Khao báo Customer Dao để lấy đối tương custoerm theo ID
            
            CustomerDAO  customerDAO = new CustomerDAO();
            
            // Khao báo OrderDetailDAO  để lấy list đối tương OrderDetail theo ID của orderID
            
            OrderDetailDAO orderDetailDAO =  new OrderDetailDAO();
            
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                // Tạo đối tượng Order từ kết quả truy vấn
                Order order = new Order();
                order.setOrderId(rs.getInt("orderId"));
//                order.setCustomer(customerDAO.getCustomerById(rs.getInt("customerId"))); 
                order.setOrderDate(rs.getDate("orderDate").toLocalDate());
                order.setTotalAmount(rs.getDouble("totalAmount"));
                order.setStatus(rs.getString("status"));
                order.setShippingAddress(rs.getString("shippingAddress"));
                order.setCreatedAt(rs.getDate("createdAt").toLocalDate());
                order.setUpdatedAt(rs.getDate("updatedAt").toLocalDate());
                    
                // set các orderDetail trương iungws với mối OdderId
                order.setOrderDetails(orderDetailDAO.getOrderDetailsByOrderId(rs.getInt("orderId")));
                
                orders.add(order); // Thêm Order vào danh sách
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return orders; // Trả về danh sách đơn hàng
    }
    
    public static void main(String[] args) {
        ShipperDAO shipperDAO = new ShipperDAO();
        
     Shipper shipper =  shipperDAO.getShipperById(2);
        
        System.out.println(shipper);
    }

}
