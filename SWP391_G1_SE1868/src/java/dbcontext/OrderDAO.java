package dbcontext;

import com.mysql.cj.xdevapi.PreparableStatement;
import dbcontext.DBContext;
import entity.OrderDetail;
import entity.Orders;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO extends DBContext {

    PreparedStatement stm;
    ResultSet rs;
    Connection conn;

    //Select all
    public List<Orders> getAll() {
        List<Orders> List = new ArrayList<>();
        String sql = "select * from orders";
        try {
            stm = conn.prepareStatement(sql);
            rs = stm.executeQuery();
            while (rs.next()) {
                String orderID = String.valueOf(rs.getInt(1));
                String customerId = String.valueOf(rs.getInt(2));
                String orderDate = String.valueOf(rs.getDate(3));
                String otalAmount = String.valueOf(rs.getDouble(4));
                String status = rs.getString(5);
                String shippingAddress = rs.getString(6);
                String createdAt = String.valueOf(rs.getDate(7));
                String updatedAt = String.valueOf(rs.getDate(8));
                String shipperId = String.valueOf(rs.getInt(9));

                Orders ord = new Orders(orderID, customerId, orderDate, otalAmount, status, shippingAddress, createdAt, updatedAt, shipperId);
                List.add(ord);
            }
        } catch (SQLException e) {
            System.out.println("getAll(): " + e.getMessage());
        }
        return List;
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
            System.out.println("Error selecting product: " + e.getMessage());

        }
        return orders;
    }
}
