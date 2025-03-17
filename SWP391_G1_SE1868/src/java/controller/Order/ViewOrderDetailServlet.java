/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.Order;

import entity.Customer;
import entity.Order;
import entity.OrderDetail;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.List;
import models.OrderDAO;
import models.OrderDetailDAO;

/**
 *
 * @author Đạt
 */
@WebServlet(name = "ViewOrderDetailServlet", urlPatterns = {"/viewOrderDetail"})
public class ViewOrderDetailServlet extends HttpServlet {

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
            out.println("<title>Servlet ViewOrderDetailServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ViewOrderDetailServlet at " + request.getContextPath() + "</h1>");
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
        int orderId = Integer.parseInt(request.getParameter("orderId"));

        String sortOrderDetailBy = request.getParameter("sortOrderDetailBy") != null ? request.getParameter("sortOrderDetailBy") : "quantity";
        String sortOrderDetail = request.getParameter("sortOrderDetail") != null ? request.getParameter("sortOrderDetail") : "DESC";
        // lấy sessiong customer
        HttpSession session = request.getSession();

        // lấy đố tương cusomret ở session
               Customer customer = (Customer) session.getAttribute("user");
        // check custoemer
        if (customer == null) {
            response.sendRedirect("login.jsp");
            return;
        }
        // khai báo orderDetailDAO
        OrderDetailDAO orderDetailDAO = new OrderDetailDAO();
        
        // khai báo orderDAO
        OrderDAO orderDAO = new OrderDAO();
       
        // lấy list OrderDetail theo orderId
        List<OrderDetail> details = orderDetailDAO.getOrderDetailsByOrderIdSorted(orderId, sortOrderDetailBy, sortOrderDetail);
        
        // check xem đơn hàng có trạng tái đã giao chưa và người dùng đã đánh giá sản phẩm chưa 
        
        boolean checkOrder = orderDAO.isOrderDelivered(orderId);
        boolean[] checkReview  = orderDetailDAO.checkOrderDetailsReviewed(details);
        
        if (checkOrder) {
             request.setAttribute("checkOrder", true);
             request.setAttribute("checkReview", checkReview);
        }

        request.setAttribute("details", details);
        // Gửi giá trị đến JSP
        request.setAttribute("sortOrderDetailBy", sortOrderDetailBy);
        request.setAttribute("sortOrderDetail", sortOrderDetail);
        
        request.setAttribute("totalOrderDetail", orderDetailDAO.calculateTotalAmountByOrderId(orderId));
        
        request.getRequestDispatcher("view-orderdetail.jsp").forward(request, response);

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
