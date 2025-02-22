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

    public List<ShipperReview> getReviewsByShipperId(int shipperId) {
        List<ShipperReview> reviews = new ArrayList<>();
        String sql = "SELECT * FROM ShipperReviews WHERE shipperId = ?"; // Truy vấn đánh giá theo shipperId

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, shipperId);  // Thiết lập shipperId cho câu lệnh truy vấn

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                // Tạo đối tượng Review từ kết quả truy vấn
                ShipperReview review = new ShipperReview();

                review.setReviewId(rs.getInt("reviewId"));
                
                review.setCustomer(getCustomerByIdForShipperReviews(rs.getInt("customerId")));
                
                review.setShipper(getShipperById(rs.getInt("shipperId")));

                review.setRating(rs.getInt("rating"));
                review.setComment(rs.getString("comment"));
                review.setCreatedAt(rs.getDate("createdAt").toLocalDate());
                review.setUpdatedAt(rs.getDate("updatedAt").toLocalDate());

                reviews.add(review); // Thêm Review vào danh sách
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return reviews; // Trả về danh sách các đánh giá
    }

    private Shipper getShipperById(int shipperId) {
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

        List<ShipperReview> shipperReview = shipperReviewDAO.getReviewsByShipperId(1);
        for (ShipperReview shipperReview1 : shipperReview) {
            System.out.println(shipperReview1);
        }
    }
}
