/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.review;

import entity.Customer;
import entity.OrderDetail;
import entity.ProductReview;
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
import java.time.LocalDate;
import models.OrderDAO;
import models.ShipperReviewDAO;

/**
 *
 * @author Đạt
 */
@WebServlet(name = "AddShipperReviewServlet", urlPatterns = {"/addShipperReview"})
public class AddShipperReviewServlet extends HttpServlet {

    ShipperReviewDAO shipperReviewDAO = new ShipperReviewDAO();
    OrderDAO orderDAO = new OrderDAO();

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
            out.println("<title>Servlet AddShipperReviewServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddShipperReviewServlet at " + request.getContextPath() + "</h1>");
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
        // processRequest(request, response);

        // lấy orderDetailId
        int orderId = Integer.parseInt(request.getParameter("orderId"));
        int shipperId = Integer.parseInt(request.getParameter("shipperId"));

        // truyền carts của customer
        HttpSession session = request.getSession();

        // lấy đố tương cusomret ở session
        Customer customer = (Customer) session.getAttribute("user");
        // check custoemer
        if (customer == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        // lấy shipper
        Shipper shipper = shipperReviewDAO.getShipperById(shipperId);

        request.setAttribute("shipper", shipper);
        request.getRequestDispatcher("addShipperReview.jsp").forward(request, response);
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
        //  processRequest(request, response);

        int orderId = Integer.parseInt(request.getParameter("orderId"));
        int shipperId = Integer.parseInt(request.getParameter("shipperId"));
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

        // lấy taoj shipper review
        
        ShipperReview shipperReview =  new ShipperReview(0, shipperReviewDAO.getShipperById(shipperId), customer, orderDAO.getOrderById(orderId), rating, comment, LocalDate.MAX);
        
        // Cập nhật thông tin vào cơ sở dữ liệu
        boolean success = shipperReviewDAO.addReview(shipperReview);

        // Truyền giá trị thành công qua query parameter
        String successParam = success ? "true" : "false";
        response.sendRedirect("viewOrder?success=" + successParam);
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
