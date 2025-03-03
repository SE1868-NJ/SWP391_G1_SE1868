/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dbcontext;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class GiangDBcontext {

    // Các thông tin kết nối cơ sở dữ liệu
    private static final String URL = "jdbc:mysql://localhost:3306/SWP391_G1";  // Thay đổi URL cho phù hợp
    private static final String USER = "root";  // Tên người dùng cơ sở dữ liệu
    private static final String PASSWORD = "1234";  // Mật khẩu của người dùng cơ sở dữ liệu

    // Phương thức trả về kết nối cơ sở dữ liệu
    public static Connection getConnection() throws SQLException {
        try {
            // Đảm bảo rằng Driver JDBC đã được tải
            Class.forName("com.mysql.cj.jdbc.Driver");  // Chỉ cần nếu bạn đang sử dụng MySQL
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new SQLException("Không tìm thấy driver JDBC.");
        }

        // Tạo và trả về kết nối đến cơ sở dữ liệu
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
