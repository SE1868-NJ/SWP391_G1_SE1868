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

/**
 *
 * @author Đạt
 */
public class ProductReviewDAO extends DBContext {

    public boolean addReview(ProductReview review) {
        String sql = "INSERT INTO `swp391_g1`.`productreviews`\n"
                + "(`ProductID`,\n"
                + "`CustomerID`,\n"
                + "`Rating`,\n"
                + "`Comment`,\n"
                + "`CreatedAt`)"
                + " VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, review.getProduct().getProductId());
            stmt.setInt(2, review.getCustomer().getCustomerId());
            stmt.setInt(3, review.getRating());
            stmt.setString(4, review.getComment());
            stmt.setTimestamp(5, Timestamp.valueOf(review.getCreatedAt()));

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateReview(ProductReview review) {
        String sql = "UPDATE swp391_g1.productreviews SET Rating = ?, Comment = ? WHERE ReviewID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, review.getRating());
            stmt.setString(2, review.getComment());
            stmt.setInt(3, review.getReviewId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
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

        if ( starFilter > 0) {
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

            if ( starFilter > 0) {
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
                review.setCreatedAt(rs.getTimestamp("CreatedAt").toLocalDateTime());
                reviews.add(review);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reviews;
    }

    public static void main(String[] args) {
        ProductReviewDAO productReviewDAO = new ProductReviewDAO();

        List<ProductReview> productReview = productReviewDAO.getReviewsByProduct(1, 1, 10, 0);

        for (ProductReview productReview1 : productReview) {
            System.out.println(productReview);
        }

    }
}
