/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.review;

import entity.Customer;
import entity.ProductReview;
import entity.ShipperReview;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import models.ProductReviewDAO;
import models.ShipperReviewDAO;

/**
 *
 * @author Đạt
 */
@WebServlet(name = "UpdateShipperReviewServlet", urlPatterns = {"/updateShipperReview"})
public class UpdateShipperReviewServlet extends HttpServlet {

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
            out.println("<title>Servlet UpdateShipperReviewServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpdateShipperReviewServlet at " + request.getContextPath() + "</h1>");
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
        int reviewId = Integer.parseInt(request.getParameter("reviewId"));
        
          // truyền carts của customer
        HttpSession session = request.getSession();

        // lấy đố tương cusomret ở session
        Customer customer = (Customer) session.getAttribute("user");
        // check custoemer
        if (customer == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        // khai báo ShipperReviewDAO
        ShipperReviewDAO shipperReviewDAO = new ShipperReviewDAO();

        
         // khai báo ShipperReview
        ShipperReview review = shipperReviewDAO.getReviewById(reviewId);

        request.setAttribute("review", review);
        request.getRequestDispatcher("updateShipperReview.jsp").forward(request, response);
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
//        processRequest(request, response);

            // Lấy các tham số từ form
        int reviewId = Integer.parseInt(request.getParameter("reviewId"));
        int rating = Integer.parseInt(request.getParameter("rating"));
        String comment = request.getParameter("comment");
        
          // truyền carts của customer
        HttpSession session = request.getSession();

        // lấy đố tương cusomret ở session
        Customer customer = (Customer) session.getAttribute("user");
        // check custoemer
        if (customer == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        // khai báo ShipperReviewDAO
        ShipperReviewDAO shipperReviewDAO = new ShipperReviewDAO();

        
         // khai báo ShipperReview
        ShipperReview review = shipperReviewDAO.getReviewById(reviewId);

        review.setRating(rating);
        review.setComment(comment);

        // Cập nhật thông tin vào cơ sở dữ liệu
        boolean success = shipperReviewDAO.updateShipperReview(review);

        // Truyền giá trị thành công qua query parameter
        String successParam = success ? "true" : "false";
        // truyền Id vừa truyền
        response.sendRedirect("shipperReview?shipperId="+review.getShipper().getShipperId()+"&success=" + successParam);
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
