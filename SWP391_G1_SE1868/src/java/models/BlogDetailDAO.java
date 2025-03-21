package models;

import dbcontext.DBContext; // Sử dụng DBContext gốc
import entity.BlogDetail;
import java.sql.*;

public class BlogDetailDAO {

    private DBContext dbContext; // Sử dụng DBContext thay vì NguyenDBContext

    public BlogDetailDAO() {
        dbContext = new DBContext(); // Khởi tạo DBContext
    }

    public BlogDetail getBlogDetailByBlogId(int idBlog) throws SQLException {
        String query = "SELECT bd.IdBlogDetail, bd.IdBlog, bd.Title, bd.Content, bd.CreatedDate, bl.ImageURL " +
                       "FROM blogdetail bd " +
                       "LEFT JOIN bloglist bl ON bd.IdBlog = bl.IdBlog " +
                       "WHERE bd.IdBlog = ?";
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            // Sử dụng connection trực tiếp từ DBContext
            if (dbContext.connection == null) {
                throw new SQLException("Database connection is null");
            }
            
            stmt = dbContext.connection.prepareStatement(query);
            stmt.setInt(1, idBlog);
            rs = stmt.executeQuery();

            if (rs.next()) {
                return new BlogDetail(
                    rs.getInt("IdBlogDetail"),
                    rs.getInt("IdBlog"),
                    rs.getString("Title"),
                    rs.getString("Content"),
                    rs.getTimestamp("CreatedDate").toLocalDateTime().toLocalDate(),
                    rs.getString("ImageURL")
                );
            }
            return null;
        } catch (SQLException e) {
            System.err.println("Error getting blog detail: " + e.getMessage());
            throw e;
        } finally {
            // Đóng ResultSet và PreparedStatement
            if (rs != null) {
                try { rs.close(); } catch (SQLException e) { e.printStackTrace(); }
            }
            if (stmt != null) {
                try { stmt.close(); } catch (SQLException e) { e.printStackTrace(); }
            }
        }
    }

    public void insertBlogDetail(BlogDetail blogDetail) throws SQLException {
        String query = "INSERT INTO blogdetail (IdBlog, Title, Content, CreatedDate) VALUES (?, ?, ?, ?)";
        PreparedStatement stmt = null;
        
        try {
            // Sử dụng connection trực tiếp từ DBContext
            if (dbContext.connection == null) {
                throw new SQLException("Database connection is null");
            }
            
            stmt = dbContext.connection.prepareStatement(query);
            System.out.println("Đang chèn blog: " + blogDetail); // Log dữ liệu đầu vào
            stmt.setInt(1, blogDetail.getIdBlog());
            stmt.setString(2, blogDetail.getTitle());
            stmt.setString(3, blogDetail.getContent());
            stmt.setTimestamp(4, Timestamp.valueOf(blogDetail.getCreatedDate().atStartOfDay()));
            int rowsAffected = stmt.executeUpdate();
            System.out.println("Số hàng được chèn: " + rowsAffected); // Log số hàng bị ảnh hưởng
        } catch (SQLException e) {
            System.err.println("Lỗi khi chèn blog: " + e.getMessage()); // Log lỗi nếu có
            throw e; // Ném lại ngoại lệ để xử lý ở lớp trên nếu cần
        } finally {
            // Đóng PreparedStatement
            if (stmt != null) {
                try { stmt.close(); } catch (SQLException e) { e.printStackTrace(); }
            }
        }
    }
}