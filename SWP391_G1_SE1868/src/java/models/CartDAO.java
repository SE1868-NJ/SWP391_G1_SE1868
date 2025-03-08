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
        String deleteQuery = "DELETE FROM Carts WHERE customerId = ? AND productId = ?";

        try (PreparedStatement checkStmt = connection.prepareStatement(checkExistQuery)) {
            checkStmt.setInt(1, customerId);
            checkStmt.setInt(2, productId);
            ResultSet rs = checkStmt.executeQuery();

            if (rs.next()) {
                int currentQuantity = rs.getInt("quantity");
                int newQuantity = currentQuantity + quantity;

                if (newQuantity <= 0) {
                    // Nếu số lượng <= 0, xóa sản phẩm khỏi giỏ hàng
                    try (PreparedStatement deleteStmt = connection.prepareStatement(deleteQuery)) {
                        deleteStmt.setInt(1, customerId);
                        deleteStmt.setInt(2, productId);
                        int rowsAffected = deleteStmt.executeUpdate();
                        return rowsAffected > 0;
                    }
                } else {
                    // Nếu số lượng hợp lệ, cập nhật số lượng
                    try (PreparedStatement updateStmt = connection.prepareStatement(updateQuery)) {
                        updateStmt.setInt(1, quantity);
                        updateStmt.setInt(2, customerId);
                        updateStmt.setInt(3, productId);
                        int rowsAffected = updateStmt.executeUpdate();
                        return rowsAffected > 0;
                    }
                }
            } else {
                // Nếu chưa tồn tại và số lượng > 0, thêm mới vào giỏ hàng
                if (quantity > 0) {
                    try (PreparedStatement insertStmt = connection.prepareStatement(insertQuery)) {
                        insertStmt.setInt(1, customerId);
                        insertStmt.setInt(2, productId);
                        insertStmt.setInt(3, quantity);
                        insertStmt.setDate(4, Date.valueOf(LocalDate.now()));
                        int rowsAffected = insertStmt.executeUpdate();
                        return rowsAffected > 0;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    

    // cập nhập quantity
    public boolean updateQuantity(int customerId, int productId, int newQuantity) {
        // Câu lệnh SQL để kiểm tra xem sản phẩm có trong giỏ hàng hay không
        String checkExistQuery = "SELECT quantity FROM Carts WHERE customerId = ? AND productId = ?";
        String updateQuery = "UPDATE Carts SET quantity = ? WHERE customerId = ? AND productId = ?";

        try (PreparedStatement checkStmt = connection.prepareStatement(checkExistQuery)) {
            checkStmt.setInt(1, customerId);
            checkStmt.setInt(2, productId);
            ResultSet rs = checkStmt.executeQuery();

            if (rs.next()) {
                // Nếu sản phẩm đã tồn tại trong giỏ hàng, cập nhật số lượng mới
                try (PreparedStatement updateStmt = connection.prepareStatement(updateQuery)) {
                    updateStmt.setInt(1, newQuantity);  // Gán số lượng mới
                    updateStmt.setInt(2, customerId);
                    updateStmt.setInt(3, productId);
                    int rowsAffected = updateStmt.executeUpdate();
                    return rowsAffected > 0;  // Trả về true nếu cập nhật thành công
                }
            } else {
                // Nếu sản phẩm chưa có trong giỏ hàng, trả về false
                System.out.println("Sản phẩm không có trong giỏ hàng.");
                return false;
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

    // Lấy tổng số sản phẩm trong giỏ hàng của một khách hàng
    public int getTotalCartQuantity(int customerId) {
        String sql = "SELECT Count(quantity) FROM Carts WHERE customerId = ?";
        int totalQuantity = 0;

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, customerId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                totalQuantity = rs.getInt(1); // Lấy tổng số lượng sản phẩm
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return totalQuantity > 0 ? totalQuantity : 0; // Tránh trả về giá trị null
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

    public double getTotalAmount(int customerId) {
        List<Cart> carts = getCartByCustomerId(customerId);
        double totalAmount = 0;

        for (Cart cart : carts) {
            totalAmount += cart.getProduct().getPrice() * cart.getQuantity();
        }

        return totalAmount;
    }

    public static void main(String[] args) {
        List<Cart> carts = new CartDAO().getCartByCustomerId(1);

//        for (Cart cart : carts) {
//            System.out.println(cart);
//        }
        int check = new CartDAO().getTotalCartQuantity(1);
        System.out.println(check);
    }
}
