package controller.Blog;

import models.BlogDAO;
import entity.Blog;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "BlogServlet", urlPatterns = {"/blog"})
public class BlogServlet extends HttpServlet {
    private BlogDAO blogDAO;
    

    @Override
    public void init() {
        blogDAO = new BlogDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Blog> blogs = blogDAO.getAllBlogs();
        request.setAttribute("blogs", blogs);
        request.getRequestDispatcher("blogList.jsp").forward(request, response);
       
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        int customerId = Integer.parseInt(request.getParameter("customerId"));
        String imageUrl = request.getParameter("imageUrl");

        Blog newBlog = new Blog(0, name, description, customerId, imageUrl);
        blogDAO.addBlog(newBlog);
        response.sendRedirect("blog");
      
    }
}
