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
        // Lấy số trang từ tham số URL, nếu không có thì mặc định là trang 1
        String pageParam = request.getParameter("page");
        int page = (pageParam != null) ? Integer.parseInt(pageParam) : 1;
        int pageSize = 9; // Số blog mỗi trang

        // Lấy danh sách blog theo trang
        List<Blog> blogs = blogDAO.getBlogsByPage(page, pageSize);

        // Lấy tổng số blog để tính số trang
        int totalBlogs = blogDAO.getTotalBlogs();
        int totalPages = (int) Math.ceil((double) totalBlogs / pageSize);

        // Set các attribute để truyền dữ liệu vào JSP
        request.setAttribute("blogs", blogs);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("currentPage", page);

        // Forward đến trang blogList.jsp
        request.getRequestDispatcher("blogList.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Lấy dữ liệu từ form
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        int customerId = Integer.parseInt(request.getParameter("customerId"));

        // Xử lý file ảnh
        Part filePart = request.getPart("image");
        String fileName = filePart.getSubmittedFileName();
        String uploadPath = request.getServletContext().getRealPath("") + "assets/img/blog/";
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }

        // Tạo đường dẫn lưu file
        String filePath = uploadPath + fileName;
        filePart.write(filePath);

        // Lưu đường dẫn tương đối vào cơ sở dữ liệu (từ webapp/assets/img/blog/)
        String relativePath = "assets/img/blog/" + fileName;

        // Tạo blog mới với CreatedDate là LocalDate.now()
        Blog newBlog = new Blog(0, name, description, customerId, relativePath, LocalDate.now());

        // Gọi phương thức thêm blog vào cơ sở dữ liệu
        blogDAO.addBlog(newBlog);

        // Chuyển hướng về trang blog
        response.sendRedirect("blog");
    }
}