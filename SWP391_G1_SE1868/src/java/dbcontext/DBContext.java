/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dbcontext;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBContext {

    private final String SERVERNAME = "LAPTOP-JNL3LDJO";
    private final String PORTNUMBER = "3306";
    private final String DATABASENAME = "SWP391_G1_SE1868";
    private final String ACCOUNT = "duy";
    private final String PASSWORD = "12345";
    private Connection conn;

    /*USE BELOW METHOD FOR YOUR DATABASE CONNECTION FOR BOTH SINGLE AND MULTILPE SQL SERVER INSTANCE(s)*/
 /*DO NOT EDIT THE BELOW METHOD, YOU MUST USE ONLY THIS ONE FOR YOUR DATABASE CONNECTION*/
    public static Connection getConnection() {
        String url = "jdbc:mysql://localhost:3306/swp391_g1";
        String user = "duy";
        String password = "12345";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try {
                return DriverManager.getConnection(url, user, password);
            } catch (SQLException ex) {
                Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);

        }
        return null;
    }

    public static void main(String[] args) {
        Connection conn = getConnection();
        if (conn != null) {
            System.out.println("Thành Công!");

        } else {
            System.out.println("Thất bại!");
        }

    }

}
