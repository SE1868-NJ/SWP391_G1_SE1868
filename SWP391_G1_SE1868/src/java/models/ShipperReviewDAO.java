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
import entity.Shipper;
import entity.ShipperReview;
import java.sql.PreparedStatement;
import java.sql.ResultSet;   // tap ban ghi 
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.SQLException;

public class ShipperReviewDAO extends DBContext {

    // lấy lisyt đánh giá của shipepr
    public List<ShipperReview> getReviewsByShipperIdWithPagination(int shipperId, int rating, int page, int pageSize) {
        List<ShipperReview> reviews = new ArrayList<>();

        // Xây dựng câu SQL với điều kiện lọc theo rating nếu có
        String sql = "SELECT * FROM ShipperReviews WHERE shipperId = ?";

        // Nếu rating > 0, thêm điều kiện lọc theo số sao
        if (rating > 0) {
            sql += " AND rating = ?";
        }

        sql += " ORDER BY createdAt DESC LIMIT ?, ?"; // Sắp xếp mới nhất + phân trang

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            int paramIndex = 1;
            stmt.setInt(paramIndex++, shipperId); // Gán shipperId

            // Nếu rating > 0, thêm vào điều kiện truy vấn
            if (rating > 0) {
                stmt.setInt(paramIndex++, rating);
            }

            // Tính toán offset cho phân trang
            int offset = (page - 1) * pageSize;
            stmt.setInt(paramIndex++, offset);
            stmt.setInt(paramIndex++, pageSize);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                ShipperReview review = new ShipperReview();
                review.setReviewId(rs.getInt("reviewId"));
                review.setCustomer(getCustomerByIdForShipperReviews(rs.getInt("customerId")));
                review.setShipper(getShipperById(rs.getInt("shipperId")));
                review.setRating(rs.getInt("rating"));
                review.setComment(rs.getString("comment"));
                review.setCreatedAt(rs.getDate("createdAt").toLocalDate());
                review.setUpdatedAt(rs.getDate("updatedAt").toLocalDate());

                reviews.add(review);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return reviews;
    }
    // Lấy review theo reviewId

    public ShipperReview getReviewById(int reviewId) {
        ShipperReview review = null;
        String sql = "SELECT * FROM ShipperReviews WHERE reviewId = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, reviewId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                review = new ShipperReview();
                review.setReviewId(rs.getInt("reviewId"));
                review.setCustomer(getCustomerByIdForShipperReviews(rs.getInt("customerId")));
                review.setShipper(getShipperById(rs.getInt("shipperId")));
                review.setRating(rs.getInt("rating"));
                review.setComment(rs.getString("comment"));
                review.setCreatedAt(rs.getDate("createdAt").toLocalDate());
                review.setUpdatedAt(rs.getDate("updatedAt").toLocalDate());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return review;
    }

    // Cập nhật rating và comment của ShipperReview theo reviewId
    public boolean updateShipperReview(ShipperReview review) {
        String sql = "UPDATE ShipperReviews SET rating = ?, comment = ?, updatedAt = NOW() WHERE reviewId = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, review.getRating());
            stmt.setString(2, review.getComment());
            stmt.setInt(3, review.getReviewId());

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0; // Trả về true nếu cập nhật thành công
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false; // Trả về false nếu có lỗi
    }

    // lấy tổng số đánh giá của shipper theo ID
    public int getTotalReviewsByShipperId(int shipperId) {
        int totalReviews = 0;

        String sql = "SELECT COUNT(*) FROM ShipperReviews WHERE shipperId = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, shipperId); // Gán giá trị shipperId
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                totalReviews = rs.getInt(1); // Lấy giá trị COUNT(*)
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return totalReviews; // Trả về tổng số đánh giá
    }

    //  trung bình rating của shipper theo ID
    public double getAverageRatingByShipperId(int shipperId) {
        double avgRating = 0.0;

        String sql = "SELECT AVG(rating) FROM ShipperReviews WHERE shipperId = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, shipperId); // Gán giá trị shipperId
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                avgRating = rs.getDouble(1); // Lấy giá trị AVG(rating)
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return avgRating; // Trả về trung bình rating
    }

    public Shipper getShipperById(int shipperId) {

        String sql = "SELECT * FROM shipper WHERE shipperId = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, shipperId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Shipper shipper = new Shipper();

                shipper.setShipperId(rs.getInt("shipperId"));
                shipper.setFullName(rs.getString("fullName"));
                shipper.setEmail(rs.getString("email"));
                shipper.setPhoneNumber(rs.getString("phoneNumber"));
                shipper.setStatus(rs.getString("status"));
                shipper.setCreatedAt(rs.getDate("createdAt").toLocalDate());
                shipper.setUpdatedAt(rs.getDate("updatedAt").toLocalDate());

                return shipper;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // lấy tổng sô page
    public int getTotalPagesForShipperReviews(int shipperId, int pageSize) {
        int totalReviews = 0;
        int totalPages = 0;

        String sql = "SELECT COUNT(*) FROM ShipperReviews WHERE shipperId = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, shipperId); // Gán shipperId vào SQL
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                totalReviews = rs.getInt(1); // Lấy tổng số đánh giá
            }

            // Tính totalPages, làm tròn lên nếu còn dư
            totalPages = (int) Math.ceil((double) totalReviews / pageSize);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return totalPages; // Trả về tổng số trang
    }

    //  Lấy khách hàng theo ID 
    private Customer getCustomerByIdForShipperReviews(int customerId) {
        String sql = "SELECT * FROM Customers WHERE customerId = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, customerId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Customer customer = new Customer();
                customer.setCustomerId(rs.getInt("customerId"));
                customer.setFullName(rs.getString("fullName"));
                customer.setEmail(rs.getString("email"));
                customer.setPhoneNumber(rs.getString("phoneNumber"));
                customer.setAddress(rs.getString("address"));
                customer.setBirthDate(rs.getDate("BirthDate").toLocalDate());
                customer.setGender(rs.getString("Gender"));
                customer.setCreatedAt(rs.getDate("CreatedAt").toLocalDate());
                customer.setUpdatedAt(rs.getDate("UpdatedAt").toLocalDate());
                customer.setProfileImage(rs.getString("ProfileImage"));
                return customer;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        ShipperReviewDAO shipperReviewDAO = new ShipperReviewDAO();

        List<ShipperReview> shipperReview = shipperReviewDAO.getReviewsByShipperIdWithPagination(1, 0, 1, 100);
        for (ShipperReview shipperReview1 : shipperReview) {
            System.out.println(shipperReview1);
        }
    }
}
