package models;

import dbcontext.DBContext;
import entity.Contact;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ContactDAO {
    private DBContext db;
    
    public ContactDAO() {
        db = new DBContext();
    }
    
    public void addContact(Contact contact) {
        String sql = "INSERT INTO contacts (fullName, email, phone, message) VALUES (?, ?, ?, ?)";
        PreparedStatement stmt = null;
        
        try {
            // Lấy connection từ DBContext
            Connection conn = db.connection;
            if (conn == null) {
                throw new SQLException("Database connection is null");
            }
            
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, contact.getFullName());
            stmt.setString(2, contact.getEmail());
            stmt.setString(3, contact.getPhone());
            stmt.setString(4, contact.getMessage());
            
            int rowsAffected = stmt.executeUpdate();
            System.out.println(rowsAffected + " row(s) inserted");
            
        } catch (SQLException ex) {
            Logger.getLogger(ContactDAO.class.getName()).log(Level.SEVERE, "Error adding contact", ex);
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ContactDAO.class.getName()).log(Level.SEVERE, "Error closing statement", ex);
                }
            }
        }
    }
}