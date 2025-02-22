/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import dbcontext.DBContext;
import entity.Category;
import entity.Customer;
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
    String sql = "SELECT * FROM shippers WHERE shipperId = ?";
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
           
            
            return shipper;
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return null;
}

}
