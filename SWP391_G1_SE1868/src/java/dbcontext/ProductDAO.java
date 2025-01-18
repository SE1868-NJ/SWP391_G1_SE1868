package dbcontext;

import dbcontext.DBContext;
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
        sql.append("SELECT p.productId, p.productName, p.price, p.discountPrice, p.image, p.description, AVG(r.rating) AS averageRating, b.brandName, s.supplierName ")
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
        sql.append("GROUP BY p.productId, p.productName, p.price, p.discountPrice, p.image, p.description, b.brandName, s.supplierName ");

        // Thêm điều kiện đánh giá vào HAVING
        if (minRating != null && maxRating != null) {
            sql.append("HAVING AVG(r.rating) BETWEEN ? AND ? ");
        } else if (minRating != null) {
            sql.append("HAVING AVG(r.rating) >= ? ");
        } else if (maxRating != null) {
            sql.append("HAVING AVG(r.rating) <= ? ");
        }

        sql.append("ORDER BY p.productId OFFSET ? ROWS FETCH NEXT ? ROWS ONLY");

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

            ps.setInt(parameterIndex++, (page - 1) * pageSize);
            ps.setInt(parameterIndex++, pageSize);

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
        sql.append("SELECT p.productId, p.productName, p.price, p.discountPrice, p.image, p.description, AVG(r.rating) AS averageRating, b.brandName, s.supplierName ")
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
        sql.append("GROUP BY p.productId, p.productName, p.price, p.discountPrice, p.image, p.description, b.brandName, s.supplierName ");

        // Thêm điều kiện đánh giá vào HAVING
        if (minRating != null && maxRating != null) {
            sql.append("HAVING AVG(r.rating) BETWEEN ? AND ? ");
        } else if (minRating != null) {
            sql.append("HAVING AVG(r.rating) >= ? ");
        } else if (maxRating != null) {
            sql.append("HAVING AVG(r.rating) <= ? ");
        }

        sql.append("ORDER BY averageRating DESC, p.discountPrice DESC OFFSET ? ROWS FETCH NEXT ? ROWS ONLY");

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

            ps.setInt(parameterIndex++, (page - 1) * pageSize);
            ps.setInt(parameterIndex++, pageSize);

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
        String sql = "SELECT * FROM Products WHERE categoryId = ? ORDER BY productId OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, categoryId);
            ps.setInt(2, (page - 1) * pageSize);
            ps.setInt(3, pageSize);
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

}
