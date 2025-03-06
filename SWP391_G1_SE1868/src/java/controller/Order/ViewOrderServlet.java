/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.Order;

import entity.Customer;
import entity.Order;
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

/**
 *
 * @author Đạt
 */
@WebServlet(name = "ViewOrderServlet", urlPatterns = {"/viewOrder"})
public class ViewOrderServlet extends HttpServlet {

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
            out.println("<title>Servlet ViewOrderServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ViewOrderServlet at " + request.getContextPath() + "</h1>");
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
        String startDateParam = request.getParameter("startDate");
        String endDateParam = request.getParameter("endDate");

        LocalDate startDate = (startDateParam != null && !startDateParam.isEmpty()) ? LocalDate.parse(startDateParam) : null;
        LocalDate endDate = (endDateParam != null && !endDateParam.isEmpty()) ? LocalDate.parse(endDateParam) : null;

        String page = request.getParameter("page") != null ? request.getParameter("page") : "1";
        int pageSize = 5;
        String sortBy = request.getParameter("sortBy") != null ? request.getParameter("sortBy") : "orderDate";
        String sortOrder = request.getParameter("sortOrder") != null ? request.getParameter("sortOrder") : "DESC";
        // lấy sessiong customer
        HttpSession session = request.getSession();

        // lấy đố tương cusomret ở session
        //       Customer customer = (Customer) session.getAttribute("user");
//        // check custoemer
//        if (customer == null) {
//            response.sendRedirect("login.jsp");
//            return;
//        }
        // khai báo OrderDAO
        OrderDAO orderDAO = new OrderDAO();

        // lấy list odder theo customerID
        List<Order> orders = orderDAO.getOrdersByCustomerId(1, startDate, endDate, Integer.parseInt(page), pageSize, sortBy, sortOrder);

        // get totalPage
        int totalPage = orderDAO.getTotalOrderPages(1, startDate, endDate, pageSize);

        request.setAttribute("orders", orders);
        request.setAttribute("totalPage", totalPage);
        request.setAttribute("currentPage", page);

        
        // Gửi giá trị đến JSP
        request.setAttribute("sortBy", sortBy);
        request.setAttribute("sortOrder", sortOrder);
        
        
        request.getRequestDispatcher("view-order.jsp").forward(request, response);

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
