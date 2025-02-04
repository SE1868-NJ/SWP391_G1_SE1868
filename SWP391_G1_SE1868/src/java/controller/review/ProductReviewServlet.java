/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.review;

import entity.ProductReview;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import models.ProductReviewDAO;

/**
 *
 * @author Đạt
 */
@WebServlet(name = "ProductReviewServlet", urlPatterns = {"/getReviews"})
public class ProductReviewServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ProductReviewServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ProductReviewServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");

        try {
            // Lấy tham số từ request
            int productId = 1;
            int page = Integer.parseInt(request.getParameter("page"));
            int pageSize = 5;
            Integer starFilter = request.getParameter("starFilter") != null ? Integer.parseInt(request.getParameter("starFilter")) : null;

            // Gọi DAO
            ProductReviewDAO reviewDAO = new ProductReviewDAO();
            List<ProductReview> reviews = reviewDAO.getReviewsByProduct(productId, page, pageSize, starFilter);
            // get totalRating
            double totalRating = reviewDAO.getAverageRating(productId);
            int totalReview = reviewDAO.getTotalReviewsByProduct(productId, null);

            // Tính tổng số trang
            int totalReviews = reviewDAO.getTotalReviewsByProduct(productId, starFilter);
            int totalPages = (int) Math.ceil((double) totalReviews / pageSize);

            // Đẩy dữ liệu đến JSP
            request.setAttribute("reviews", reviews);
            request.setAttribute("totalPages", totalPages);
            request.setAttribute("currentPage", page);
            request.setAttribute("totalRating", totalRating);
            request.setAttribute("totalReview", totalReview);

            request.getRequestDispatcher("shop-details.jsp").forward(request, response);

        } catch (NumberFormatException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            request.setAttribute("error", "Tham số không hợp lệ: productId, page và pageSize phải là số nguyên.");
            request.getRequestDispatcher("error.jsp").forward(request, response);
            e.printStackTrace();
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            request.setAttribute("error", "Đã xảy ra lỗi trong quá trình xử lý.");
            request.getRequestDispatcher("error.jsp").forward(request, response);
            e.printStackTrace();
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
