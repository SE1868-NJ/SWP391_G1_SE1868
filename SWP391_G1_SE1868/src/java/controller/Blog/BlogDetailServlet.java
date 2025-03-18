package controller.Blog;

import models.BlogDetailDAO;
import entity.BlogDetail;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "BlogDetailServlet", urlPatterns = {"/BlogDetailServlet"})
public class BlogDetailServlet extends HttpServlet {

    private BlogDetailDAO blogDetailDAO;

    @Override
    public void init() throws ServletException {
        blogDetailDAO = new BlogDetailDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String idParam = request.getParameter("id");
            if (idParam == null || idParam.trim().isEmpty()) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing or invalid 'id' parameter");
                return;
            }

            int idBlog;
            try {
                idBlog = Integer.parseInt(idParam);
            } catch (NumberFormatException e) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid 'id' parameter: must be a number");
                return;
            }

            BlogDetail blogDetail = blogDetailDAO.getBlogDetailByBlogId(idBlog);
            if (blogDetail == null) {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Blog detail not found");
                return;
            }

            String content = blogDetail.getContent();
            request.setAttribute("blogDetail", blogDetail);
            request.setAttribute("content", content);

            request.getRequestDispatcher("/BlogDetail.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Database error: " + e.getMessage());
        }
    }
}