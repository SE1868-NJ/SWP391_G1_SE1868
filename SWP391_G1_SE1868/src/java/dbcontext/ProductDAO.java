package dbcontext;

import dto.ProductItemDTO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProductDAO extends GiangDBcontext {
    private final int TOP_RATED_PAGE = 1;
    private final int TOP_RATED_SIZE_PER_PAGE = 12;

    public List<ProductItemDTO> getAllProducts(int page, int pageSize, String searchKeyword) {
        List<ProductItemDTO> products = new ArrayList<>();
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT p.productId, p.name, p.price, p.stockQuantity , pi.imageURL, p.description, AVG(r.rating) AS averageRating ")
                .append("FROM Products p ")
                .append("LEFT JOIN productreviews r ON p.productId = r.productId ")
                .append("LEFT JOIN ProductImages pi ON p.productId = pi.productId ")
                .append("WHERE 1=1 ");

        // Thêm điều kiện tìm kiếm
        if (searchKeyword != null && !searchKeyword.isEmpty()) {
            sql.append("AND (p.name LIKE ? OR p.productId LIKE ? ) ");
        }

        sql.append("GROUP BY p.productId, p.name, p.price, pi.imageURL, p.description , p.stockQuantity ");

        sql.append("ORDER BY p.productId LIMIT ? OFFSET ? ");

        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql.toString())) {
            int parameterIndex = 1;

            // Gán giá trị cho các tham số truy vấn
            if (searchKeyword != null && !searchKeyword.isEmpty()) {
                ps.setString(parameterIndex++, "%" + searchKeyword + "%");
                ps.setString(parameterIndex++, "%" + searchKeyword + "%");
            }
            
            ps.setInt(parameterIndex++, pageSize);
            ps.setInt(parameterIndex++, (page - 1) * pageSize);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    ProductItemDTO product = new ProductItemDTO();
                    product.setProductId(rs.getInt("productId"));
                    product.setName(rs.getString("Name"));
                    product.setPrice(rs.getDouble("price"));
                    
                    product.setImageUrl(rs.getString("imageURL"));
                    product.setDescription(rs.getString("description"));
                    product.setAverageRating(rs.getDouble("averageRating"));
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

    public List<ProductItemDTO> getTopRatedAndDiscountedProducts() {
        List<ProductItemDTO> products = new ArrayList<>();
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT p.productId, p.name, p.price, p.stockQuantity , pi.imageURL, p.description, AVG(r.rating) AS averageRating ")
                .append("FROM Products p ")
                .append("LEFT JOIN productreviews r ON p.productId = r.productId ")
                
                .append("LEFT JOIN ProductImages pi ON p.productId = pi.productId ");

        // Thêm phần lọc đánh giá trong HAVING
        sql.append("GROUP BY p.productId, p.name, p.price, pi.imageURL, p.description , p.stockQuantity ");

        sql.append("ORDER BY averageRating DESC LIMIT ? OFFSET ? ");

        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql.toString())) {
            int parameterIndex = 1;

            ps.setInt(parameterIndex++, TOP_RATED_SIZE_PER_PAGE);
            ps.setInt(parameterIndex++, (TOP_RATED_PAGE - 1) * TOP_RATED_SIZE_PER_PAGE);
            

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    ProductItemDTO product = new ProductItemDTO();
                    product.setProductId(rs.getInt("productId"));
                    product.setName(rs.getString("Name"));
                    product.setPrice(rs.getDouble("price"));
                    product.setImageUrl(rs.getString("imageURL"));
                    product.setDescription(rs.getString("description"));
                    product.setAverageRating(rs.getDouble("averageRating"));
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

    private ProductItemDTO mapProduct(ResultSet rs) throws SQLException {
        ProductItemDTO product = new ProductItemDTO();
        product.setProductId(rs.getInt("productId"));
        product.setName(rs.getString("Name"));
        product.setCategoryId(rs.getInt("categoryId"));
        product.setPrice(rs.getDouble("price"));
        
        product.setStockQuantity(rs.getInt("stockQuantity"));
        product.setDescription(rs.getString("description"));
        product.setImageUrl(rs.getString("imageURL"));
        return product;
    }

    public List<ProductItemDTO> getProductsByCategory(int categoryId, int page, int pageSize) {
        List<ProductItemDTO> products = new ArrayList<>();
        String sql = "SELECT * FROM Products LEFT JOIN ProductImages pi on Products.productId = pi.productId WHERE categoryId = ? ORDER BY Products.productId LIMIT ? OFFSET ? ";
        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, categoryId);
            ps.setInt(3, (page - 1) * pageSize);
            ps.setInt(2, pageSize);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    ProductItemDTO product = mapProduct(rs);
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
        String query = "SELECT COUNT(*) FROM products WHERE categoryId = ?";

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

    public int countProducts(String searchKeyword) {
        int count = 0;
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT COUNT(p.productId) ")
                .append("FROM Products p ")
                
                .append("WHERE 1=1 ");

        // Thêm điều kiện tìm kiếm
        if (searchKeyword != null && !searchKeyword.isEmpty()) {
            sql.append("AND (p.name LIKE ? OR p.productId LIKE ? ) ");
        }

        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql.toString())) {

            int parameterIndex = 1;

            // Gán giá trị cho các tham số truy vấn
            if (searchKeyword != null && !searchKeyword.isEmpty()) {
                ps.setString(parameterIndex++, "%" + searchKeyword + "%");
                ps.setString(parameterIndex++, "%" + searchKeyword + "%");
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
    
    public ProductItemDTO getProductById(int productId) {
        String query = "SELECT `products`.`StockQuantity` FROM `products` WHERE productId = ?";

        try (PreparedStatement stmt = getConnection().prepareStatement(query)) {
            stmt.setInt(1, productId);
            // Thực thi câu truy vấn và lấy kết quả
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    ProductItemDTO productItemDTO = new ProductItemDTO();
                    productItemDTO.setStockQuantity(rs.getInt("StockQuantity"));
                    return productItemDTO;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

}
