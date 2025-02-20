/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author Đạt
 */
import Utils.VNPayUtils;
import config.ConfigVNPay;
import dbcontext.DBContext;
import entity.Customer;
import entity.ProductReview;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;   // tap ban ghi 
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.SQLException;
import java.sql.Timestamp;

public class CustomerDAO extends DBContext {
    // ✅ 1. Thêm khách hàng

    public boolean addCustomer(Customer customer) {
        String sql = "INSERT INTO Customers (fullName, email, password, phoneNumber, address, BirthDate, Gender, ProfileImage) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, customer.getFullName());
            stmt.setString(2, customer.getEmail());
            stmt.setString(3, customer.getPassword());
            stmt.setString(4, customer.getPhoneNumber());
            stmt.setString(5, customer.getAddress());
            stmt.setDate(6, Date.valueOf(customer.getBirthDate()));
            stmt.setString(7, customer.getGender());
            stmt.setString(8, customer.getProfileImage());
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
    
    
     // ✅ 1. Thêm khách hàng mã hóa
     public boolean addCustomerSHA512(Customer customer) {
        String sql = "INSERT INTO Customers (fullName, email, password, phoneNumber, address, BirthDate, Gender, ProfileImage) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, customer.getFullName());
            stmt.setString(2, customer.getEmail());
            stmt.setString(3, VNPayUtils.hmacSHA512(ConfigVNPay.vnp_HashSecret, customer.getPassword()));
            stmt.setString(4, customer.getPhoneNumber());
            stmt.setString(5, customer.getAddress());
            stmt.setDate(6, Date.valueOf(customer.getBirthDate()));
            stmt.setString(7, customer.getGender());
            stmt.setString(8, customer.getProfileImage());
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

    //  2. Cập nhật khách hàng mã hóa
    public boolean updateCustomer(Customer customer) {
        String sql = "UPDATE Customers SET FullName = ?, Email = ?, Password = ?, PhoneNumber = ?, Address = ?, BirthDate = ?, Gender = ?, ProfileImage = ? WHERE CustomerID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, customer.getFullName());
            stmt.setString(2, customer.getEmail());
            stmt.setString(3, VNPayUtils.hmacSHA512(ConfigVNPay.vnp_HashSecret, customer.getPassword()));
            stmt.setString(4, customer.getPhoneNumber());
            stmt.setString(5, customer.getAddress());
            stmt.setDate(6, Date.valueOf(customer.getBirthDate()));  // Chuyển từ LocalDate thành java.sql.Date
            stmt.setString(7, customer.getGender());  // 'Male' or 'Female'
            stmt.setString(8, customer.getProfileImage());  // Đường dẫn hình ảnh (có thể null)
            stmt.setInt(9, customer.getCustomerId());  // Dùng CustomerID để xác định khách hàng cần cập nhật

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    //  3. Xóa khách hàng
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

    //  5. Lấy tất cả khách hàng
    public List<Customer> getAllCustomers() {
        List<Customer> customers = new ArrayList<>();
        String sql = "SELECT * FROM Customers ORDER BY createdAt DESC";
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                customers.add(mapResultSetToCustomer(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customers;
    }

    // Hàm đăng nhập
    public Customer login(String email, String password) {
        String sql = "SELECT * FROM Customers WHERE email = ? AND password = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, email);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return mapResultSetToCustomer(rs); // Trả về đối tượng Customer nếu tìm thấy
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // Nếu không tìm thấy, trả về null
    }
    
    // Hàm đăng nhập
    public Customer LoginSHA512(String email, String password) {
        String sql = "SELECT * FROM Customers WHERE email = ? AND password = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, email);
            stmt.setString(2, VNPayUtils.hmacSHA512(ConfigVNPay.vnp_HashSecret, password));

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return mapResultSetToCustomer(rs); // Trả về đối tượng Customer nếu tìm thấy
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // Nếu không tìm thấy, trả về null
    }

    public Customer checkEmailExists(String email) {
        String sql = "SELECT * FROM Customers WHERE email = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, email);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return mapResultSetToCustomer(rs); // Trả về đối tượng Customer nếu tìm thấy
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // Nếu không tìm thấy, trả về null
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
        customer.setBirthDate(rs.getDate("BirthDate").toLocalDate());
        customer.setGender(rs.getString("Gender"));
        customer.setCreatedAt(rs.getDate("CreatedAt").toLocalDate());
        customer.setUpdatedAt(rs.getDate("UpdatedAt").toLocalDate());
        customer.setProfileImage(rs.getString("ProfileImage"));

        return customer;
    }

    public static void main(String[] args) {
        CustomerDAO customerDAO = new CustomerDAO();

        Customer customer = customerDAO.login("vana@gmail.com", "password123");
        System.out.println(customer);
    }

}
