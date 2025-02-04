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
import entity.Customer;
import entity.ProductReview;
import java.sql.PreparedStatement;
import java.sql.ResultSet;   // tap ban ghi 
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.SQLException;
import java.sql.Timestamp;
public class CustomerDAO extends DBContext{
     // ✅ 1. Thêm khách hàng
    public boolean addCustomer(Customer customer) {
        String sql = "INSERT INTO Customers (fullName, email, password, phoneNumber, address, createdAt, updatedAt) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, customer.getFullName());
            stmt.setString(2, customer.getEmail());
            stmt.setString(3, customer.getPassword());
            stmt.setString(4, customer.getPhoneNumber());
            stmt.setString(5, customer.getAddress());
            stmt.setTimestamp(6, Timestamp.valueOf(customer.getCreatedAt()));
            stmt.setTimestamp(7, Timestamp.valueOf(customer.getUpdatedAt()));

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                ResultSet generatedKeys = stmt.getGeneratedKeys();
                if (generatedKeys.next()) {
                    customer.setCustomerId(generatedKeys.getInt(1));
                }
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // ✅ 2. Cập nhật khách hàng
    public boolean updateCustomer(Customer customer) {
        String sql = "UPDATE Customers SET fullName = ?, email = ?, password = ?, phoneNumber = ?, address = ?, updatedAt = ? WHERE customerId = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, customer.getFullName());
            stmt.setString(2, customer.getEmail());
            stmt.setString(3, customer.getPassword());
            stmt.setString(4, customer.getPhoneNumber());
            stmt.setString(5, customer.getAddress());
            stmt.setTimestamp(6, Timestamp.valueOf(customer.getUpdatedAt()));
            stmt.setInt(7, customer.getCustomerId());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // ✅ 3. Xóa khách hàng
    public boolean deleteCustomer(int customerId) {
        String sql = "DELETE FROM Customers WHERE customerId = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, customerId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    //  Lấy khách hàng theo ID (Có kiểm tra `NULL` để tránh lỗi)
    public Customer getCustomerById(int customerId) {
        String sql = "SELECT * FROM Customers WHERE customerId = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, customerId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return mapResultSetToCustomer(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // ✅ 5. Lấy tất cả khách hàng
    public List<Customer> getAllCustomers() {
        List<Customer> customers = new ArrayList<>();
        String sql = "SELECT * FROM Customers ORDER BY createdAt DESC";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                customers.add(mapResultSetToCustomer(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customers;
    }

    //  Hàm trợ giúp chuyển đổi ResultSet thành Customer (Có kiểm tra NULL)
    private Customer mapResultSetToCustomer(ResultSet rs) throws SQLException {
        Customer customer = new Customer();
        customer.setCustomerId(rs.getInt("customerId"));
        customer.setFullName(rs.getString("fullName"));
        customer.setEmail(rs.getString("email"));
        customer.setPassword(rs.getString("password"));
        customer.setPhoneNumber(rs.getString("phoneNumber"));
        customer.setAddress(rs.getString("address"));

        //  Kiểm tra NULL trước khi gọi .toLocalDateTime()
        Timestamp createdTimestamp = rs.getTimestamp("createdAt");
        customer.setCreatedAt(createdTimestamp != null ? createdTimestamp.toLocalDateTime() : null);

        Timestamp updatedTimestamp = rs.getTimestamp("updatedAt");
        customer.setUpdatedAt(updatedTimestamp != null ? updatedTimestamp.toLocalDateTime() : null);

        return customer;
    }
}
