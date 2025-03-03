/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dbcontext;
import dto.CartItemDTO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class CartDAO extends GiangDBcontext{
    public void addToCart(int customerId, int productId) throws SQLException {

        try {
            // Bắt đầu transaction
            Connection connection = getConnection();
            connection.setAutoCommit(false);  // Tắt tự động commit để thực hiện transaction

            try {
                // Kiểm tra sản phẩm đã có trong giỏ hàng hay chưa
                String checkExistQuery = "SELECT Quantity FROM Carts WHERE CustomerID = ? AND ProductID = ?";
                try (PreparedStatement stmt = connection.prepareStatement(checkExistQuery)) {
                    stmt.setInt(1, customerId);
                    stmt.setInt(2, productId);
                    ResultSet rs = stmt.executeQuery();

                    if (rs.next()) {
                        // Sản phẩm đã có trong giỏ hàng, cập nhật số lượng
                        int existingQuantity = rs.getInt("Quantity");
                        String updateQuery = "UPDATE Carts SET Quantity = ? WHERE CustomerID = ? AND ProductID = ?";
                        try (PreparedStatement updateStmt = connection.prepareStatement(updateQuery)) {
                            updateStmt.setInt(1, existingQuantity + 1); // Cập nhật số lượng sản phẩm trong giỏ hàng
                            updateStmt.setInt(2, customerId);
                            updateStmt.setInt(3, productId);
                            updateStmt.executeUpdate();
                        }
                    } else {
                        // Sản phẩm chưa có trong giỏ hàng, thêm mới vào giỏ
                        String insertQuery = "INSERT INTO Carts (CustomerID, ProductID, Quantity) VALUES (?, ?, ?)";
                        try (PreparedStatement insertStmt = connection.prepareStatement(insertQuery)) {
                            insertStmt.setInt(1, customerId);
                            insertStmt.setInt(2, productId);
                            insertStmt.setInt(3, 1);
                            insertStmt.executeUpdate();
                        }
                    }
                    // Cập nhật số lượng sản phẩm trong kho
                    updateProductStockQuantity(productId, 1);
                }

                // Commit transaction nếu mọi thứ thành công
                connection.commit();
            } catch (SQLException e) {
                // Nếu có lỗi, rollback transaction
                connection.rollback();
                throw e;  // Ném lại exception để xử lý ở nơi khác
            } finally {
                // Đảm bảo bật lại auto-commit sau khi hoàn thành
                connection.setAutoCommit(true);
            }
        } catch (Exception ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updateCartItemQuantity(int customerId, int productId, int newQuantity) throws SQLException {

        try {
            // Bắt đầu transaction
            Connection connection = getConnection();
            connection.setAutoCommit(false);  // Tắt tự động commit để thực hiện transaction

            try {
                // Lấy số lượng cũ trong giỏ hàng
                String currentQuantityQuery = "SELECT Quantity FROM Carts WHERE CustomerID = ? AND ProductID = ?";
                try (PreparedStatement currentQuantityStmt = connection.prepareStatement(currentQuantityQuery)) {
                    currentQuantityStmt.setInt(1, customerId);
                    currentQuantityStmt.setInt(2, productId);
                    ResultSet rs = currentQuantityStmt.executeQuery();

                    if (rs.next()) {
                        int oldQuantity = rs.getInt("Quantity");
                        int quantityDifference = newQuantity - oldQuantity;  // Tính sự thay đổi về số lượng

                        // Cập nhật số lượng sản phẩm trong giỏ hàng
                        String updateQuery = "UPDATE Carts SET Quantity = ? WHERE CustomerID = ? AND ProductID = ?";
                        try (PreparedStatement updateStmt = connection.prepareStatement(updateQuery)) {
                            updateStmt.setInt(1, newQuantity);
                            updateStmt.setInt(2, customerId);
                            updateStmt.setInt(3, productId);
                            updateStmt.executeUpdate();
                        }

                        // Cập nhật số lượng sản phẩm trong kho
                        updateProductStockQuantity(productId, quantityDifference);
                    }
                }

                // Commit transaction nếu mọi thứ thành công
                connection.commit();
            } catch (SQLException e) {
                // Nếu có lỗi, rollback transaction
                connection.rollback();
                throw e;  // Ném lại exception để xử lý ở nơi khác
            } finally {
                // Đảm bảo bật lại auto-commit sau khi hoàn thành
                connection.setAutoCommit(true);
            }
        } catch (Exception ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Phương thức cập nhật số lượng sản phẩm trong kho
    private void updateProductStockQuantity(int productId, int quantityChange) throws SQLException {
        String updateStockQuery = "UPDATE Products SET StockQuantity = StockQuantity - ? WHERE ProductID = ?";
        try (PreparedStatement stmt = getConnection().prepareStatement(updateStockQuery)) {
            stmt.setInt(1, quantityChange);  // Giảm stock theo số lượng sản phẩm được thêm vào hoặc cập nhật trong giỏ
            stmt.setInt(2, productId);
            stmt.executeUpdate();
        } catch (Exception ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void clearCart(int customerId) throws SQLException {

        try {
            // Bắt đầu transaction
            Connection connection = getConnection();
            connection.setAutoCommit(false);  // Tắt tự động commit để thực hiện transaction

            try {
                // Lấy tất cả sản phẩm trong giỏ hàng của khách hàng
                String selectAllQuery = "SELECT ProductID, Quantity FROM Carts WHERE CustomerID = ?";
                try (PreparedStatement selectStmt = connection.prepareStatement(selectAllQuery)) {
                    selectStmt.setInt(1, customerId);
                    ResultSet rs = selectStmt.executeQuery();

                    while (rs.next()) {
                        int productId = rs.getInt("ProductID");
                        int quantityToRemove = rs.getInt("Quantity");

                        // Cập nhật lại số lượng sản phẩm trong kho (tăng lại stockQuantity)
                        updateProductStockQuantity(productId, -quantityToRemove);  // Cộng lại số lượng sản phẩm đã bị xóa khỏi giỏ hàng
                    }

                    // Xóa tất cả sản phẩm trong giỏ hàng
                    String deleteAllQuery = "DELETE FROM Carts WHERE CustomerID = ?";
                    try (PreparedStatement deleteStmt = connection.prepareStatement(deleteAllQuery)) {
                        deleteStmt.setInt(1, customerId);
                        deleteStmt.executeUpdate();
                    }
                }

                // Commit transaction nếu mọi thứ thành công
                connection.commit();
            } catch (SQLException e) {
                // Nếu có lỗi, rollback transaction
                connection.rollback();
                throw e;  // Ném lại exception để xử lý ở nơi khác
            } finally {
                // Đảm bảo bật lại auto-commit sau khi hoàn thành
                connection.setAutoCommit(true);
            }
        } catch (Exception ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void removeFromCart(int customerId, int productId) throws SQLException {
        try {
            // Bắt đầu transaction
            Connection connection = getConnection();
            connection.setAutoCommit(false);  // Tắt tự động commit để thực hiện transaction

            try {
                // Lấy số lượng sản phẩm trong giỏ hàng trước khi xóa
                String currentQuantityQuery = "SELECT Quantity FROM Carts WHERE CustomerID = ? AND ProductID = ?";
                try (PreparedStatement currentQuantityStmt = connection.prepareStatement(currentQuantityQuery)) {
                    currentQuantityStmt.setInt(1, customerId);
                    currentQuantityStmt.setInt(2, productId);
                    ResultSet rs = currentQuantityStmt.executeQuery();

                    if (rs.next()) {
                        int quantityToRemove = rs.getInt("Quantity");

                        // Xóa sản phẩm khỏi giỏ hàng
                        String deleteQuery = "DELETE FROM Carts WHERE CustomerID = ? AND ProductID = ?";
                        try (PreparedStatement deleteStmt = connection.prepareStatement(deleteQuery)) {
                            deleteStmt.setInt(1, customerId);
                            deleteStmt.setInt(2, productId);
                            deleteStmt.executeUpdate();
                        }

                        // Cập nhật lại số lượng sản phẩm trong kho (tăng lại stockQuantity)
                        updateProductStockQuantity(productId, -quantityToRemove);  // Cộng lại số lượng sản phẩm đã bị xóa khỏi giỏ hàng
                    }
                }

                // Commit transaction nếu mọi thứ thành công
                connection.commit();
            } catch (SQLException e) {
                // Nếu có lỗi, rollback transaction
                connection.rollback();
                throw e;  // Ném lại exception để xử lý ở nơi khác
            } finally {
                // Đảm bảo bật lại auto-commit sau khi hoàn thành
                connection.setAutoCommit(true);
            }
        } catch (Exception ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    

    public List<CartItemDTO> getCartByCustomerId(int customerId) throws SQLException {
        List<CartItemDTO> cartList = new ArrayList<>();

        // Câu lệnh SQL để lấy dữ liệu giỏ hàng của khách hàng
        String query = "SELECT c.CartID, c.CustomerID, c.CreatedAt, c.ProductID, c.Quantity, "
                + "p.name, pi.ImageURL, p.Price "
                + "FROM Carts c "
                + "JOIN Products p ON c.ProductID = p.ProductID "
                + "LEFT JOIN ProductImages pi ON c.ProductID = pi.ProductID "
                + "WHERE c.CustomerID = ?";

        try (PreparedStatement stmt = getConnection().prepareStatement(query)) {
            stmt.setInt(1, customerId);
            ResultSet rs = stmt.executeQuery();

            // Duyệt qua các kết quả và tạo đối tượng Cart từ kết quả truy vấn
            while (rs.next()) {
                CartItemDTO cart = new CartItemDTO();
                cart.setCartId(rs.getInt("CartID"));
                cart.setCustomerId(rs.getInt("CustomerID"));
                cart.setCreatedAt(rs.getTimestamp("CreatedAt").toLocalDateTime());
                cart.setProductId(rs.getInt("ProductID"));
                cart.setQuantity(rs.getInt("Quantity"));
                cart.setProductName(rs.getString("name"));
                cart.setImage(rs.getString("ImageURL"));
                cart.setPrice(rs.getBigDecimal("Price"));

                cartList.add(cart);
            }
        } catch (Exception ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return cartList;
    }
    
    public CartItemDTO getCartByCustomerIdAndProductId(int customerId , int productId) throws SQLException {

        // Câu lệnh SQL để lấy dữ liệu giỏ hàng của khách hàng
        String query = "SELECT c.CartID, c.CustomerID, c.CreatedAt, c.ProductID, c.Quantity, "
                + "p.name, pi.ImageURL, p.Price "
                + "FROM Carts c "
                + "JOIN Products p ON c.ProductID = p.ProductID "
                + "LEFT JOIN ProductImages pi ON c.ProductID = pi.ProductID "
                + "WHERE c.CustomerID = ? and c.ProductId = ?";

        try (PreparedStatement stmt = getConnection().prepareStatement(query)) {
            stmt.setInt(1, customerId);
            stmt.setInt(2, productId);
            ResultSet rs = stmt.executeQuery();

            // Duyệt qua các kết quả và tạo đối tượng Cart từ kết quả truy vấn
            while (rs.next()) {
                CartItemDTO cart = new CartItemDTO();
                cart.setCartId(rs.getInt("CartID"));
                cart.setCustomerId(rs.getInt("CustomerID"));
                cart.setCreatedAt(rs.getTimestamp("CreatedAt").toLocalDateTime());
                cart.setProductId(rs.getInt("ProductID"));
                cart.setQuantity(rs.getInt("Quantity"));
                cart.setProductName(rs.getString("name"));
                cart.setImage(rs.getString("ImageURL"));
                cart.setPrice(rs.getBigDecimal("Price"));
                return cart;
            }
        } catch (Exception ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }
}
