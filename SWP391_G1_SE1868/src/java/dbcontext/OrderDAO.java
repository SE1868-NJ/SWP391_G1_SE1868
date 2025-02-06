package dbcontext;

import dbcontext.DBContext;
import entity.OrderDetail;
import entity.Orders;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class OrderDAO extends DBContext {

    PreparedStatement stm;
    ResultSet rs;

    //Select all
    public List<Orders> getAll(String orderID, String customerId, String orderDate, String otalAmount, String status, String shippingAddress, String createdAt, String updatedAt, String shipperId) throws Exception {
        List<Orders> ordersList = new ArrayList<>();
        String sql = "select * from orders";
        try {
            rs = stm.executeQuery();
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ordersList;
    }


}
