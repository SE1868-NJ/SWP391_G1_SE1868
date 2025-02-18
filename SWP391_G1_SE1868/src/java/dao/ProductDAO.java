/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dbcontext.DBConnection;
import java.sql.*;
import entity.Product;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Giang123
 */
public class ProductDAO extends DBConnection {

     // Phương thức này dùng để lấy danh sách sản phẩm yêu thích của khách hàng
    public List<Product> getFavoriteProducts(int customerID) {
        List<Product> favoriteProducts = new ArrayList<>();
        
        // Câu lệnh SQL để truy vấn các sản phẩm yêu thích
        String sql = "SELECT p.Name AS productName, pi.ImageURL AS productImage, p.Price AS productPrice, p.StockQuantity " +
                     "FROM Products p " +
                     "JOIN FavoritesDetails fd ON p.ProductID = fd.ProductID " +
                     "JOIN Favorites f ON fd.FavoriteID = f.FavoriteID " +
                     "JOIN ProductImages pi ON p.ProductID = pi.ProductID " +
                     "WHERE f.CustomerID = ?";
        
        // Kết nối cơ sở dữ liệu và thực thi câu lệnh SQL
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/SWP391_G1", "root", "1234");
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, customerID); // Thiết lập customerID vào câu lệnh SQL
            
            try (ResultSet rs = stmt.executeQuery()) {
                // Duyệt qua các kết quả trả về và thêm vào danh sách
                while (rs.next()) {
                    String productName = rs.getString("productName");
                    String productImage = rs.getString("productImage");
                    double productPrice = rs.getDouble("productPrice");
                    int stockQuantity = rs.getInt("StockQuantity");
                    
                    // Tạo đối tượng Product và thêm vào danh sách
                    Product product = new Product(productName, productImage, productPrice, stockQuantity);
                    favoriteProducts.add(product);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return favoriteProducts;
    }

       
    }


