/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import dbcontext.DBContext;
import entity.Cart;
import entity.CartItem;
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
import java.sql.Date;

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
            stmt.setInt(1, order.getCustomer().getCustomerId()); // Giả sử có getter cho customerId
            stmt.setDate(2, Date.valueOf(order.getOrderDate()));  // Chuyển LocalDate sang java.sql.Date
            stmt.setDouble(3, order.getTotalAmount());
            stmt.setString(4, order.getStatus());
            stmt.setString(5, order.getShippingAddress());
            stmt.setDate(6, Date.valueOf(order.getCreatedAt())); // Chuyển LocalDate sang java.sql.Date
            stmt.setDate(7, Date.valueOf(order.getUpdatedAt())); // Chuyển LocalDate sang java.sql.Date

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
    public boolean deleteCartItemsByCartId(int cartId) {
        String sql = "DELETE FROM CartItems WHERE cartId = ?"; // Xóa tất cả CartItems có cartId tương ứng

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, cartId);  // Thiết lập cartId cho câu lệnh truy vấn

            // Thực thi câu lệnh DELETE
            int rowsDeleted = stmt.executeUpdate();

            // Nếu có ít nhất một dòng bị xóa, trả về true
            return rowsDeleted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Nếu có lỗi xảy ra, trả về false
        }
    }

    // lấy ra các cartItem với cartId truyền vào
    public List<CartItem> getCartItemsByCartId(int cartId) {
        List<CartItem> cartItems = new ArrayList<>();
        String sql = "SELECT * FROM CartItems WHERE cartId = ?";  // Truy vấn CartItems theo cartId

        // Tạo đối tượng Cart chỉ một lần để tránh việc gọi getCartByCustomerId nhiều lần
        Cart cart = getCartByCartId(cartId);
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, cartId);  // Thiết lập cartId cho câu lệnh truy vấn

            // khai báo prodcut Dao
            ProductDAO productDAO = new ProductDAO();
            try (ResultSet rs = stmt.executeQuery()) {
                // Duyệt qua kết quả truy vấn và tạo đối tượng CartItem từ ResultSet
                while (rs.next()) {
                    CartItem cartItem = new CartItem();
                    cartItem.setCartItemId(rs.getInt("cartItemId"));
                    cartItem.setCart(cart);
                    cartItem.setProduct(productDAO.getProductByIdNoJoin(rs.getInt("productId")));
                    cartItem.setQuantity(rs.getInt("quantity"));
                    cartItem.setAddedAt(rs.getDate("addedAt").toLocalDate());

                    cartItems.add(cartItem);  // Thêm CartItem vào danh sách
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cartItems;  // Trả về danh sách tất cả CartItems
    }
    

    // lấy cart thẻo CustomerID
    public Cart getCartByCustomerId(int CustomerId) {

        String sql = "SELECT * FROM Carts WHERE CustomerId = ? ORDER BY cartId DESC LIMIT 1 "; // Truy vấn giỏ hàng theo cartId

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, CustomerId);  // Thiết lập cartId cho câu lệnh truy vấn
            
            // khai báo customerDAO
            CustomerDAO customerDAO = new CustomerDAO();
            
            // tạo cusotmer 
            Customer customer = customerDAO.getCustomerById(CustomerId);
            
            try (ResultSet rs = stmt.executeQuery()) {
                // Nếu tìm thấy giỏ hàng với cartId, lấy thông tin
                if (rs.next()) {
                    Cart cart = new Cart();
                    cart.setCartId(rs.getInt("cartId"));

                    // truyền đối tương customer
                    cart.setCustomer(customer);

                    cart.setCreatedAt(rs.getDate("createdAt").toLocalDate());

                    return cart;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;  // Trả về đối tượng Cart nếu tìm thấy, ngược lại trả về null
    }
    
    
    // lấy cart thẻo CartID
    public Cart getCartByCartId(int CartID) {

        String sql = "SELECT * FROM Carts WHERE CartID = ?"; // Truy vấn giỏ hàng theo cartId

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, CartID);  // Thiết lập cartId cho câu lệnh truy vấn
           
            // khai báo customerDAO
            CustomerDAO customerDAO = new CustomerDAO();
            
            
            
            try (ResultSet rs = stmt.executeQuery()) {
                // Nếu tìm thấy giỏ hàng với cartId, lấy thông tin
                if (rs.next()) {
                    Cart cart = new Cart();
                    
                    cart.setCartId(rs.getInt("cartId"));
                    
                    // truyền đối tương customer
                    cart.setCustomer(customerDAO.getCustomerById(rs.getInt("CustomerId")));
                    
                    cart.setCreatedAt(rs.getDate("createdAt").toLocalDate());

                    return cart;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;  // Trả về đối tượng Cart nếu tìm thấy, ngược lại trả về null
    }

    public static void main(String[] args) {
        OrderDAO orderDAO = new OrderDAO();

        List<CartItem> cart = orderDAO.getCartItemsByCartId(1);
        for (CartItem cartItem : cart) {
            System.out.println(cartItem);
        }
    }
}
