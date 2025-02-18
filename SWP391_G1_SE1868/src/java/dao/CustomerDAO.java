package dao;

import java.sql.*;
import dbcontext.DBConnection;
import entity.Customer;
import static org.apache.tomcat.jni.Buffer.address;

public class CustomerDAO {

    // Phương thức đăng nhập, trả về đối tượng Customer nếu đăng nhập thành công
public Customer login(String email, String password) {
    Customer customer = null;

    // SQL query để tìm khách hàng theo email và mật khẩu
    String query = "SELECT * FROM customers WHERE Email = ? AND Password = ?";

    try (Connection connection = DBConnection.getConnection(); 
         PreparedStatement stmt = connection.prepareStatement(query)) {

        stmt.setString(1, email);
        stmt.setString(2, password);

        ResultSet rs = stmt.executeQuery();

        // Nếu có kết quả trả về, tạo đối tượng Customer từ dữ liệu
        if (rs.next()) {
            int customerId = rs.getInt("CustomerID");
            String fullName = rs.getString("FullName");
            String customerEmail = rs.getString("Email");
            String phoneNumber = rs.getString("PhoneNumber");
            String address = rs.getString("Address");
            Date birthDate = rs.getDate("BirthDate"); // Retrieve the birth date from the result set
            String gender = rs.getString("Gender");

            Date createdAt = rs.getDate("CreatedAt"); // Retrieve the created at timestamp
            Date updatedAt = rs.getDate("UpdatedAt"); // Retrieve the updated at timestamp

            // Create a new customer object with the data from the database
            customer = new Customer(customerId, fullName, email, password, phoneNumber, address, birthDate, gender, createdAt, updatedAt, query, updatedAt);
        }
    } catch (SQLException e) {
        e.printStackTrace();  // Log any SQL errors
    }
    return customer;
}


    // Method to get customer details by customerID
public Customer getCustomerById(int customerId) {
    String query = "SELECT FullName, Email, PhoneNumber, Address, BirthDate, Gender, CreatedAt, UpdatedAt FROM customers WHERE CustomerID = ?";
    Customer customer = null;

    try (Connection connection = DBConnection.getConnection(); 
         PreparedStatement stmt = connection.prepareStatement(query)) {

        stmt.setInt(1, customerId); // Use customerID for this query

        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            customer.setCustomerId(customerId); // Set customerID
            customer.setFullName(rs.getString("FullName"));
            customer.setEmail(rs.getString("Email"));
            customer.setPhoneNumber(rs.getString("PhoneNumber"));
            customer.setAddress(rs.getString("Address"));
            customer.setBirthDate(rs.getDate("BirthDate"));
            customer.setGender(rs.getString("Gender"));
            customer.setCreatedAt(rs.getDate("CreatedAt"));  // Retrieve CreatedAt from ResultSet
            customer.setUpdatedAt(rs.getDate("UpdatedAt"));  // Retrieve UpdatedAt from ResultSet
            
        }
    } catch (SQLException e) {
        e.printStackTrace();  // Log exception
    }
    return customer;  // Return null if no customer found
}


    // Method to update the customer profile
    public boolean updateCustomerProfile(Customer customer) {
        String updateSQL = "UPDATE customers SET FullName = ?, Email = ?, PhoneNumber = ?, Address = ?, BirthDate = ?, Gender = ?, Password = ?, UpdatedAt = ? WHERE CustomerID = ?";

        try (Connection conn = DBConnection.getConnection(); 
             PreparedStatement ps = conn.prepareStatement(updateSQL)) {

            ps.setString(1, customer.getFullName());
            ps.setString(2, customer.getEmail());
            ps.setString(3, customer.getPhoneNumber());
            ps.setString(4, customer.getAddress());
            ps.setDate(5, (Date) customer.getBirthDate());
            ps.setString(6, customer.getGender());
            ps.setString(7, customer.getPassword());  // Plain password, no encryption for simplicity
            ps.setDate(8, new Date(System.currentTimeMillis()));  // UpdatedAt
            ps.setInt(9, customer.getCustomerId());

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;  // Return true if update was successful
        } catch (SQLException e) {
            e.printStackTrace();  // Log exception more specifically
        }
        return false;  // Return false if update fails
    }
}
