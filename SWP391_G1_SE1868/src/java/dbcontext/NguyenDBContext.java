package dbcontext;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException; // Thêm import này
import java.util.logging.Level;
import java.util.logging.Logger;

public class NguyenDBContext {

    private static final String DB_USER = "root"; // Username MySQL
    private static final String DB_PASSWORD = "1111"; // Password MySQL
    private static final String DB_URL = "jdbc:mysql://localhost:3306/swp391_g1"; // Database name

    public NguyenDBContext() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(NguyenDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    }

    public static void main(String[] args) {
        try {
            NguyenDBContext db = new NguyenDBContext();
            Connection conn = db.getConnection();
            System.out.println("Database connection successful: " + conn);
            conn.close(); // Đóng kết nối sau khi kiểm tra
        } catch (SQLException ex) {
            Logger.getLogger(NguyenDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}