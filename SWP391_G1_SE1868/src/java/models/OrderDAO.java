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
    
    

public  boolean createOrderWithPaymentAndDetails(Customer customer, List<Cart> carts, String shippingAddress, String paymentMethod) {
    String orderSql = "INSERT INTO Orders (customerId, orderDate, totalAmount, status, shippingAddress, createdAt, updatedAt, shipperId) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    String paymentSql = "INSERT INTO Payment (orderId, paymentDate, amount, paymentMethod, paymentStatus) VALUES (?, ?, ?, ?, ?)";
    String orderDetailSql = "INSERT INTO OrderDetails (orderId, productId, quantity, unitPrice, subTotal) VALUES (?, ?, ?, ?, ?)";

    try (PreparedStatement orderStmt = connection.prepareStatement(orderSql, Statement.RETURN_GENERATED_KEYS);
         PreparedStatement paymentStmt = connection.prepareStatement(paymentSql);
         PreparedStatement orderDetailStmt = connection.prepareStatement(orderDetailSql)) {

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

    public static void main(String[] args) {
        OrderDAO orderDAO = new OrderDAO();
        CustomerDAO customerDAO = new CustomerDAO();
        Customer customer = new Customer(1, "John Doe", "john.doe@example.com", 
                "acb5247f837fa3b652f43ddae7b521423b07e22afa3b8eab956eaa225f76b2738abe302744d53471e190fce3f43d96ae1aee9b747987a6a36b8aa7ce2a49bcd3",
                "1234567890", "123 Main St, Thành phố", null, "Male",null ,null, "assets/img/profile/product-12.jpg", true);
       String shippingAddress = "hà Nội";
        String paymentMethod = "ATM";
        
        boolean check =  orderDAO.createOrderWithPaymentAndDetails(customer, customerDAO.getCartsByCustomerId(1), shippingAddress, paymentMethod);
        System.out.println(check);
    }
}
