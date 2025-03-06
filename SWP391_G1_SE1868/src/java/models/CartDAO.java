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
public class CartDAO extends DBContext {

    // Thêm sản phẩm vào giỏ hàng (Kiểm tra trước khi thêm)
    public boolean addToCart(int customerId, int productId, int quantity) {
        // Kiểm tra xem sản phẩm đã tồn tại trong giỏ hàng hay chưa
        String checkExistQuery = "SELECT quantity FROM Carts WHERE customerId = ? AND productId = ?";
        String insertQuery = "INSERT INTO Carts (customerId, productId, quantity, createdAt) VALUES (?, ?, ?, ?)";
        String updateQuery = "UPDATE Carts SET quantity = quantity + ? WHERE customerId = ? AND productId = ?";

        try (PreparedStatement checkStmt = connection.prepareStatement(checkExistQuery)) {
            checkStmt.setInt(1, customerId);
            checkStmt.setInt(2, productId);
            ResultSet rs = checkStmt.executeQuery();

            if (rs.next()) {
                // Nếu sản phẩm đã tồn tại, cập nhật số lượng
                try (PreparedStatement updateStmt = connection.prepareStatement(updateQuery)) {
                    updateStmt.setInt(1, quantity);
                    updateStmt.setInt(2, customerId);
                    updateStmt.setInt(3, productId);
                    int rowsAffected = updateStmt.executeUpdate();
                    return rowsAffected > 0;
                }
            } else {
                // Nếu chưa tồn tại, thêm mới vào giỏ hàng
                try (PreparedStatement insertStmt = connection.prepareStatement(insertQuery)) {
                    insertStmt.setInt(1, customerId);
                    insertStmt.setInt(2, productId);
                    insertStmt.setInt(3, quantity);
                    insertStmt.setDate(4, Date.valueOf(LocalDate.now()));
                    int rowsAffected = insertStmt.executeUpdate();
                    return rowsAffected > 0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    

    // Xóa sản phẩm khỏi giỏ hàng
    public boolean removeFromCart(int customerId, int productId) {
        String sql = "DELETE FROM Carts WHERE customerId = ? AND productId = ?";

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
            Customer customer = new CustomerDAO().getCustomerById(customerId);

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

    public static void main(String[] args) {
        List<Cart> carts = new CartDAO().getCartByCustomerId(1);

//        for (Cart cart : carts) {
//            System.out.println(cart);
//        }
        boolean check = new CartDAO().addToCart(1, 3,1);
        System.out.println(check);
    }
}
