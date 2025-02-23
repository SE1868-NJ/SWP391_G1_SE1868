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
public class OrderDetailDAO extends DBContext {

    public List<OrderDetail> getOrderDetailsByOrderId(int orderId) {
        List<OrderDetail> orderDetails = new ArrayList<>();
        String sql = "SELECT * FROM OrderDetails WHERE orderId = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, orderId);
            ResultSet rs = stmt.executeQuery();

            // khai báo DAO Porduct
            ProductDAO productDAO = new ProductDAO();
            while (rs.next()) {
                OrderDetail orderDetail = new OrderDetail();
                orderDetail.setOrderDetailId(rs.getInt("orderDetailId"));
                orderDetail.setOrder(getOrderById(rs.getInt("orderId"))); // Lấy thông tin Order
                orderDetail.setProduct(productDAO.getProductByIdNoJoin(rs.getInt("productId"))); // Lấy thông tin Product
                orderDetail.setQuantity(rs.getInt("quantity"));
                orderDetail.setUnitPrice(rs.getDouble("unitPrice"));
                orderDetail.setSubTotal(rs.getDouble("subTotal"));

                orderDetails.add(orderDetail);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderDetails;
    }

    // hàm lấy orderById private
    private Order getOrderById(int orderId) {
        String sql = "SELECT * FROM orders WHERE orderId = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, orderId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
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
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // TÍNH TÔNG GIÁ TRỊ ĐƠN HÀNG THEO ODERID
    public double calculateTotalAmountByOrderId(int orderId) {
        double totalAmount = 0.0;
        String sql = "SELECT * FROM OrderDetails WHERE orderId = ?"; // Truy vấn bảng order_details

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, orderId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                // Lấy subTotal từ từng OrderDetail
                double subTotal = rs.getInt("quantity") * rs.getDouble("unitPrice");
                totalAmount += subTotal;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Định dạng kết quả để không hiển thị dưới dạng khoa học
        System.out.println(String.format("%.0f", totalAmount)); // Chỉ in ra giá trị mà không có khoa học notation
        return totalAmount;
    }

    // THÊM OrderDetail
    public boolean addOrderDetail(OrderDetail orderDetail) {
        String sql = "INSERT INTO OrderDetails (orderId, productId, quantity, unitPrice, subTotal) "
                + "VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            // Cài đặt các tham số cho câu lệnh SQL
            stmt.setInt(1, orderDetail.getOrder().getOrderId()); // orderId từ đối tượng Order
            stmt.setInt(2, orderDetail.getProduct().getProductId()); // productId từ đối tượng Product
            stmt.setInt(3, orderDetail.getQuantity()); // quantity từ OrderDetail
            stmt.setDouble(4, orderDetail.getUnitPrice()); // unitPrice từ OrderDetail
            stmt.setDouble(5, orderDetail.getSubTotal()); // subTotal từ OrderDetail

            // Thực thi câu lệnh INSERT
            int rowsInserted = stmt.executeUpdate();

            // Nếu có ít nhất một dòng bị ảnh hưởng, trả về true
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Nếu có lỗi xảy ra, trả về false
        }
    }

    public static void main(String[] args) {
        OrderDetailDAO orderDetailDAO = new OrderDetailDAO();

        List<OrderDetail> orderDetails = orderDetailDAO.getOrderDetailsByOrderId(1);

        for (OrderDetail orderDetail : orderDetails) {
            System.out.println(orderDetail);
        }

    }

}
