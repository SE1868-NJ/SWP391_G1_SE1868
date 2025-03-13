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

    // Lấy blog với phân trang
    public List<Blog> getBlogsByPage(int page, int pageSize) {
        List<Blog> blogs = new ArrayList<>();
        String sql = "SELECT * FROM bloglist LIMIT ?, ?";
        
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            int offset = (page - 1) * pageSize;
            ps.setInt(1, offset);
            ps.setInt(2, pageSize);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                // Lấy Date từ database và chuyển sang LocalDate
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

    // Lấy tổng số blog trong cơ sở dữ liệu
    public int getTotalBlogs() {
        int totalBlogs = 0;
        String sql = "SELECT COUNT(*) FROM bloglist";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
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
    public void addBlog(Blog blog) {
        String sql = "INSERT INTO bloglist (NameBlog, DescriptionBlog, CustomerID, ImageURL, CreatedDate) VALUES (?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, blog.getName());
            ps.setString(2, blog.getDescription());
            ps.setInt(3, blog.getCustomerId());
            ps.setString(4, blog.getImageUrl());
            // Chuyển LocalDate sang java.sql.Date
            LocalDate createdDate = blog.getCreatedDate() != null ? blog.getCreatedDate() : LocalDate.now();
            ps.setDate(5, java.sql.Date.valueOf(createdDate));
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}