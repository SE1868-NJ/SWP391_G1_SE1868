/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import dbcontext.DBContext;
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
import java.sql.Date;

/**
 *
 * @author Đạt
 */
public class ProductReviewDAO extends DBContext {

    public boolean addReview(ProductReview review) {
        String sql = "INSERT INTO `swp391_g1`.`productreviews` "
                + "(`ProductID`, `CustomerID`, `Rating`, `Comment`, `CreatedAt`, `ProductReviewsImage`) "
                + "VALUES (?, ?, ?, ?, ?, ?)";  // Thêm cột `ProductReviewsImage`

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            // Set các tham số cho PreparedStatement từ đối tượng ProductReview
            stmt.setInt(1, review.getProduct().getProductId());  // Lấy productId từ đối tượng Product
            stmt.setInt(2, review.getCustomer().getCustomerId());  // Lấy customerId từ đối tượng Customer
            stmt.setInt(3, review.getRating());  // Lấy Rating
            stmt.setString(4, review.getComment());  // Lấy Comment
            stmt.setDate(5, Date.valueOf(review.getCreatedAt()));  // Chuyển LocalDate thành java.sql.Date

            // Set giá trị cho trường `ProductReviewsImage`
            stmt.setString(6, review.getProductReviewsImage());  // Lấy đường dẫn hình ảnh (có thể là null)

            // Thực thi câu lệnh SQL
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateReview(ProductReview review) {
        String sql = "UPDATE swp391_g1.productreviews SET Rating = ?, Comment = ? , ProductReviewsImage = ? WHERE ReviewID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, review.getRating());
            stmt.setString(2, review.getComment());
            stmt.setString(3, review.getProductReviewsImage());
            stmt.setInt(4, review.getReviewId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public ProductReview getProductReviewById(int reviewId) {
        String sql = "SELECT `ProductID`, `CustomerID`, `Rating`, `Comment`, `CreatedAt` "
                + "FROM `swp391_g1`.`productreviews` "
                + "WHERE `ReviewID` = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, reviewId);

            try (ResultSet rs = stmt.executeQuery()) {
                // instane dao
                ProductDAO productDAO = new ProductDAO();
                CustomerDAO customerDAO = new CustomerDAO();

                if (rs.next()) {
                    // Lấy dữ liệu từ ResultSet và tạo đối tượng ProductReview
                    ProductReview review = new ProductReview();

                    // lấy obj product
                    review.setProduct(productDAO.getProductById(rs.getInt("ProductID")));

                    // lấy obj Customer
                    review.setCustomer(customerDAO.getCustomerById(rs.getInt("CustomerID")));

                    review.setRating(rs.getInt("Rating"));
                    review.setComment(rs.getString("Comment"));
                    review.setCreatedAt(rs.getDate("CreatedAt").toLocalDate());

                    
                    return review;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // Trả về null nếu không tìm thấy đánh giá
    }

    public boolean deleteReview(int reviewId) {
        String sql = "DELETE FROM swp391_g1.productreviews WHERE ReviewID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, reviewId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Hàm lấy tổng số đánh giá của sản phẩm
    public int getTotalReviewsByProduct(int productId, int starFilter) {
        int totalReviews = 0;
        String sql = "SELECT COUNT(*) FROM productreviews WHERE ProductID = ?";

        if (starFilter > 0) {
            sql += " AND Rating = ?";
        }

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, productId);
            if (starFilter > 0) {
                stmt.setInt(2, starFilter);
            }

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                totalReviews = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return totalReviews;
    }

    //  Tính trung bình số sao
    public double getAverageRating(int productId) {
        String sql = "SELECT AVG(rating) FROM swp391_g1.productreviews WHERE ProductID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, productId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getDouble(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0.0;
    }

    //  Lấy danh sách đánh giá có phân trang và lọc theo số sao
    public List<ProductReview> getReviewsByProduct(int productId, int page, int pageSize, int starFilter) {
        List<ProductReview> reviews = new ArrayList<>();
        String sql = "SELECT * FROM swp391_g1.productreviews WHERE ProductID = ? ";

        if (starFilter > 0) {
            sql += " AND Rating = ? ";
        }

        sql += " ORDER BY CreatedAt DESC LIMIT ? OFFSET ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, productId);
            int paramIndex = 2;

            if (starFilter > 0) {
                stmt.setInt(paramIndex++, starFilter);
            }

            stmt.setInt(paramIndex++, pageSize);
            stmt.setInt(paramIndex, (page - 1) * pageSize);

            ResultSet rs = stmt.executeQuery();
            ProductDAO productDAO = new ProductDAO();
            CustomerDAO customerDAO = new CustomerDAO();
            while (rs.next()) {
                ProductReview review = new ProductReview();
                review.setReviewId(rs.getInt("ReviewID"));

                // review.setProduct(new Product(rs.getInt("ProductID")));
                Product product = productDAO.getProductById(rs.getInt("ProductID"));
                review.setProduct(product);

                // review.setCustomer(new Customer(rs.getInt("CustomerID")));
                Customer customer = customerDAO.getCustomerById(rs.getInt("CustomerID"));
                review.setCustomer(customer);

                review.setRating(rs.getInt("Rating"));
                review.setComment(rs.getString("Comment"));
                review.setCreatedAt(rs.getDate("CreatedAt").toLocalDate());
                
                
                
                reviews.add(review);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reviews;
    }

    public static void main(String[] args) {
        ProductReviewDAO productReviewDAO = new ProductReviewDAO();

        ProductReview productReview = productReviewDAO.getProductReviewById(1);

        System.out.println(productReview);

    }
}
