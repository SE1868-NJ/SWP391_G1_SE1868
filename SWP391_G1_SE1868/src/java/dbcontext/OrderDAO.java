package dbcontext;

import dbcontext.DBContext;
import entity.Orders;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO extends DBContext {

    PreparedStatement stm;
    ResultSet rs;
    Connection conn;

    public OrderDAO() {
        try {
            conn = new DBContext().getConnection();
            if (conn == null) {
                System.out.println("Connection is null. Please check DBContext.");
            }
        } catch (Exception e) {
            System.out.println("OrderDAO connection error: " + e.getMessage());
        }
    }

    public List<Orders> getAll() {
        List<Orders> list = new ArrayList<>();
        String sql = "SELECT * FROM orders";

        try {
            if (conn == null) {
                System.out.println("Database connection is null in getAll()");
                return list;
            }

            stm = conn.prepareStatement(sql);
            rs = stm.executeQuery();
            while (rs.next()) {
                String orderID = String.valueOf(rs.getInt(1));
                String customerId = String.valueOf(rs.getInt(2));
                String orderDate = String.valueOf(rs.getDate(3));
                String totalAmount = String.valueOf(rs.getDouble(4));
                String status = rs.getString(5);
                String shippingAddress = rs.getString(6);
                String createdAt = String.valueOf(rs.getDate(7));
                String updatedAt = String.valueOf(rs.getDate(8));
                String shipperId = String.valueOf(rs.getInt(9));

                Orders ord = new Orders(orderID, customerId, orderDate, totalAmount, status, shippingAddress, createdAt, updatedAt, shipperId);
                list.add(ord);
            }
        } catch (SQLException e) {
            System.out.println("getAll() error: " + e.getMessage());
        }
        return list;
    }

    //select by orderID
    public Orders select(String orderID) {
        String sql = "SELECT * FROM Orders WHERE orderID = ?";
        Orders orders = null;
        try (PreparedStatement stm = conn.prepareStatement(sql)) {
            stm.setString(1, orderID);
            try (ResultSet rs = stm.executeQuery()) {
                if (rs.next()) {
                    String orderDate = rs.getString("orderDate");
                    String totalAmount = rs.getString("totalAmount");
                    String status = rs.getString("status");
                    String shippingAddress = rs.getString("shippingAddress");
                    String createdAt = rs.getString("createdAt");
                    String updatedAt = rs.getString("updatedAt");
                    String shipperId = rs.getString("shipperId");
                    orders = new Orders(orderID, shipperId, orderDate, totalAmount, status, shippingAddress, createdAt, updatedAt, shipperId);
                }
            }

        } catch (Exception e) {
            System.out.println("Error selecting Orders: " + e.getMessage());

        }
        return orders;
    }

    // insert 
    public void indert(String orderID, String customerId, String orderDate, String otalAmount, String status, String shippingAddress, String createdAt, String updatedAt, String shipperId) {
        String sql = "INSERT INTO orders\n"
                + "(`OrderID`,\n"
                + "`CustomerID`,\n"
                + "`OrderDate`,\n"
                + "`TotalAmount`,\n"
                + "`Status`,\n"
                + "`ShippingAddress`,\n"
                + "`CreatedAt`,\n"
                + "`UpdatedAt`,\n"
                + "`ShipperID`)\n"
                + "VALUES\n"
                + "(?,?,?,?,?,?,?,?,?)";
        try {
            stm = conn.prepareStatement(sql);
            stm.setString(1, orderID);
            stm.setString(2, customerId);
            stm.setString(3, orderDate);
            stm.setString(4, otalAmount);
            stm.setString(5, status);
            stm.setString(6, shippingAddress);
            stm.setString(7, createdAt);
            stm.setString(8, updatedAt);
            stm.setString(9, shipperId);
        } catch (SQLException e) {
            System.out.println("Error inserting Orders: " + e.getMessage());

        }
    }

    // Edit
    public void updateOrder(String orderID, String customerId, String orderDate, String otalAmount, String status, String shippingAddress, String createdAt, String updatedAt, String shipperId) {
        String sql = "UPDATE orders\n"
                + "SET\n"
                + "OrderID = ?,\n"
                + "CustomerID = ?,\n"
                + "OrderDate = ?,\n"
                + "TotalAmount = ?,\n"
                + "Status = ?,\n"
                + "ShippingAddress = ?,\n"
                + "CreatedAt = ?,\n"
                + "UpdatedAt = ?,\n"
                + "ShipperID = ?\n"
                + "WHERE OrderID = ?";
        try {
            stm = conn.prepareStatement(sql);
            stm.setString(1, orderID);
            stm.setString(2, customerId);
            stm.setString(3, orderDate);
            stm.setString(4, otalAmount);
            stm.setString(5, status);
            stm.setString(6, shippingAddress);
            stm.setString(7, createdAt);
            stm.setString(8, updatedAt);
            stm.setString(9, shipperId);

            stm.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error update Orders: " + e.getMessage());
        }
    }

    // Delete
    public void deleteOrder(String orderID) {
        String sql = "DELETE FROM orders\n"
                + "WHERE CustomerID = ?";
        try {
            stm = conn.prepareStatement(sql);
            stm.setString(1, orderID);
            stm.executeUpdate();
        } catch (SQLException e) {
        }
    }
}
