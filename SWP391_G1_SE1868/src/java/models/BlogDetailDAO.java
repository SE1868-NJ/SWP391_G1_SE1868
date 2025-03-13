package models;

import dbcontext.DBContext;
import entity.BlogDetail;
import jakarta.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.sql.Date;

public class BlogDetailDAO {
    private DBContext db;

    public BlogDetailDAO() {
        db = new DBContext();
    }

    public BlogDetail getBlogDetailById(int idBlogDetail) throws SQLException {
        String sql = "SELECT bd.*, bl.ImageURL " +
                     "FROM blogdetail bd " +
                     "JOIN bloglist bl ON bd.IdBlog = bl.IdBlog " +
                     "WHERE bd.IdBlogDetail = ?";
        PreparedStatement stmt = db.connection.prepareStatement(sql);
        stmt.setInt(1, idBlogDetail);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            // Chuyển đổi Date từ database sang LocalDate
            Date sqlDate = rs.getDate("CreatedDate");
            LocalDate createdDate = sqlDate != null ? sqlDate.toLocalDate() : null;

            BlogDetail blogDetail = new BlogDetail();
            blogDetail.setIdBlogDetail(rs.getInt("IdBlogDetail"));
            blogDetail.setIdBlog(rs.getInt("IdBlog"));
            blogDetail.setTitle(rs.getString("Title"));
            blogDetail.setContentFilePath(rs.getString("ContentFilePath"));
            blogDetail.setCreatedDate(createdDate); // Sử dụng LocalDate
            blogDetail.setImageUrl(rs.getString("ImageURL")); // Lấy ImageURL từ bloglist
            return blogDetail;
        }
        return null;
    }

    public BlogDetail getBlogDetailByBlogId(int idBlog) throws SQLException {
        String sql = "SELECT bd.*, bl.ImageURL " +
                     "FROM blogdetail bd " +
                     "JOIN bloglist bl ON bd.IdBlog = bl.IdBlog " +
                     "WHERE bd.IdBlog = ?";
        PreparedStatement stmt = db.connection.prepareStatement(sql);
        stmt.setInt(1, idBlog);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            // Chuyển đổi Date từ database sang LocalDate
            Date sqlDate = rs.getDate("CreatedDate");
            LocalDate createdDate = sqlDate != null ? sqlDate.toLocalDate() : null;

            BlogDetail blogDetail = new BlogDetail();
            blogDetail.setIdBlogDetail(rs.getInt("IdBlogDetail"));
            blogDetail.setIdBlog(rs.getInt("IdBlog"));
            blogDetail.setTitle(rs.getString("Title"));
            blogDetail.setContentFilePath(rs.getString("ContentFilePath"));
            blogDetail.setCreatedDate(createdDate); // Sử dụng LocalDate
            blogDetail.setImageUrl(rs.getString("ImageURL")); // Lấy ImageURL từ bloglist
            return blogDetail;
        }
        return null;
    }

    public String getContentFromFile(String filePath, HttpServletRequest request) {
        String realPath = request.getServletContext().getRealPath("/") + filePath;
        StringBuilder content = new StringBuilder();
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(
                        new java.io.FileInputStream(realPath), StandardCharsets.UTF_8))) {
            String line;
            while ((line = br.readLine()) != null) {
                content.append(line).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "Error reading file: " + realPath;
        }
        return content.toString();
    }
}