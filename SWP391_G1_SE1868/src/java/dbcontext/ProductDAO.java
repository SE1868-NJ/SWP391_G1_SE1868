package dbcontext;

import dbcontext.DBContext;
import entity.Product;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProductDAO extends DBContext {

    public List<Product> getAllProducts(int page, int pageSize) {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM Products ORDER BY productId OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, (page - 1) * pageSize);
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

    public List<Product> getTopRatedAndDiscountedProducts(int page, int pageSize) {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT p.productId  , p.productName , p.image , p.description , p.price , p.discountPrice , AVG(r.rating) AS avgRating "
                + "FROM Products p LEFT JOIN Reviews r ON p.productId = r.productId "
                + "GROUP BY p.productId  , p.productName , p.image , p.description , p.price , p.discountPrice "
                + "ORDER BY avgRating DESC, p.discountPrice DESC "
                + "OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, (page - 1) * pageSize);
            ps.setInt(2, pageSize);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Product product = new Product();
                    product.setProductId(rs.getInt("productId"));
                    product.setProductName(rs.getString("productName"));
                    product.setPrice(rs.getBigDecimal("price"));
                    product.setDiscountPrice(rs.getBigDecimal("discountPrice"));
                    product.setDescription(rs.getString("description"));
                    product.setImage(rs.getString("image"));
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
}
