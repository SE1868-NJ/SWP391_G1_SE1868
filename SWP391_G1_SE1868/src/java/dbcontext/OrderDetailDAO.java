/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dbcontext;

import dbcontext.DBContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import entity.OrderDetail;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

/**
 *
 * @author DUCDUY2003
 */
public class OrderDetailDAO {

    PreparedStatement stm;
    ResultSet rs;
    Connection conn;
    private Date orderDate;

    public OrderDetailDAO() {
        try {
            conn = new DBContext().getConnection();
            if (conn == null) {
                System.out.println("Connection is null. Please check DBContext.");
            }
        } catch (Exception e) {
            System.out.println("OrderDAO connection error: " + e.getMessage());
        }
    }
//SELECT OrderDetail ALL

    public List<OrderDetail> getAllOrderD() {
        List<OrderDetail> list = new ArrayList<>();
        String sql = "SELECT orderdetails.OrderDetailID, orders.OrderID, orderdetails.ProductID, "
                + "categories.CategoryID, categories.Name AS CategoryName, "
                + "products.Name AS ProductName, products.Description AS ProductDescription, "
                + "categories.Description AS CategoryDescription, orders.OrderDate, "
                + "products.Price, orders.TotalAmount, orders.Status, orders.ShippingAddress "
                + "FROM orderdetails "
                + "LEFT JOIN orders ON orderdetails.OrderID = orders.OrderID "
                + "LEFT JOIN products ON orderdetails.ProductID = products.ProductID "
                + "LEFT JOIN categories ON products.CategoryID = categories.CategoryID";

        try {
            PreparedStatement stm = conn.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                OrderDetail order = new OrderDetail(0, 0, 0, 0, sql, sql, sql, sql, orderDate, 0, 0, sql, sql);
                order.setOrderDetailID(rs.getInt("OrderDetailID"));
                order.setOrderID(rs.getInt("OrderID"));
                order.setProductID(rs.getInt("ProductID"));
                order.setCategoryID(rs.getInt("CategoryID"));
                order.setCategoryName(rs.getString("CategoryName"));
                order.setProductName(rs.getString("ProductName"));
                order.setProductDescription(rs.getString("ProductDescription"));
                order.setCategoryDescription(rs.getString("CategoryDescription"));
                order.setOrderDate(rs.getDate("OrderDate"));
                order.setPrice(rs.getDouble("Price"));
                order.setTotalAmount(rs.getDouble("TotalAmount"));
                order.setStatus(rs.getString("Status"));
                order.setShippingAddress(rs.getString("ShippingAddress"));

                list.add(order);
            }
        } catch (SQLException e) {
            System.out.println("getAll(): " + e.getMessage());
        }
        return list;
    }

}
