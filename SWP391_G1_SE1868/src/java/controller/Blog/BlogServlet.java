package controller.Blog;

import models.BlogDAO;
import entity.Blog;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@WebServlet(name = "BlogServlet", urlPatterns = {"/blog"})
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
                 maxFileSize = 1024 * 1024 * 10,      // 10MB
                 maxRequestSize = 1024 * 1024 * 50)    // 50MB
public class BlogServlet extends HttpServlet {

    private BlogDAO blogDAO;

    @Override
    public void init() {
        blogDAO = new BlogDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pageParam = request.getParameter("page");
        String search = request.getParameter("search");
        int page = (pageParam != null) ? Integer.parseInt(pageParam) : 1;
        int pageSize = 9;

        List<Blog> blogs = blogDAO.searchBlogsByPage(page, pageSize, search);
        int totalBlogs = blogDAO.getTotalBlogs(search);
        int totalPages = (int) Math.ceil((double) totalBlogs / pageSize);

        request.setAttribute("blogs", blogs);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("currentPage", page);

        request.getRequestDispatcher("blogList.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        int customerId = Integer.parseInt(request.getParameter("customerId"));

        Part filePart = request.getPart("image");
        String fileName = filePart.getSubmittedFileName();
        String uploadPath = request.getServletContext().getRealPath("") + "assets/img/blog/";
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }

        String filePath = uploadPath + fileName;
        filePart.write(filePath);
        String relativePath = "assets/img/blog/" + fileName;

        Blog newBlog = new Blog(0, name, description, customerId, relativePath, LocalDate.now());
        blogDAO.addBlog(newBlog);

        response.sendRedirect("blog?page=1");
    }
}