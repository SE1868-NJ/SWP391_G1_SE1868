/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import dbcontext.DBContext;
import entity.Category;
import entity.Customer;
import entity.Product;
import entity.ProductImage;
import entity.ProductReview;
import java.sql.PreparedStatement;
import java.sql.ResultSet;   // tap ban ghi 
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.SQLException;
import java.sql.Timestamp;

/**
 *
 * @author Đạt
 */
public class ProductDAO extends DBContext {

    // Thêm sản phẩm
    public boolean addProduct(Product product) {
        String sql = "INSERT INTO Products (name, description, price, stockQuantity, createdAt, updatedAt, categoryId) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, product.getName());
            stmt.setString(2, product.getDescription());
            stmt.setDouble(3, product.getPrice());
            stmt.setInt(4, product.getStockQuantity());
            stmt.setTimestamp(5, Timestamp.valueOf(product.getCreatedAt()));
            stmt.setTimestamp(6, Timestamp.valueOf(product.getUpdatedAt()));
            stmt.setObject(7, product.getCategory() != null ? product.getCategory().getCategoryId() : null);

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                ResultSet generatedKeys = stmt.getGeneratedKeys();
                if (generatedKeys.next()) {
                    product.setProductId(generatedKeys.getInt(1));
                }
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // . Cập nhật sản phẩm
    public boolean updateProduct(Product product) {
        String sql = "UPDATE Products SET name = ?, description = ?, price = ?, stockQuantity = ?, updatedAt = ?, categoryId = ? WHERE productId = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, product.getName());
            stmt.setString(2, product.getDescription());
            stmt.setDouble(3, product.getPrice());
            stmt.setInt(4, product.getStockQuantity());
            stmt.setTimestamp(5, Timestamp.valueOf(product.getUpdatedAt()));
            stmt.setObject(6, product.getCategory() != null ? product.getCategory().getCategoryId() : null);
            stmt.setInt(7, product.getProductId());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // . Xóa sản phẩm
    public boolean deleteProduct(int productId) {
        String sql = "DELETE FROM Products WHERE productId = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, productId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Product getProductById(int productId) {
        String sql = "SELECT * FROM Products WHERE productId = ?";
        String sqlImages = "SELECT * FROM ProductImages WHERE productId = ?";  // Truy vấn để lấy danh sách hình ảnh

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, productId);
            try (ResultSet rs = stmt.executeQuery()) {
                CategoryDAO categoryDAO = new CategoryDAO();

                if (rs.next()) {
                    Product product = new Product();

                    product.setProductId(rs.getInt("productId"));
                    product.setName(rs.getString("name"));
                    product.setDescription(rs.getString("description"));
                    product.setPrice(rs.getDouble("price"));
                    product.setStockQuantity(rs.getInt("stockQuantity"));

                    // Kiểm tra NULL trước khi gọi .toLocalDateTime()
                    Timestamp createdTimestamp = rs.getTimestamp("createdAt");
                    product.setCreatedAt(createdTimestamp != null ? createdTimestamp.toLocalDateTime() : null);

                    Timestamp updatedTimestamp = rs.getTimestamp("updatedAt");
                    product.setUpdatedAt(updatedTimestamp != null ? updatedTimestamp.toLocalDateTime() : null);

                    // Lấy thông tin Category
                    Category category = categoryDAO.getCategoryById(rs.getInt("categoryId"));
                    product.setCategory(category);

                    // Lấy danh sách hình ảnh sản phẩm
                    try (PreparedStatement stmtImages = connection.prepareStatement(sqlImages)) {
                        stmtImages.setInt(1, productId);
                        try (ResultSet rsImages = stmtImages.executeQuery()) {
                            List<ProductImage> images = new ArrayList<>();
                            while (rsImages.next()) {
                                ProductImage productImage = new ProductImage();
                                productImage.setProductImageId(rsImages.getInt("productImageId"));
                                productImage.setImageUrl(rsImages.getString("imageUrl"));
                                productImage.setCreatedAt(rsImages.getTimestamp("createdAt") != null
                                        ? rsImages.getTimestamp("createdAt").toLocalDateTime() : null);

                                // Thiết lập đối tượng product cho mỗi hình ảnh
                                productImage.setProduct(product);  // Cập nhật đối tượng Product cho ProductImage

                                images.add(productImage);  // Thêm hình ảnh vào danh sách
                            }
                            product.setImages(images);  // Cập nhật danh sách hình ảnh vào product
                        }
                    }

                    return product;  // Trả về đối tượng Product đã được cập nhật
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;  // Nếu không tìm thấy sản phẩm, trả về null
    }

    public static void main(String[] args) {

        ProductDAO DAO = new ProductDAO();

        Product product = DAO.getProductById(1);

        System.out.println(product);
    }

}
