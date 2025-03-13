package models;

import dbcontext.DBContext;
import entity.Blog;
import java.sql.*;
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
        int offset = (page - 1) * pageSize; // Tính toán OFFSET dựa vào trang hiện tại
        ps.setInt(1, offset);
        ps.setInt(2, pageSize); // Giới hạn số lượng blog mỗi trang
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            // Thêm trường CreatedDate vào constructor
            blogs.add(new Blog(
                    rs.getInt("IdBlog"),
                    rs.getString("NameBlog"),
                    rs.getString("DescriptionBlog"),
                    rs.getInt("CustomerID"),
                    rs.getString("ImageURL"),
                    rs.getTimestamp("CreatedDate")  // Lấy CreatedDate từ ResultSet
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

    public void addBlog(Blog blog) {
    String sql = "INSERT INTO bloglist (NameBlog, DescriptionBlog, CustomerID, ImageURL, CreatedDate) VALUES (?, ?, ?, ?, ?)";
    try {
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, blog.getName());
        ps.setString(2, blog.getDescription());
        ps.setInt(3, blog.getCustomerId());
        ps.setString(4, blog.getImageUrl());
        
        // Đặt CreatedDate là thời gian hiện tại
        ps.setTimestamp(5, new Timestamp(System.currentTimeMillis()));

        ps.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}


}
