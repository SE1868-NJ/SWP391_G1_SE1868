package models;


import dbcontext.DBContext;
import entity.Contact;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ContactDAO extends DBContext{
   

    public void addContact(Contact contact) {
        String sql = "INSERT INTO contacts (fullName, email, phone, message) VALUES (?, ?, ?, ?)";
        try ( PreparedStatement stmt = connection.prepareStatement(sql)) {
            
            stmt.setString(1, contact.getFullName());
            stmt.setString(2, contact.getEmail());
            stmt.setString(3, contact.getPhone());
            stmt.setString(4, contact.getMessage());
            
            stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ContactDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}