/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dbcontext;

import static dbcontext.GiangDBcontext.getConnection;
import entity.Favorite;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Giang123
 */
public class FavoriteDAO extends DBContext{
    private static final Logger LOGGER = Logger.getLogger(ProductDAO.class.getName());

    // 🔹 Lấy danh sách sản phẩm yêu thích của khách hàng (hỗ trợ phân trang)
    public List<Favorite> getFavoriteProducts(int customerId, int page, int pageSize) {
        List<Favorite> favoriteProducts = new ArrayList<>();
        int offset = (page - 1) * pageSize;

        String sql = """
                     SELECT 
                         p.ProductID, 
                         p.Name AS productName, 
                         pi.ImageURL AS productImage, 
                         p.Price AS productPrice, 
                         p.StockQuantity, 
                         s.Name AS shopName
                     FROM 
                         Products p 
                     JOIN 
                         FavoritesDetails fd ON p.ProductID = fd.ProductID 
                     JOIN 
                         Favorites f ON fd.FavoriteID = f.FavoriteID 
                     JOIN 
                         ProductImages pi ON p.ProductID = pi.ProductID 
                     JOIN 
                         Shop s ON p.ShopID = s.ShopID
                     WHERE 
                         f.CustomerID = ?
                     LIMIT ? OFFSET ?""";

        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, customerId);
            stmt.setInt(2, pageSize);
            stmt.setInt(3, offset);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Favorite favorite = new Favorite(
                            rs.getInt("ProductID"),
                            rs.getString("productName"),
                            rs.getString("productImage"),
                            rs.getDouble("productPrice"),
                            rs.getInt("StockQuantity"),
                            rs.getString("shopName")
                            
                    );
                    favoriteProducts.add(favorite);
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Lỗi khi lấy danh sách sản phẩm yêu thích", e);
        }
        return favoriteProducts;
    }

    // 🔹 Đếm tổng số sản phẩm yêu thích của khách hàng
    public int getTotalFavoriteProducts(int customerId) {
        String sql = "SELECT COUNT(*) FROM FavoritesDetails fd " +
                     "JOIN Favorites f ON fd.FavoriteID = f.FavoriteID " +
                     "WHERE f.CustomerID = ?";

        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, customerId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Lỗi khi đếm sản phẩm yêu thích", e);
        }
        return 0;
    }

    // 🔹 Cập nhật số lượng sản phẩm yêu thích
    public boolean updateFavoriteQuantity(int customerId, int productId, int quantity) {
        String sql = "UPDATE FavoritesDetails SET Quantity = ? " +
                     "WHERE ProductID = ? " +
                     "AND FavoriteID = (SELECT FavoriteID FROM Favorites WHERE CustomerID = ?)";

        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, quantity);
            stmt.setInt(2, productId);
            stmt.setInt(3, customerId);

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Lỗi khi cập nhật số lượng sản phẩm yêu thích", e);
            return false;
        }
    }

    // 🔹 Xóa sản phẩm yêu thích
    public boolean removeFavoriteProduct(int customerId, int productId) {
        String sql = "DELETE fd FROM FavoritesDetails fd " +
                     "JOIN Favorites f ON fd.FavoriteID = f.FavoriteID " +
                     "WHERE f.CustomerID = ? AND fd.ProductID = ?";

        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, customerId);
            stmt.setInt(2, productId);

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Lỗi khi xóa sản phẩm yêu thích", e);
            return false;
        }
    }
}
