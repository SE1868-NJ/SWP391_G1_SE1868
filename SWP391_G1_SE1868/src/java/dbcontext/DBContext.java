/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dbcontext;


import java.sql.Connection;
import java.sql.DriverManager;
import java.util.logging.Level;
import java.util.logging.Logger;
 
public class DBContext {
    private final String SERVERNAME = "localhost";
    private final String PORTNUMBER = "3306"; // Default port of MySQL
    private final String DATABASENAME = "SWP391_G1";
    private final String ACCOUNT = "root"; // MySQL account
    private final String PASSWORD = "nhat2004"; // MySQL password

    public Connection getConnection() throws Exception {
        // URL kết nối MySQL
        String url = "jdbc:mysql://" + SERVERNAME + ":" + PORTNUMBER 
                + "/" + DATABASENAME 
                + "?useSSL=false&allowPublicKeyRetrieval=true";

        // Sử dụng MySQL JDBC Driver
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(url, ACCOUNT, PASSWORD);
    }

    public static void main(String[] args) {
        try {
            DBContext dBContext = new DBContext();
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
