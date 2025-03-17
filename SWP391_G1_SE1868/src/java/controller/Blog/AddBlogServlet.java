package controller.Blog;

import entity.Customer;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "AddBlogServlet", urlPatterns = {"/addBlog"})
public class AddBlogServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Customer customer = (Customer) session.getAttribute("user");

        if (customer == null) {
            // Chuyển hướng đến trang đăng nhập nếu chưa đăng nhập
            response.sendRedirect("login.jsp");
            return;
        }

        // Nếu đã đăng nhập, hiển thị form thêm blog
        request.getRequestDispatcher("addBlog.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Nếu có yêu cầu POST tới /addBlog, chuyển hướng về /blog để xử lý
        response.sendRedirect("blog");
    }
}