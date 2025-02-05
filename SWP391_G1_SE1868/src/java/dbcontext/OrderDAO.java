package dbcontext;

import entity.Orders;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO extends DBContext {

    public List<Orders> getAllOrders(int orderID, int customerId, LocalDateTime orderDate, double otalAmount, String status, String shippingAddress, LocalDateTime createdAt, LocalDateTime updatedAt, int shipperId) {
        List<Orders> orders = new ArrayList<>();
        String query = "SELECT * FROM orders";
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
            Order order = new Order();
            order.setOrderId(resultSet.getInt("OrderID"));
            order.setCustomerId(resultSet.getInt("CustomerID"));
            order.setOrderDate(resultSet.getTimestamp("OrderDate").toLocalDateTime());
            order.setTotalAmount(resultSet.getDouble("TotalAmount"));
            order.setStatus(resultSet.getString("Status"));
            order.setShippingAddress(resultSet.getString("ShippingAddress"));
            order.setCreatedAt(resultSet.getTimestamp("CreatedAt") != null ? resultSet.getTimestamp("CreatedAt").toLocalDateTime() : null);
            order.setUpdatedAt(resultSet.getTimestamp("UpdatedAt") != null ? resultSet.getTimestamp("UpdatedAt").toLocalDateTime() : null);
            order.setShipperId(resultSet.getObject("ShipperID"));
            orders.add(order);
            }
        }
        return orders;
    }
}
