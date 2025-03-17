/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.review;

import entity.Customer;
import entity.Shipper;
import entity.ShipperReview;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import models.ShipperReviewDAO;
import models.OrderDAO;

/**
 *
 * @author Đạt
 */
@WebServlet(name = "ShipperReviewServlet", urlPatterns = {"/shipperReview"})
public class ShipperReviewServlet extends HttpServlet {

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
            out.println("<title>Servlet ShipperReviewServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ShipperReviewServlet at " + request.getContextPath() + "</h1>");
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
//        processRequest(request, response);
        int shipperId = Integer.parseInt(request.getParameter("shipperId"));
        int orderId = Integer.parseInt(request.getParameter("orderId"));
        String page = request.getParameter("page");
        String rating = request.getParameter("rating");
        int pageSize = 3;

        if (rating == null || rating.isEmpty()) {
            rating = "0";  // thiết lập giá trị mặc định là 1 nếu tham số không có
        }

        if (page == null || page.isEmpty()) {
            page = "1";  // thiết lập giá trị mặc định là 1 nếu tham số không có
        }
        // lấy sessiong customer
        HttpSession session = request.getSession();

        //lấy đố tương cusomret ở session
        Customer customer = (Customer) session.getAttribute("user");
        // check custoemer
        if (customer == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        // khai bao Dao ShipperReview
        ShipperReviewDAO shipperReviewDAO = new ShipperReviewDAO();

        // check đon hang đã được đánh giá chưa
        OrderDAO orderDAO = new OrderDAO();

        // lấy list shipepr review
        List<ShipperReview> shipperReviews = shipperReviewDAO.getReviewsByShipperIdWithPagination(1, Integer.parseInt(rating), Integer.parseInt(page), pageSize);

        // lấy thông tin shipper
        Shipper shipper = shipperReviewDAO.getShipperById(shipperId);

        // laays toongr soo đabgs giá của shipper
        int totalReview = shipperReviewDAO.getTotalReviewsByShipperId(shipperId);

        // lấy trung bình số rating
        double totalRating = shipperReviewDAO.getAverageRatingByShipperId(shipperId);

        // lấy totalPage để paging
        int totalPage = shipperReviewDAO.getTotalPagesForShipperReviews(shipperId, pageSize);

        // check xem đơn hàng đã gia chưa
        boolean checkOrder = orderDAO.isOrderDelivered(orderId);
        // chekc xem đã đánh giá shipper cho đơn hàng đấy chưa
        boolean checkReviewShipper = shipperReviewDAO.isReviewExist(orderId);
        // kiểm tra
        if(checkOrder && ! checkReviewShipper){
            request.setAttribute("checkOrder", true);
        }
        
        request.setAttribute("totalReview", totalReview);
        request.setAttribute("shipperReviews", shipperReviews);
        request.setAttribute("totalRating", totalRating);
        request.setAttribute("currentPage", page);
        request.setAttribute("shipper", shipper);
        request.setAttribute("totalPage", totalPage);

        request.getRequestDispatcher("shipper-review.jsp").forward(request, response);

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
        processRequest(request, response);
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
