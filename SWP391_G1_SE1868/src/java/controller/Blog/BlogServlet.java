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
        int totalPages = (int) Math.ceil((double) totalBlogs / pageSize); // Tính tổng số trang

        // Set các attribute để truyền dữ liệu vào JSP
        request.setAttribute("blogs", blogs);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("currentPage", page);

        // Forward đến trang blogList.jsp
        request.getRequestDispatcher("blogList.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        int customerId = Integer.parseInt(request.getParameter("customerId"));
        String imageUrl = request.getParameter("imageUrl");

        // Tạo blog mới và bỏ qua CreatedDate vì nó sẽ được xử lý trong DB
        Blog newBlog = new Blog(0, name, description, customerId, imageUrl, null); // null vì database sẽ tự động set CreatedDate

        // Gọi phương thức thêm blog vào cơ sở dữ liệu
        blogDAO.addBlog(newBlog);

        // Chuyển hướng về trang blog
        response.sendRedirect("blog");
    }

}
