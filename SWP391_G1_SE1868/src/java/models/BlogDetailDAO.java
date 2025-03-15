package models;

import dbcontext.NguyenDBContext; // Import NguyenDBContext thay vì DBContext cũ
import entity.BlogDetail;
import java.sql.*;


public class BlogDetailDAO {

    private NguyenDBContext dbContext; // Thay DBContext thành NguyenDBContext

    public BlogDetailDAO() {
        dbContext = new NguyenDBContext(); // Khởi tạo NguyenDBContext
    }

    private Connection getConnection() throws SQLException {
        return dbContext.getConnection(); // Lấy kết nối từ NguyenDBContext
    }

    public BlogDetail getBlogDetailByBlogId(int idBlog) throws SQLException {
        String query = "SELECT bd.IdBlogDetail, bd.IdBlog, bd.Title, bd.Content, bd.CreatedDate, bl.ImageURL " +
                       "FROM blogdetail bd " +
                       "LEFT JOIN bloglist bl ON bd.IdBlog = bl.IdBlog " +
                       "WHERE bd.IdBlog = ?";
        try (Connection conn = getConnection(); // Tạo kết nối mới và tự động đóng
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, idBlog);
            ResultSet rs = stmt.executeQuery();

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
        }
    }
    public void insertBlogDetail(BlogDetail blogDetail) throws SQLException {
    String query = "INSERT INTO blogdetail (IdBlog, Title, Content, CreatedDate) VALUES (?, ?, ?, ?)";
    try (Connection conn = getConnection();
         PreparedStatement stmt = conn.prepareStatement(query)) {
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
    }
}
}