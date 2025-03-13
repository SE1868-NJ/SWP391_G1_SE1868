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

    public List<Blog> getAllBlogs() {
        List<Blog> blogs = new ArrayList<>();
        String sql = "SELECT * FROM bloglist";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                blogs.add(new Blog(
                        rs.getInt("IdBlog"),
                        rs.getString("NameBlog"),
                        rs.getString("DescriptionBlog"),
                        rs.getInt("CustomerID"),
                        rs.getString("ImageURL")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return blogs;
    }

    public void addBlog(Blog blog) {
        String sql = "INSERT INTO bloglist (NameBlog, DescriptionBlog, CustomerID, ImageURL) VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, blog.getName());
            ps.setString(2, blog.getDescription());
            ps.setInt(3, blog.getCustomerId());
            ps.setString(4, blog.getImageUrl());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    

}
