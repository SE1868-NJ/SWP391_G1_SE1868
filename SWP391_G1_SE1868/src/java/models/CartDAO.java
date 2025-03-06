/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import dbcontext.DBContext;
import dbcontext.DBContext;
import entity.Cart;
import entity.Category;
import entity.Customer;
import entity.Product;
import entity.ProductReview;
import java.sql.PreparedStatement;
import java.sql.ResultSet;   // tap ban ghi 
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.SQLException;
import java.time.LocalDate;
import java.sql.Date;
/**
 *
 * @author Đạt
 */
public class CartDAO extends DBContext{
    
    
       // Thêm sản phẩm vào giỏ hàng
    public boolean addToCart(int customerId, int productId, int quantity) {
        String sql = "INSERT INTO Carts (customerId, productId, quantity, createdAt) " +
                     "VALUES (?, ?, ?, ?) " +
                     "ON DUPLICATE KEY UPDATE quantity = quantity + ?";  // Nếu đã tồn tại thì cập nhật số lượng

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, customerId);
            stmt.setInt(2, productId);
            stmt.setInt(3, quantity);
            stmt.setDate(4, Date.valueOf(LocalDate.now()));
            stmt.setInt(5, quantity); // Dùng để cập nhật số lượng nếu đã tồn tại

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0; // Trả về true nếu thêm thành công
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Cập nhật số lượng sản phẩm trong giỏ hàng
    public boolean updateCart(int customerId, int productId, int newQuantity) {
        String sql = "UPDATE Carts SET quantity = ? WHERE customerId = ? AND productId = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, newQuantity);
            stmt.setInt(2, customerId);
            stmt.setInt(3, productId);

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0; // Trả về true nếu cập nhật thành công
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Xóa sản phẩm khỏi giỏ hàng
    public boolean removeFromCart(int customerId, int productId) {
        String sql = "DELETE FROM Cart WHERE customerId = ? AND productId = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, customerId);
            stmt.setInt(2, productId);

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0; // Trả về true nếu xóa thành công
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Lấy danh sách sản phẩm trong giỏ hàng của một khách hàng
    public List<Cart> getCartByCustomerId(int customerId) {
        List<Cart> cartList = new ArrayList<>();
        String sql = "SELECT * FROM Carts WHERE customerId = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, customerId);
            ResultSet rs = stmt.executeQuery();
            
            // khai váo DAO customer and Prodcuct
            
            Customer customer =  new CustomerDAO().getCustomerById(customerId);
            

            while (rs.next()) {
                Cart cart = new Cart();
                cart.setCartId(rs.getInt("cartId"));
                cart.setCustomer(customer);
                cart.setProduct(new ProductDAO().getProductById(rs.getInt("productId")));
                cart.setQuantity(rs.getInt("quantity"));
                cart.setCreatedAt(rs.getDate("createdAt").toLocalDate());

                cartList.add(cart);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cartList;
    }
    public static void main(String[] args) {
        List<Cart> carts =  new CartDAO().getCartByCustomerId(1);
        
        for (Cart cart : carts) {
            System.out.println(cart);
        }
    }
}
