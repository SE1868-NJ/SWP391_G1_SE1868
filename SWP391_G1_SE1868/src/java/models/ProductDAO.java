/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import dbcontext.DBContext;
import entity.Cart;
import entity.Category;
import entity.Customer;
import entity.Product;
import entity.ProductImage;
import entity.ProductReview;
import entity.Shop;
import java.sql.PreparedStatement;
import java.sql.ResultSet;   // tap ban ghi 
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

/**
 *
 * @author Đạt
 */
public class ProductDAO extends DBContext {

    // Thêm sản phẩm
    public boolean addProduct(Product product) {
        String sql = "INSERT INTO Products (name, description, price, stockQuantity, categoryId, shopId) "
                + "VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, product.getName());
            stmt.setString(2, product.getDescription());
            stmt.setDouble(3, product.getPrice());
            stmt.setInt(4, product.getStockQuantity());

            // Kiểm tra categoryId (nếu category không phải null)
            stmt.setInt(5, product.getCategory() != null ? product.getCategory().getCategoryId() : null);

            // Kiểm tra shopId (nếu shop không phải null)
            stmt.setInt(6, product.getCategory() != null ? product.getShop().getShopId() : null);

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                // Lấy productId được sinh tự động (AUTO_INCREMENT)
                ResultSet generatedKeys = stmt.getGeneratedKeys();
                if (generatedKeys.next()) {
                    product.setProductId(generatedKeys.getInt(1));  // Lưu productId vào đối tượng
                }
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
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
                //Goi CatehoryDao
                CategoryDAO categoryDAO = new CategoryDAO();

                // Gọi shopDAO
                ShopDAO shopDAO = new ShopDAO();

                if (rs.next()) {
                    Product product = new Product();

                    product.setProductId(rs.getInt("productId"));
                    product.setName(rs.getString("name"));
                    product.setDescription(rs.getString("description"));
                    product.setPrice(rs.getDouble("price"));
                    product.setStockQuantity(rs.getInt("stockQuantity"));
                    product.setCreatedAt(rs.getDate("createdAt").toLocalDate());
                    product.setUpdatedAt(rs.getDate("updatedAt").toLocalDate());

                    // Lấy thông tin Category
                    Category category = categoryDAO.getCategoryById(rs.getInt("categoryId"));
                    product.setCategory(category);

                    // Lấy thong tin shop
                    Shop shop = shopDAO.getShopById(rs.getInt("shopId"));
                    product.setShop(shop);

                    // Lấy danh sách hình ảnh sản phẩm
                    try (PreparedStatement stmtImages = connection.prepareStatement(sqlImages)) {
                        stmtImages.setInt(1, productId);
                        try (ResultSet rsImages = stmtImages.executeQuery()) {
                            List<ProductImage> images = new ArrayList<>();
                            while (rsImages.next()) {
                                ProductImage productImage = new ProductImage();
                                productImage.setProductImageId(rsImages.getInt("productImageId"));
                                productImage.setImageUrl(rsImages.getString("imageUrl"));
                                productImage.setCreatedAt(rsImages.getDate("CreatedAt").toLocalDate());

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

    public Product getProductByIdNoJoin(int productId) {
        String sql = "SELECT * FROM Products WHERE productId = ?";
        String sqlImages = "SELECT * FROM ProductImages WHERE productId = ?";  // Truy vấn để lấy danh sách hình ảnh

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, productId);
            try (ResultSet rs = stmt.executeQuery()) {
                // khai bao dao Categoty
                CategoryDAO categoryDAO = new CategoryDAO();

                if (rs.next()) {
                    Product product = new Product();

                    product.setProductId(rs.getInt("productId"));
                    product.setName(rs.getString("name"));
                    product.setDescription(rs.getString("description"));
                    product.setPrice(rs.getDouble("price"));
                    product.setStockQuantity(rs.getInt("stockQuantity"));
                    product.setCreatedAt(rs.getDate("createdAt").toLocalDate());
                    product.setUpdatedAt(rs.getDate("updatedAt").toLocalDate());
                    product.setCategory(categoryDAO.getCategoryById(rs.getInt("categoryId")));

                    // Lấy danh sách hình ảnh sản phẩm
                    try (PreparedStatement stmtImages = connection.prepareStatement(sqlImages)) {
                        stmtImages.setInt(1, productId);
                        try (ResultSet rsImages = stmtImages.executeQuery()) {
                            List<ProductImage> images = new ArrayList<>();
                            while (rsImages.next()) {
                                ProductImage productImage = new ProductImage();
                                productImage.setProductImageId(rsImages.getInt("productImageId"));
                                productImage.setImageUrl(rsImages.getString("imageUrl"));
                                productImage.setCreatedAt(rsImages.getDate("CreatedAt").toLocalDate());

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

// . Cập nhật sản phẩm
    public boolean updateProduct(Product product) {
        String sql = "UPDATE Products SET name = ?, description = ?, price = ?, stockQuantity = ?,  categoryId = ?, shopId = ? WHERE productId = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, product.getName());
            stmt.setString(2, product.getDescription());
            stmt.setDouble(3, product.getPrice());
            stmt.setInt(4, product.getStockQuantity());
            stmt.setObject(5, product.getCategory() != null ? product.getCategory().getCategoryId() : null);
            stmt.setObject(6, product.getCategory() != null ? product.getCategory().getCategoryId() : null);
            stmt.setInt(7, product.getProductId());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Product> listProducts(String keyword, Double minPrice, Double maxPrice, Integer categoryId, String sortBy, String order, int page, int pageSize) {
        List<Product> productList = new ArrayList<>();

        // Truy vấn SQL động
        StringBuilder sql = new StringBuilder("SELECT p.*, COALESCE(AVG(r.rating), 0) AS avgRating FROM Products p ");
        sql.append("LEFT JOIN ProductReviews r ON p.productId = r.productId "); // Join để tính trung bình rating
        sql.append("WHERE 1=1 "); // Điều kiện luôn đúng để dễ dàng thêm điều kiện khác

        // Tìm kiếm theo tên
        if (keyword != null && !keyword.trim().isEmpty()) {
            sql.append("AND p.name LIKE ? ");
        }

        // Lọc theo khoảng giá
        if (minPrice != null) {
            sql.append("AND p.price >= ? ");
        }
        if (maxPrice != null) {
            sql.append("AND p.price <= ? ");
        }

        // Lọc theo danh mục
        if (categoryId != null) {
            sql.append("AND p.categoryId = ? ");
        }

        sql.append("GROUP BY p.productId "); // Nhóm theo sản phẩm để tính avgRating

        // Xử lý sắp xếp
        if (sortBy != null) {
            switch (sortBy) {
                case "price":
                    sql.append("ORDER BY p.price ");
                    break;
                case "rating":
                    sql.append("ORDER BY avgRating ");
                    break;
                default:
                    sql.append("ORDER BY p.productId "); // Mặc định sắp xếp theo ID
            }
            // Thêm kiểu sắp xếp (ASC/DESC)
            if ("desc".equalsIgnoreCase(order)) {
                sql.append("DESC ");
            } else {
                sql.append("ASC ");
            }
        }

        // **Phân trang**
        sql.append("LIMIT ? OFFSET ?");

        try (PreparedStatement stmt = connection.prepareStatement(sql.toString())) {
            int paramIndex = 1;

            // Nếu có từ khóa tìm kiếm
            if (keyword != null && !keyword.trim().isEmpty()) {
                stmt.setString(paramIndex++, "%" + keyword.trim() + "%");
            }

            // Nếu có khoảng giá
            if (minPrice != null) {
                stmt.setDouble(paramIndex++, minPrice);
            }
            if (maxPrice != null) {
                stmt.setDouble(paramIndex++, maxPrice);
            }

            // Nếu có danh mục
            if (categoryId != null) {
                stmt.setInt(paramIndex++, categoryId);
            }

            // **Gán giá trị LIMIT và OFFSET cho phân trang**
            stmt.setInt(paramIndex++, pageSize); // Số sản phẩm mỗi trang
            stmt.setInt(paramIndex++, (page - 1) * pageSize); // Bỏ qua (page-1) * pageSize sản phẩm trước đó

            try (ResultSet rs = stmt.executeQuery()) {
                CategoryDAO categoryDAO = new CategoryDAO();
                ShopDAO shopDAO = new ShopDAO();

                while (rs.next()) {
                    Product product = new Product();
                    product.setProductId(rs.getInt("productId"));
                    product.setName(rs.getString("name"));
                    product.setDescription(rs.getString("description"));
                    product.setPrice(rs.getDouble("price"));
                    product.setStockQuantity(rs.getInt("stockQuantity"));
                    product.setCreatedAt(rs.getDate("createdAt").toLocalDate());
                    product.setUpdatedAt(rs.getDate("updatedAt").toLocalDate());

                    // Lấy thông tin danh mục sản phẩm
                    Category category = categoryDAO.getCategoryById(rs.getInt("categoryId"));
                    product.setCategory(category);

                    // Lấy thông tin cửa hàng
                    Shop shop = shopDAO.getShopById(rs.getInt("shopId"));
                    product.setShop(shop);

                    // Lấy danh sách hình ảnh sản phẩm
                    String sqlImages = "SELECT * FROM ProductImages WHERE productId = ?";
                    try (PreparedStatement stmtImages = connection.prepareStatement(sqlImages)) {
                        stmtImages.setInt(1, product.getProductId());
                        try (ResultSet rsImages = stmtImages.executeQuery()) {
                            List<ProductImage> images = new ArrayList<>();
                            while (rsImages.next()) {
                                ProductImage productImage = new ProductImage();
                                productImage.setProductImageId(rsImages.getInt("productImageId"));
                                productImage.setImageUrl(rsImages.getString("imageUrl"));
                                productImage.setCreatedAt(rsImages.getDate("CreatedAt").toLocalDate());
                                productImage.setProduct(product);
                                images.add(productImage);
                            }
                            product.setImages(images);
                        }
                    }

                    // Lấy đánh giá trung bình (nếu có)
                    double avgRating = rs.getDouble("avgRating");
                    product.setAvgRating(avgRating);

                    productList.add(product);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return productList;
    }
    // lấy tổng số lượng bản gi
    public int getTotalProducts(String keyword, Double minPrice, Double maxPrice, Integer categoryId) {
        int totalProducts = 0;

        // Tạo truy vấn SQL động
        StringBuilder sql = new StringBuilder("SELECT COUNT(*) FROM Products WHERE 1=1 ");

        // Nếu có từ khóa tìm kiếm
        if (keyword != null && !keyword.trim().isEmpty()) {
            sql.append("AND name LIKE ? ");
        }

        // Nếu có lọc theo khoảng giá
        if (minPrice != null) {
            sql.append("AND price >= ? ");
        }
        if (maxPrice != null) {
            sql.append("AND price <= ? ");
        }

        // Nếu có lọc theo danh mục
        if (categoryId != null) {
            sql.append("AND categoryId = ? ");
        }

        try (PreparedStatement stmt = connection.prepareStatement(sql.toString())) {
            int paramIndex = 1;

            // Gán tham số tìm kiếm
            if (keyword != null && !keyword.trim().isEmpty()) {
                stmt.setString(paramIndex++, "%" + keyword.trim() + "%");
            }

            // Gán tham số lọc giá
            if (minPrice != null) {
                stmt.setDouble(paramIndex++, minPrice);
            }
            if (maxPrice != null) {
                stmt.setDouble(paramIndex++, maxPrice);
            }

            // Gán tham số danh mục
            if (categoryId != null) {
                stmt.setInt(paramIndex++, categoryId);
            }

            // Thực thi truy vấn
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    totalProducts = rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return totalProducts;
    }
    
    // // lấy tổng số trang 
    public int getTotalPages(String keyword, Double minPrice, Double maxPrice, Integer categoryId, int pageSize) {
        int totalProducts = getTotalProducts(keyword, minPrice, maxPrice, categoryId);

        // Tính tổng số trang
        return (int) Math.ceil((double) totalProducts / pageSize);
    }

    // lấy list product có đánh giá cao 
    public List<Product> getTopRatedProducts(int limit) {
        String sql = "SELECT p.*, AVG(r.rating) as avgRating, COUNT(r.reviewId) as reviewCount "
                + "FROM Products p "
                + "JOIN ProductReviews r ON p.productId = r.productId "
                + "GROUP BY p.productId "
                + "HAVING COUNT(r.reviewId) > 0 "
                + // Chỉ lấy sản phẩm có ít nhất 1 đánh giá
                "ORDER BY avgRating DESC, reviewCount DESC "
                + // Ưu tiên điểm cao, nếu bằng nhau thì ưu tiên nhiều đánh giá hơn
                "LIMIT ?";

        String sqlImages = "SELECT * FROM ProductImages WHERE productId = ?";  // Truy vấn lấy danh sách hình ảnh

        List<Product> products = new ArrayList<>();

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, limit);
            try (ResultSet rs = stmt.executeQuery()) {
                // Khởi tạo DAO một lần để tránh tốn tài nguyên
                CategoryDAO categoryDAO = new CategoryDAO();
                ShopDAO shopDAO = new ShopDAO();

                while (rs.next()) {
                    Product product = new Product();
                    int productId = rs.getInt("productId"); // Lấy ID sản phẩm

                    product.setProductId(productId);
                    product.setName(rs.getString("name"));
                    product.setDescription(rs.getString("description"));
                    product.setPrice(rs.getDouble("price"));
                    product.setStockQuantity(rs.getInt("stockQuantity"));
                    product.setCreatedAt(rs.getDate("createdAt").toLocalDate());
                    product.setUpdatedAt(rs.getDate("updatedAt").toLocalDate());

                    // Lưu giá trị trung bình đánh giá
                    product.setAvgRating(rs.getDouble("avgRating")); // Gán avgRating vào product

                    // Lấy danh sách hình ảnh của sản phẩm
                    List<ProductImage> images = new ArrayList<>();
                    try (PreparedStatement stmtImages = connection.prepareStatement(sqlImages)) {
                        stmtImages.setInt(1, productId);
                        try (ResultSet rsImages = stmtImages.executeQuery()) {
                            while (rsImages.next()) {
                                ProductImage productImage = new ProductImage();
                                productImage.setProductImageId(rsImages.getInt("productImageId"));
                                productImage.setImageUrl(rsImages.getString("imageUrl"));
                                productImage.setCreatedAt(rsImages.getDate("CreatedAt").toLocalDate());
                                images.add(productImage);
                            }
                        }
                    }
                    product.setImages(images);

                    // Lấy thông tin danh mục
                    Category category = categoryDAO.getCategoryById(rs.getInt("categoryId"));
                    product.setCategory(category);

                    // Lấy thông tin shop
                    Shop shop = shopDAO.getShopById(rs.getInt("shopId"));
                    product.setShop(shop);

                    // Thêm vào danh sách sản phẩm
                    products.add(product);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return products;
    }

    public static void main(String[] args) {

        ProductDAO DAO = new ProductDAO();

        List<Product> product = DAO.listProducts("   com ", 10000d, 100000d, null, "price", "ASC", 1, 111);

        for (Product product1 : product) {
            System.out.println(product1);
        }
    }

}
