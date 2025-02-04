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
import java.sql.PreparedStatement;
import java.sql.ResultSet;   // tap ban ghi 
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.SQLException;
import java.sql.Timestamp;
public class CategoryDAO extends DBContext {
    // ✅ 1. Thêm danh mục
    public boolean addCategory(Category category) {
        String sql = "INSERT INTO Categories (name, description) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, category.getName());
            stmt.setString(2, category.getDescription());

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                ResultSet generatedKeys = stmt.getGeneratedKeys();
                if (generatedKeys.next()) {
                    category.setCategoryId(generatedKeys.getInt(1));
                }
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // ✅ 2. Cập nhật danh mục
    public boolean updateCategory(Category category) {
        String sql = "UPDATE Categories SET name = ?, description = ? WHERE categoryId = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, category.getName());
            stmt.setString(2, category.getDescription());
            stmt.setInt(3, category.getCategoryId());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // ✅ 3. Xóa danh mục
    public boolean deleteCategory(int categoryId) {
        String sql = "DELETE FROM Categories WHERE categoryId = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, categoryId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // ✅ 4. Lấy danh mục theo ID
    public Category getCategoryById(int categoryId) {
        String sql = "SELECT * FROM Categories WHERE categoryId = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, categoryId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return mapResultSetToCategory(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // ✅ 5. Lấy tất cả danh mục
    public List<Category> getAllCategories() {
        List<Category> categories = new ArrayList<>();
        String sql = "SELECT * FROM Categories ORDER BY name ASC";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                categories.add(mapResultSetToCategory(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categories;
    }

    // ✅ 6. Hàm trợ giúp chuyển đổi ResultSet thành Category
    private Category mapResultSetToCategory(ResultSet rs) throws SQLException {
        Category category = new Category();
        category.setCategoryId(rs.getInt("categoryId"));
        category.setName(rs.getString("name"));
        category.setDescription(rs.getString("description"));
        return category;
    }
}
