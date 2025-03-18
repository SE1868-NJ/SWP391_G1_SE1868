package models;

import dbcontext.DBContext;
import entity.Blog;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BlogDAO {

    private Connection conn;

    public BlogDAO() {
        conn = new DBContext().connection;
    }

    // Lấy blog với phân trang, sắp xếp theo CreatedDate giảm dần
    public List<Blog> getBlogsByPage(int page, int pageSize) {
        return searchBlogsByPage(page, pageSize, null);
    }

    // Tìm kiếm blog với phân trang
    public List<Blog> searchBlogsByPage(int page, int pageSize, String keyword) {
        List<Blog> blogs = new ArrayList<>();
        String sql;
        if (keyword == null || keyword.trim().isEmpty()) {
            sql = "SELECT * FROM bloglist ORDER BY CreatedDate DESC LIMIT ?, ?";
        } else {
            sql = "SELECT * FROM bloglist WHERE NameBlog LIKE ? OR DescriptionBlog LIKE ? " +
                  "ORDER BY CreatedDate DESC LIMIT ?, ?";
        }
        
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            int offset = (page - 1) * pageSize;
            if (keyword != null && !keyword.trim().isEmpty()) {
                ps.setString(1, "%" + keyword + "%");
                ps.setString(2, "%" + keyword + "%");
                ps.setInt(3, offset);
                ps.setInt(4, pageSize);
            } else {
                ps.setInt(1, offset);
                ps.setInt(2, pageSize);
            }
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Date sqlDate = rs.getDate("CreatedDate");
                LocalDate createdDate = sqlDate != null ? sqlDate.toLocalDate() : null;
                
                blogs.add(new Blog(
                        rs.getInt("IdBlog"),
                        rs.getString("NameBlog"),
                        rs.getString("DescriptionBlog"),
                        rs.getInt("CustomerID"),
                        rs.getString("ImageURL"),
                        createdDate
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return blogs;
    }

    // Lấy tổng số blog (có lọc theo từ khóa nếu có)
    public int getTotalBlogs(String keyword) {
        int totalBlogs = 0;
        String sql;
        if (keyword == null || keyword.trim().isEmpty()) {
            sql = "SELECT COUNT(*) FROM bloglist";
        } else {
            sql = "SELECT COUNT(*) FROM bloglist WHERE NameBlog LIKE ? OR DescriptionBlog LIKE ?";
        }
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            if (keyword != null && !keyword.trim().isEmpty()) {
                ps.setString(1, "%" + keyword + "%");
                ps.setString(2, "%" + keyword + "%");
            }
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                totalBlogs = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return totalBlogs;
    }

    // Thêm blog mới
    public int addBlog(Blog blog) {
    String sql = "INSERT INTO bloglist (NameBlog, DescriptionBlog, CustomerID, ImageURL, CreatedDate) VALUES (?, ?, ?, ?, ?)";
    try {
        PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ps.setString(1, blog.getName());
        ps.setString(2, blog.getDescription());
        ps.setInt(3, blog.getCustomerId());
        ps.setString(4, blog.getImageUrl());
        LocalDate createdDate = blog.getCreatedDate() != null ? blog.getCreatedDate() : LocalDate.now();
        ps.setDate(5, java.sql.Date.valueOf(createdDate));
        ps.executeUpdate();

        // Lấy IdBlog vừa tạo
        ResultSet rs = ps.getGeneratedKeys();
        if (rs.next()) {
            return rs.getInt(1);
        }
        throw new SQLException("Không thể lấy IdBlog vừa tạo.");
    } catch (SQLException e) {
        e.printStackTrace();
        throw new RuntimeException("Lỗi khi thêm blog: " + e.getMessage());
    }
}
}