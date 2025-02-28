package dbcontext;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.logging.Level;
import java.util.logging.Logger;

public class NguyenDBContext {
    private final String SERVERNAME = "localhost";
    private final String PORTNUMBER = "3306"; // Default MySQL port
    private final String DATABASENAME = "SWP391_G1";
    private final String ACCOUNT = "root"; // MySQL default user
    private final String PASSWORD = "1111"; // Update this to your MySQL password

    /*USE BELOW METHOD FOR YOUR DATABASE CONNECTION FOR BOTH SINGLE AND MULTIPLE MYSQL INSTANCE(s)*/
    /*DO NOT EDIT THE BELOW METHOD, YOU MUST USE ONLY THIS ONE FOR YOUR DATABASE CONNECTION*/
    public Connection getConnection() throws Exception {
        // MySQL connection URL format
        String url = "jdbc:mysql://" + SERVERNAME + ":" + PORTNUMBER + "/" + DATABASENAME;
        Class.forName("com.mysql.cj.jdbc.Driver"); // MySQL JDBC driver
        return DriverManager.getConnection(url, ACCOUNT, PASSWORD);
    }

    public static void main(String[] args) {
        try {
            NguyenDBContext dBContext = new NguyenDBContext();
            if (dBContext.getConnection() != null) {
                System.out.println("Kết nối thành công");
            } else {
                System.out.println("Kết nối thất bại");
            }
        } catch (Exception ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
