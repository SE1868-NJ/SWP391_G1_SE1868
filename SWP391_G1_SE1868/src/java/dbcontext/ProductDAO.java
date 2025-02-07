package dbcontext;

import entity.Cart;
import entity.Product;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProductDAO extends DBContext {

    public List<Product> getAllProducts(int page, int pageSize, String searchKeyword, Double minPrice, Double maxPrice, Double minRating, Double maxRating, String brandName, String supplierName) {
        List<Product> products = new ArrayList<>();
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT p.productId, p.productName, p.price, p.stockQuantity , p.discountPrice, p.image, p.description, AVG(r.rating) AS averageRating, b.brandName, s.supplierName ")
                .append("FROM Products p ")
                .append("LEFT JOIN Reviews r ON p.productId = r.productId ")
                .append("LEFT JOIN Brands b ON p.brandId = b.brandId ")
                .append("LEFT JOIN Suppliers s ON p.supplierId = s.supplierId ")
                .append("WHERE 1=1 ");

        // Thêm điều kiện tìm kiếm
        if (searchKeyword != null && !searchKeyword.isEmpty()) {
            sql.append("AND (p.productName LIKE ? OR p.productId LIKE ? OR b.brandName LIKE ? OR s.supplierName LIKE ?) ");
        }

        // Thêm điều kiện giá
        if (minPrice != null) {
            sql.append("AND p.price >= ? ");
        }
        if (maxPrice != null) {
            sql.append("AND p.price <= ? ");
        }

        // Thêm điều kiện lọc theo brandName và supplierName
        if (brandName != null && !brandName.isEmpty()) {
            sql.append("AND b.brandName LIKE ? ");
        }
        if (supplierName != null && !supplierName.isEmpty()) {
            sql.append("AND s.supplierName LIKE ? ");
        }

        // Thêm phần lọc đánh giá trong HAVING
        sql.append("GROUP BY p.productId, p.productName, p.price, p.discountPrice, p.image, p.description, b.brandName, s.supplierName , p.stockQuantity ");

        // Thêm điều kiện đánh giá vào HAVING
        if (minRating != null && maxRating != null) {
            sql.append("HAVING AVG(r.rating) BETWEEN ? AND ? ");
        } else if (minRating != null) {
            sql.append("HAVING AVG(r.rating) >= ? ");
        } else if (maxRating != null) {
            sql.append("HAVING AVG(r.rating) <= ? ");
        }

        sql.append("ORDER BY p.productId LIMIT ? OFFSET ? ");

        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql.toString())) {
            int parameterIndex = 1;

            // Gán giá trị cho các tham số truy vấn
            if (searchKeyword != null && !searchKeyword.isEmpty()) {
                ps.setString(parameterIndex++, "%" + searchKeyword + "%");
                ps.setString(parameterIndex++, "%" + searchKeyword + "%");
                ps.setString(parameterIndex++, "%" + searchKeyword + "%");
                ps.setString(parameterIndex++, "%" + searchKeyword + "%");
            }

            if (minPrice != null) {
                ps.setDouble(parameterIndex++, minPrice);
            }
            if (maxPrice != null) {
                ps.setDouble(parameterIndex++, maxPrice);
            }

            if (minRating != null && maxRating != null) {
                ps.setDouble(parameterIndex++, minRating);
                ps.setDouble(parameterIndex++, maxRating);
            } else if (minRating != null) {
                ps.setDouble(parameterIndex++, minRating);
            } else if (maxRating != null) {
                ps.setDouble(parameterIndex++, maxRating);
            }

            if (brandName != null && !brandName.isEmpty()) {
                ps.setString(parameterIndex++, "%" + brandName + "%");
            }

            if (supplierName != null && !supplierName.isEmpty()) {
                ps.setString(parameterIndex++, "%" + supplierName + "%");
            }
            
            ps.setInt(parameterIndex++, pageSize);
            ps.setInt(parameterIndex++, (page - 1) * pageSize);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Product product = new Product();
                    product.setProductId(rs.getInt("productId"));
                    product.setProductName(rs.getString("productName"));
                    product.setPrice(rs.getBigDecimal("price"));
                    product.setDiscountPrice(rs.getBigDecimal("discountPrice"));
                    product.setImage(rs.getString("image"));
                    product.setDescription(rs.getString("description"));
                    product.setAverageRating(rs.getDouble("averageRating"));
                    product.setBrandName(rs.getString("brandName"));
                    product.setSupplierName(rs.getString("supplierName"));
                    product.setStockQuantity(rs.getInt("stockQuantity"));
                    products.add(product);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return products;
    }

    public List<Product> getTopRatedAndDiscountedProducts(int page, int pageSize, String searchKeyword, Double minPrice, Double maxPrice, Double minRating, Double maxRating, String brandName, String supplierName) {
        List<Product> products = new ArrayList<>();
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT p.productId, p.productName, p.price, p.stockQuantity , p.discountPrice, p.image, p.description, AVG(r.rating) AS averageRating, b.brandName, s.supplierName ")
                .append("FROM Products p ")
                .append("LEFT JOIN Reviews r ON p.productId = r.productId ")
                .append("LEFT JOIN Brands b ON p.brandId = b.brandId ")
                .append("LEFT JOIN Suppliers s ON p.supplierId = s.supplierId ")
                .append("WHERE p.discountPrice > 0 ");

        // Thêm điều kiện tìm kiếm
        if (searchKeyword != null && !searchKeyword.isEmpty()) {
            sql.append("AND (p.productName LIKE ? OR p.productId LIKE ? OR b.brandName LIKE ? OR s.supplierName LIKE ?) ");
        }

        // Thêm điều kiện giá
        if (minPrice != null) {
            sql.append("AND p.price >= ? ");
        }
        if (maxPrice != null) {
            sql.append("AND p.price <= ? ");
        }

        // Thêm điều kiện lọc theo brandName và supplierName
        if (brandName != null && !brandName.isEmpty()) {
            sql.append("AND b.brandName LIKE ? ");
        }
        if (supplierName != null && !supplierName.isEmpty()) {
            sql.append("AND s.supplierName LIKE ? ");
        }

        // Thêm phần lọc đánh giá trong HAVING
        sql.append("GROUP BY p.productId, p.productName, p.price, p.discountPrice, p.image, p.description, b.brandName, s.supplierName , p.stockQuantity ");

        // Thêm điều kiện đánh giá vào HAVING
        if (minRating != null && maxRating != null) {
            sql.append("HAVING AVG(r.rating) BETWEEN ? AND ? ");
        } else if (minRating != null) {
            sql.append("HAVING AVG(r.rating) >= ? ");
        } else if (maxRating != null) {
            sql.append("HAVING AVG(r.rating) <= ? ");
        }

        sql.append("ORDER BY averageRating DESC, p.discountPrice DESC LIMIT ? OFFSET ? ");

        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql.toString())) {
            int parameterIndex = 1;

            // Gán giá trị cho các tham số truy vấn
            if (searchKeyword != null && !searchKeyword.isEmpty()) {
                ps.setString(parameterIndex++, "%" + searchKeyword + "%");
                ps.setString(parameterIndex++, "%" + searchKeyword + "%");
                ps.setString(parameterIndex++, "%" + searchKeyword + "%");
                ps.setString(parameterIndex++, "%" + searchKeyword + "%");
            }

            if (minPrice != null) {
                ps.setDouble(parameterIndex++, minPrice);
            }
            if (maxPrice != null) {
                ps.setDouble(parameterIndex++, maxPrice);
            }

            if (minRating != null && maxRating != null) {
                ps.setDouble(parameterIndex++, minRating);
                ps.setDouble(parameterIndex++, maxRating);
            } else if (minRating != null) {
                ps.setDouble(parameterIndex++, minRating);
            } else if (maxRating != null) {
                ps.setDouble(parameterIndex++, maxRating);
            }

            if (brandName != null && !brandName.isEmpty()) {
                ps.setString(parameterIndex++, "%" + brandName + "%");
            }

            if (supplierName != null && !supplierName.isEmpty()) {
                ps.setString(parameterIndex++, "%" + supplierName + "%");
            }
            
            ps.setInt(parameterIndex++, pageSize);
            ps.setInt(parameterIndex++, (page - 1) * pageSize);
            

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Product product = new Product();
                    product.setProductId(rs.getInt("productId"));
                    product.setProductName(rs.getString("productName"));
                    product.setPrice(rs.getBigDecimal("price"));
                    product.setDiscountPrice(rs.getBigDecimal("discountPrice"));
                    product.setImage(rs.getString("image"));
                    product.setDescription(rs.getString("description"));
                    product.setAverageRating(rs.getDouble("averageRating"));
                    product.setBrandName(rs.getString("brandName"));
                    product.setStockQuantity(rs.getInt("stockQuantity"));
                    product.setSupplierName(rs.getString("supplierName"));
                    products.add(product);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return products;
    }

    private Product mapProduct(ResultSet rs) throws SQLException {
        Product product = new Product();
        product.setProductId(rs.getInt("productId"));
        product.setProductName(rs.getString("productName"));
        product.setCategoryId(rs.getInt("categoryId"));
        product.setSupplierId(rs.getInt("supplierId"));
        product.setBrandId(rs.getInt("brandId"));
        product.setPrice(rs.getBigDecimal("price"));
        product.setDiscountPrice(rs.getBigDecimal("discountPrice"));
        product.setStockQuantity(rs.getInt("stockQuantity"));
        product.setDescription(rs.getString("description"));
        product.setImage(rs.getString("image"));
        return product;
    }

    public List<Product> getProductsByCategory(int categoryId, int page, int pageSize) {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM Products WHERE categoryId = ? ORDER BY productId LIMIT ? OFFSET ? ";
        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, categoryId);
            ps.setInt(3, (page - 1) * pageSize);
            ps.setInt(2, pageSize);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Product product = mapProduct(rs);
                    products.add(product);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return products;
    }

    public int countProductsByCategory(int categoryId) {
        int count = 0;
        String query = "SELECT COUNT(*) FROM products WHERE category_id = ?";

        try (PreparedStatement stmt = getConnection().prepareStatement(query)) {
            stmt.setInt(1, categoryId);
            // Thực thi câu truy vấn và lấy kết quả
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    count = rs.getInt(1); // Lấy giá trị đếm được từ kết quả truy vấn
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return count;
    }

    public int countProducts(String searchKeyword, Double minPrice, Double maxPrice, Double minRating, Double maxRating, String brandName, String supplierName) {
        int count = 0;
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT COUNT(p.productId) ")
                .append("FROM Products p ")
                .append("LEFT JOIN Reviews r ON p.productId = r.productId ")
                .append("LEFT JOIN Brands b ON p.brandId = b.brandId ")
                .append("LEFT JOIN Suppliers s ON p.supplierId = s.supplierId ")
                .append("WHERE 1=1 ");

        // Thêm điều kiện tìm kiếm
        if (searchKeyword != null && !searchKeyword.isEmpty()) {
            sql.append("AND (p.productName LIKE ? OR p.productId LIKE ? OR b.brandName LIKE ? OR s.supplierName LIKE ?) ");
        }

        // Thêm điều kiện giá
        if (minPrice != null) {
            sql.append("AND p.price >= ? ");
        }
        if (maxPrice != null) {
            sql.append("AND p.price <= ? ");
        }

        // Thêm điều kiện lọc theo brandName và supplierName
        if (brandName != null && !brandName.isEmpty()) {
            sql.append("AND b.brandName LIKE ? ");
        }
        if (supplierName != null && !supplierName.isEmpty()) {
            sql.append("AND s.supplierName LIKE ? ");
        }

        // Thêm điều kiện HAVING cho đánh giá
        if (minRating != null || maxRating != null) {
            if (minRating != null) {
                sql.append("AND (SELECT AVG(r.Rating) FROM Reviews r WHERE r.ProductID = p.ProductID ) >= ? ");
            }
            if (maxRating != null) {
                sql.append("AND (SELECT AVG(r.Rating) FROM Reviews r WHERE r.ProductID = p.ProductID ) <= ? ");
            }
        }

        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql.toString())) {

            int parameterIndex = 1;

            // Gán giá trị cho các tham số truy vấn
            if (searchKeyword != null && !searchKeyword.isEmpty()) {
                ps.setString(parameterIndex++, "%" + searchKeyword + "%");
                ps.setString(parameterIndex++, "%" + searchKeyword + "%");
                ps.setString(parameterIndex++, "%" + searchKeyword + "%");
                ps.setString(parameterIndex++, "%" + searchKeyword + "%");
            }

            if (minPrice != null) {
                ps.setDouble(parameterIndex++, minPrice);
            }
            if (maxPrice != null) {
                ps.setDouble(parameterIndex++, maxPrice);
            }

            if (brandName != null && !brandName.isEmpty()) {
                ps.setString(parameterIndex++, "%" + brandName + "%");
            }

            if (supplierName != null && !supplierName.isEmpty()) {
                ps.setString(parameterIndex++, "%" + supplierName + "%");
            }

            // Gán giá trị cho điều kiện minRating và maxRating nếu có
            if (minRating != null) {
                ps.setDouble(parameterIndex++, minRating);
            }
            if (maxRating != null) {
                ps.setDouble(parameterIndex++, maxRating);
            }

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    count = rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return count;
    }

    public Product getProductById(int productId) {
        Product product = null;
        String sql = "SELECT p.productId, p.productName, p.price, p.discountPrice, p.stockQuantity, p.description, p.image, "
                + "AVG(r.rating) AS averageRating "
                + "FROM Products p "
                + "LEFT JOIN Reviews r ON p.productId = r.productId "
                + "WHERE p.productId = ? "
                + "GROUP BY p.productId , p.productName, p.price, p.discountPrice, p.stockQuantity, p.description, p.image";

        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, productId);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    product = new Product();
                    product.setProductId(rs.getInt("productId"));
                    product.setProductName(rs.getString("productName"));
                    product.setPrice(rs.getBigDecimal("price"));
                    product.setDiscountPrice(rs.getBigDecimal("discountPrice"));
                    product.setStockQuantity(rs.getInt("stockQuantity"));
                    product.setDescription(rs.getString("description"));
                    product.setImage(rs.getString("image"));
                    product.setAverageRating(rs.getDouble("averageRating"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return product;
    }

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

    public List<Cart> getCartByCustomerId(int customerId) throws SQLException {
        List<Cart> cartList = new ArrayList<>();

        // Câu lệnh SQL để lấy dữ liệu giỏ hàng của khách hàng
        String query = "SELECT c.CartID, c.CustomerID, c.CreatedDate, c.ProductID, c.Quantity, "
                + "p.ProductName, p.Image, p.DiscountPrice, p.Price "
                + "FROM Carts c "
                + "JOIN Products p ON c.ProductID = p.ProductID "
                + "WHERE c.CustomerID = ?";

        try (PreparedStatement stmt = getConnection().prepareStatement(query)) {
            stmt.setInt(1, customerId);
            ResultSet rs = stmt.executeQuery();

            // Duyệt qua các kết quả và tạo đối tượng Cart từ kết quả truy vấn
            while (rs.next()) {
                Cart cart = new Cart();
                cart.setCartId(rs.getInt("CartID"));
                cart.setCustomerId(rs.getInt("CustomerID"));
                cart.setCreatedDate(rs.getTimestamp("CreatedDate").toLocalDateTime());
                cart.setProductId(rs.getInt("ProductID"));
                cart.setQuantity(rs.getInt("Quantity"));
                cart.setProductName(rs.getString("ProductName"));
                cart.setImage(rs.getString("Image"));
                cart.setDiscountPrice(rs.getBigDecimal("DiscountPrice"));
                cart.setPrice(rs.getBigDecimal("Price"));

                cartList.add(cart);
            }
        } catch (Exception ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return cartList;
    }

}
