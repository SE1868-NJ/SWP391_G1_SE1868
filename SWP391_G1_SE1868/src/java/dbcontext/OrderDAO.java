package dbcontext;

import entity.Orders;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrderDAO extends DBContext {

    PreparedStatement stm;
    ResultSet rs;

    //Select by orderID
    public Optional<Orders> getbyOrderID(String orderID) throws Exception {
        String sql = "SELECT * FROM Orders WHERE orderID = ?";

        try (Connection conn = getConnection(); PreparedStatement stm = conn.prepareStatement(sql)) {
            stm.setString(1, orderID);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                Orders order = mapResultSetToOrder(rs);
                return Optional.of(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
       
    }

    //Select all
    public List<Orders> getAll(String orderID, String customerId, String orderDate, String otalAmount, String status, String shippingAddress, String createdAt, String updatedAt, String shipperId) throws Exception {
        List<Orders> ordersList = new ArrayList<>();
        String sql = "select * from orders";
        try (Connection conn = getConnection(); PreparedStatement stm = conn.prepareStatement(sql)) {
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Orders order = mapResultSetToOrder(rs);
                ordersList.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ordersList;
    }

    private Orders mapResultSetToOrder(ResultSet rs) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
