package controller.Blog;

import models.BlogDAO;
import entity.Blog;
import entity.BlogDetail;
import entity.Customer;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import models.BlogDetailDAO;

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
        HttpSession session = request.getSession();
        Customer customer = (Customer) session.getAttribute("user");

        if (customer == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String content = request.getParameter("content");
        int customerId = customer.getCustomerId();

        System.out.println("Description nhận được: " + (description != null ? description.length() : 0) + " ký tự - " + description);
        System.out.println("Content nhận được: " + (content != null ? content.length() : 0) + " ký tự - " + content);

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
        int newBlogId = blogDAO.addBlog(newBlog);

        if (newBlogId > 0) {
            System.out.println("Blog đã được thêm với IdBlog: " + newBlogId);
        } else {
            System.out.println("Lỗi: Không thêm được blog!");
        }

        BlogDetailDAO blogDetailDAO = new BlogDetailDAO();
        BlogDetail blogDetail = new BlogDetail(0, newBlogId, name, content, LocalDate.now(), relativePath);
        try {
            blogDetailDAO.insertBlogDetail(blogDetail);
            System.out.println("Đã thêm blog vào blogdetail với IdBlog: " + newBlogId);
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error adding blog detail: " + e.getMessage());
            return;
        }

        response.sendRedirect("blog?page=1");
    }
}