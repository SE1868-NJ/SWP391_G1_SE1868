/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author Đạt
 */
import dbcontext.DBContext;
import entity.Category;
import entity.Customer;
import entity.Product;
import entity.ProductReview;
import entity.Shop;
import java.sql.PreparedStatement;
import java.sql.ResultSet;   // tap ban ghi 
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.SQLException;

public class ShopDAO extends DBContext {
    //  4. Lấy shop theo ID

    public Shop getShopById(int shopId) {
        String sql = "SELECT * FROM Shop WHERE shopId = ?";
        String sqlProducts = "SELECT * FROM Products WHERE shopId = ?";  // Truy vấn để lấy danh sách sản phẩm liên quan đến cửa hàng

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, shopId);
            try (ResultSet rs = stmt.executeQuery()) {

                if (rs.next()) {
                    Shop shop = new Shop();

                    // Lấy thông tin cửa hàng
                    shop.setShopId(rs.getInt("shopId"));
                    shop.setName(rs.getString("name"));
                    shop.setLogo(rs.getString("logo"));
                    shop.setLocation(rs.getString("location"));
                    shop.setOwner(rs.getString("owner"));
                    shop.setCreatedAt(rs.getDate("createdAt").toLocalDate());
                    shop.setUpdatedAt(rs.getDate("updatedAt").toLocalDate());

                    // Lấy danh sách sản phẩm của cửa hàng
                    try (PreparedStatement stmtProducts = connection.prepareStatement(sqlProducts)) {
                        stmtProducts.setInt(1, shopId);
                        try (ResultSet rsProducts = stmtProducts.executeQuery()) {
                            List<Product> products = new ArrayList<>();
                            while (rsProducts.next()) {
                                Product product = new Product();
                                product.setProductId(rsProducts.getInt("productId"));
                                product.setName(rsProducts.getString("name"));
                                product.setDescription(rsProducts.getString("description"));
                                product.setPrice(rsProducts.getDouble("price"));
                                product.setStockQuantity(rsProducts.getInt("stockQuantity"));

                                // Thêm sản phẩm vào danh sách sản phẩm của cửa hàng
                                products.add(product);
                            }
                            shop.setProducts(products);  // Cập nhật danh sách sản phẩm vào cửa hàng
                        }
                    }

                    return shop;  // Trả về đối tượng Shop đã được cập nhật
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;  // Trả về null nếu không tìm thấy cửa hàng
    }

}
