/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;
import dbcontext.DBContext;
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
/**
 *
 * @author Đạt
 */
public class OrderDetailDAO extends DBContext{
    
//    public List<OrderDetail> getOrderDetailsByOrderId(int orderId) {
//        List<OrderDetail> orderDetails = new ArrayList<>();
//        String sql = "SELECT * FROM order_details WHERE orderId = ?";
//
//        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
//            stmt.setInt(1, orderId);
//            ResultSet rs = stmt.executeQuery();
//
//            while (rs.next()) {
//                OrderDetail orderDetail = new OrderDetail();
//                orderDetail.setOrderDetailId(rs.getInt("orderDetailId"));
//                orderDetail.setOrder(getOrderById(rs.getInt("orderId"))); // Lấy thông tin Order
//                orderDetail.setProduct(getProductById(rs.getInt("productId"))); // Lấy thông tin Product
//                orderDetail.setQuantity(rs.getInt("quantity"));
//                orderDetail.setUnitPrice(rs.getDouble("unitPrice"));
//                orderDetail.setSubTotal(rs.getDouble("subTotal"));
//
//                orderDetails.add(orderDetail);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return orderDetails;
//    }

}
