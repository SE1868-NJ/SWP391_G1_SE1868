/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import dbcontext.DBContext;
import entity.Cart;
import entity.Category;
import entity.Customer;
import entity.Order;
import entity.OrderDetail;
import entity.Product;
import entity.ProductReview;
import entity.Shipper;
import java.sql.PreparedStatement;
import java.sql.ResultSet;   // tap ban ghi 
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.SQLException;
import java.sql.Date;
import java.time.LocalDate;

/**
 *
 * @author Đạt
 */
public class OrderDAO extends DBContext {

    public boolean updateOrder(Order order) {
        String sql = "UPDATE Orders SET customerId = ?, orderDate = ?, totalAmount = ?, status = ?, shippingAddress = ?, updatedAt = ? WHERE orderId = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            // Cập nhật thông tin của order
            stmt.setInt(1, order.getCustomer().getCustomerId()); // Giả sử có getter cho customerId
            stmt.setDate(2, Date.valueOf(order.getOrderDate()));
            stmt.setDouble(3, order.getTotalAmount());
            stmt.setString(4, order.getStatus());
            stmt.setString(5, order.getShippingAddress());
            stmt.setDate(6, Date.valueOf(order.getUpdatedAt()));
            stmt.setInt(7, order.getOrderId());

            // Thực thi câu lệnh UPDATE
            int rowsUpdated = stmt.executeUpdate();

            // Nếu có ít nhất một dòng bị ảnh hưởng, trả về true
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Nếu có lỗi xảy ra, trả về false
        }
    }

    // get byId
    public Order getOrderById(int orderId) {

        String sql = "SELECT * FROM Orders WHERE orderId = ?"; // Truy vấn đơn hàng theo orderId

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, orderId);  // Thiết lập orderId cho câu lệnh truy vấn

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    // Tạo đối tượng Order từ kết quả truy vấn
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
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    // thêm đơn hàng
    public boolean addOrder(Order order) {
        String sql = "INSERT INTO Orders (customerId, orderDate, totalAmount, status, shippingAddress, createdAt, updatedAt) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            // Thêm thông tin của order vào câu lệnh SQL
            stmt.setInt(1, order.getCustomer().getCustomerId());
            stmt.setDate(2, Date.valueOf(order.getOrderDate()));
            stmt.setDouble(3, order.getTotalAmount());
            stmt.setString(4, order.getStatus());
            stmt.setString(5, order.getShippingAddress());
            stmt.setDate(6, Date.valueOf(order.getCreatedAt()));
            stmt.setDate(7, Date.valueOf(order.getUpdatedAt()));

            // Thực thi câu lệnh INSERT
            int rowsInserted = stmt.executeUpdate();

            // Nếu có ít nhất một dòng bị ảnh hưởng, trả về true
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Nếu có lỗi xảy ra, trả về false
        }
    }

    // Xóa tất cả CartItems có cartId tương ứng
    public boolean deleteCartBycustomerID(int customerId) {
        String sql = "DELETE FROM Carts WHERE customerId = ?"; // Xóa tất cả CartItems có cartId tương ứng

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, customerId);  // Thiết lập cartId cho câu lệnh truy vấn

            // Thực thi câu lệnh DELETE
            int rowsDeleted = stmt.executeUpdate();

            // Nếu có ít nhất một dòng bị xóa, trả về true
            return rowsDeleted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Nếu có lỗi xảy ra, trả về false
        }
    }

    public boolean createOrderWithPaymentAndDetails(Customer customer, List<Cart> carts, String shippingAddress, String paymentMethod) {
        String orderSql = "INSERT INTO Orders (customerId, orderDate, totalAmount, status, shippingAddress, createdAt, updatedAt, shipperId) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        String paymentSql = "INSERT INTO Payment (orderId, paymentDate, amount, paymentMethod, paymentStatus) VALUES (?, ?, ?, ?, ?)";
        String orderDetailSql = "INSERT INTO OrderDetails (orderId, productId, quantity, unitPrice, subTotal) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement orderStmt = connection.prepareStatement(orderSql, Statement.RETURN_GENERATED_KEYS); PreparedStatement paymentStmt = connection.prepareStatement(paymentSql); PreparedStatement orderDetailStmt = connection.prepareStatement(orderDetailSql)) {

            // Tính tổng số tiền cho đơn hàng
            double totalAmount = 0;
            for (Cart cart : carts) {
                Product product = cart.getProduct();
                totalAmount += product.getPrice() * cart.getQuantity();
            }

            // Tạo đơn hàng
            orderStmt.setInt(1, customer.getCustomerId());
            orderStmt.setDate(2, Date.valueOf(LocalDate.now()));
            orderStmt.setDouble(3, totalAmount);
            orderStmt.setString(4, "Pending");  // Trạng thái đơn hàng
            orderStmt.setString(5, shippingAddress);
            orderStmt.setDate(6, Date.valueOf(LocalDate.now())); // createdAt
            orderStmt.setDate(7, Date.valueOf(LocalDate.now())); // updatedAt
            orderStmt.setInt(8, 1);

            int rowsAffected = orderStmt.executeUpdate();
            if (rowsAffected == 0) {
                return false;  // Nếu không có đơn hàng nào được thêm trả về false
            }

            // Lấy ID của đơn hàng mới tạo
            ResultSet generatedKeys = orderStmt.getGeneratedKeys();
            int orderId = 0;
            if (generatedKeys.next()) {
                orderId = generatedKeys.getInt(1);
            }

            // Tạo Payment
            paymentStmt.setInt(1, orderId);
            paymentStmt.setDate(2, Date.valueOf(LocalDate.now()));
            paymentStmt.setDouble(3, totalAmount); // Payment amount
            paymentStmt.setString(4, paymentMethod);
            paymentStmt.setString(5, "Pending"); // Payment status
            paymentStmt.executeUpdate();

            // Thêm chi tiết đơn hàng
            for (Cart cart : carts) {
                Product product = cart.getProduct();
                int quantityInCart = cart.getQuantity();
                double unitPrice = product.getPrice();
                double subTotal = unitPrice * quantityInCart;

                orderDetailStmt.setInt(1, orderId);
                orderDetailStmt.setInt(2, product.getProductId());
                orderDetailStmt.setInt(3, quantityInCart);
                orderDetailStmt.setDouble(4, unitPrice);
                orderDetailStmt.setDouble(5, subTotal);
                orderDetailStmt.addBatch();  // Thêm vào batch
            }

            // Thực thi batch để thêm tất cả chi tiết đơn hàng
            orderDetailStmt.executeBatch();

            return true;  // Trả về true nếu mọi thao tác thành công

        } catch (SQLException e) {
            e.printStackTrace();
            return false;  // Nếu có lỗi xảy ra, trả về false
        }
    }

    // lấy list đơn hang của cumtomer
    public List<Order> getOrdersByCustomerId(int customerId, LocalDate startDate, LocalDate endDate, int page, int pageSize, String sortBy, String sortOrder) {
        List<Order> orders = new ArrayList<>();

        // Xác định cột sắp xếp hợp lệ
        String validSortBy = "orderDate"; // Mặc định sắp xếp theo orderDate
        if ("totalAmount".equals(sortBy)) {
            validSortBy = "totalAmount";
        }

        // Xác định thứ tự sắp xếp hợp lệ (ASC hoặc DESC)
        String validSortOrder = "DESC"; // Mặc định là giảm dần
        if ("ASC".equalsIgnoreCase(sortOrder)) {
            validSortOrder = "ASC";
        }

        // Xây dựng truy vấn SQL động với điều kiện thời gian, sắp xếp và phân trang
        String sql = "SELECT * FROM Orders WHERE customerId = ? ";
        if (startDate != null && endDate != null) {
            sql += "AND orderDate BETWEEN ? AND ? ";
        }
        sql += "ORDER BY " + validSortBy + " " + validSortOrder + " LIMIT ? OFFSET ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, customerId);

            int index = 2;
            if (startDate != null && endDate != null) {
                stmt.setDate(index++, Date.valueOf(startDate));
                stmt.setDate(index++, Date.valueOf(endDate));
            }

            // Thiết lập giới hạn (LIMIT) và vị trí bắt đầu (OFFSET)
            stmt.setInt(index++, pageSize);
            stmt.setInt(index, (page - 1) * pageSize);

            // Khai báo PaymentDAO và ShipperDAO
            PaymentDAO paymentDAO = new PaymentDAO();
            ShipperDAO shipperDAO = new ShipperDAO();
            Shipper shipper = shipperDAO.getShipperById(1);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Order order = new Order();
                    order.setOrderId(rs.getInt("orderId"));
                    order.setOrderDate(rs.getDate("orderDate").toLocalDate());
                    order.setTotalAmount(rs.getDouble("totalAmount"));
                    order.setStatus(rs.getString("status"));
                    order.setShippingAddress(rs.getString("shippingAddress"));
                    order.setCreatedAt(rs.getDate("createdAt").toLocalDate());
                    order.setUpdatedAt(rs.getDate("updatedAt").toLocalDate());
                    order.setPayment(paymentDAO.getPaymentByOrderId(rs.getInt("orderId")));
                    order.setShipper(shipper);

                    orders.add(order);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return orders;
    }

    // lấy tổng số đơn hàng
    public int getTotalOrderPages(int customerId, LocalDate startDate, LocalDate endDate, int pageSize) {
        int totalOrders = 0;
        String sql = "SELECT COUNT(*) FROM Orders WHERE customerId = ?";

        if (startDate != null && endDate != null) {
            sql += " AND orderDate BETWEEN ? AND ?";
        }

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, customerId);

            int index = 2;
            if (startDate != null && endDate != null) {
                stmt.setDate(index++, Date.valueOf(startDate));
                stmt.setDate(index, Date.valueOf(endDate));
            }

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    totalOrders = rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return (int) Math.ceil((double) totalOrders / pageSize);
    }

    // lấy đơn hàng mới nhất của cutomer
    public Order getLatestOrderByCustomerId(int customerId) {

        // Truy vấn đơn hàng mới nhất của customerId, sắp xếp theo ngày tạo (createdAt) giảm dần
        String sql = "SELECT * FROM Orders WHERE customerId = ? ORDER BY createdAt DESC LIMIT 1";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, customerId);  // Thiết lập customerId cho câu lệnh truy vấn

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    // Tạo đối tượng Order từ kết quả truy vấn
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
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;  // Trả về null nếu không tìm thấy đơn hàng
    }

    // Phương thức cập nhật số lượng sản phẩm từ giỏ hàng
    public boolean updateProductStockFromCart(List<Cart> carts) {
        String sql = "UPDATE Products SET stockQuantity = ? WHERE productId = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {

            for (Cart cart : carts) {
                Product product = cart.getProduct();
                int quantityInCart = cart.getQuantity();
                int updatedStock = product.getStockQuantity() - quantityInCart;

                // Chỉ cập nhật nếu có đủ hàng trong kho
                if (updatedStock >= 0) {
                    stmt.setInt(1, updatedStock);
                    stmt.setInt(2, product.getProductId());
                    // Cập nhật và kiểm tra xem có thay đổi nào không
                    if (stmt.executeUpdate() > 0) {
                        return true;  // Trả về true ngay khi có thay đổi đầu tiên
                    }
                }
            }

            // Trả về false nếu không có cập nhật nào thành công
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void main(String[] args) {
        OrderDAO orderDAO = new OrderDAO();
        CustomerDAO customerDAO = new CustomerDAO();

        var check = orderDAO.getOrdersByCustomerId(1,null, null, 1, 10, null, null);
        for (Order order : check) {
            System.out.println(order);
        }
    }
}
