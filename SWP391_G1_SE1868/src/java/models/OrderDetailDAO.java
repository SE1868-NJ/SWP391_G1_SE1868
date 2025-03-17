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

    // list OrderDetail
    public List<OrderDetail> getOrderDetailsByOrderIdSorted(int orderId, String sortOrderDetailBy, String sortOrderDetail) {
        List<OrderDetail> orderDetails = new ArrayList<>();

        // Xác định cột sắp xếp hợp lệ (Mặc định là quantity)
        String validSortOrderDetailBy = "quantity";
        if ("subTotal".equals(sortOrderDetailBy)) {
            validSortOrderDetailBy = "subTotal";
        }

        // Xác định thứ tự sắp xếp hợp lệ (ASC hoặc DESC, mặc định là DESC)
        String validSortOrderDetail = "DESC";
        if ("ASC".equalsIgnoreCase(sortOrderDetail)) {
            validSortOrderDetail = "ASC";
        }

        // Câu truy vấn SQL với sắp xếp động
        String sql = "SELECT * FROM OrderDetails WHERE orderId = ? ORDER BY " + validSortOrderDetailBy + " " + validSortOrderDetail;

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, orderId);
            ResultSet rs = stmt.executeQuery();

            // Khai báo ProductDAO để lấy thông tin sản phẩm
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

    public OrderDetail getOrderDetailById(int orderDetailId) {
        OrderDetail orderDetail = null;

        String sql = "SELECT * FROM OrderDetails WHERE orderDetailId = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, orderDetailId);
            ResultSet rs = stmt.executeQuery();

            // Khai báo ProductDAO để lấy thông tin sản phẩm
            ProductDAO productDAO = new ProductDAO();

            if (rs.next()) {
                orderDetail = new OrderDetail();
                orderDetail.setOrderDetailId(rs.getInt("orderDetailId"));
                orderDetail.setOrder(getOrderById(rs.getInt("orderId"))); // Lấy thông tin Order
                orderDetail.setProduct(productDAO.getProductByIdNoJoin(rs.getInt("productId"))); // Lấy thông tin Product
                orderDetail.setQuantity(rs.getInt("quantity"));
                orderDetail.setUnitPrice(rs.getDouble("unitPrice"));
                orderDetail.setSubTotal(rs.getDouble("subTotal"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderDetail;
    }

    // kiểm tra người dùng đã đánh giá đơn hàng đấy chưa 
    public boolean isOrderDetailExist(int orderDetailId, int customerId) {
        String sql = "SELECT 1 FROM OrderDetails WHERE orderDetailId = ? AND customerId = ?"; // Truy vấn kiểm tra sự tồn tại của orderDetailId và userId

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, orderDetailId);  // Thiết lập orderDetailId cho câu lệnh truy vấn
            stmt.setInt(2, customerId);  // Thiết lập userId cho câu lệnh truy vấn

            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next(); // Nếu có bản ghi trả về, trả về true
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false; // Trả về false nếu không tìm thấy bản ghi
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

        List<OrderDetail> orderDetails = orderDetailDAO.getOrderDetailsByOrderIdSorted(36, null, null);

        for (OrderDetail orderDetail : orderDetails) {
            System.out.println(orderDetail);
        }

    }

}
