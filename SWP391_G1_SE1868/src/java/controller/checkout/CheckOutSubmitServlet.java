/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.checkout;

import Utils.UserUtils;
import entity.Cart;
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
import models.CustomerDAO;
import models.OrderDAO;
import models.ShipperDAO;
import services.VNPayService;

/**
 *
 * @author Đạt
 */
@WebServlet(name = "CheckOutSubmitServlet", urlPatterns = {"/checkoutsubmit"})
public class CheckOutSubmitServlet extends HttpServlet {

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
            out.println("<title>Servlet CheckOutSubmitServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CheckOutSubmitServlet at " + request.getContextPath() + "</h1>");
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
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String phoneNumber = request.getParameter("phone");
        String address = request.getParameter("address");
        String paymentMethod = request.getParameter("paymentMethod");

        // Biến lỗi
        String error_name = "";
        String error_email = "";
        String error_phone = "";
        String error_address = "";

        // lấy sessiong customer
        HttpSession session = request.getSession();

        // lấy đố tương cusomret ở session
        Customer customer = (Customer) session.getAttribute("user");

        // check custoemer
        if (customer == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        boolean hasError = false;

        // Kiểm tra tên
        if (!UserUtils.isNameValid(name)) {
            error_name = "Tên không được để trống!";
            hasError = true;
        }

        // Kiểm tra email
        if (email == null || email.trim().isEmpty()) {
            error_email = "Email không được để trống!";
            hasError = true;
        } else if (!UserUtils.isEmailValid(email)) {
            error_email = "Email không đúng định dạng!";
            hasError = true;
        }

        // Kiểm tra số điện thoại
        if (phoneNumber == null || phoneNumber.trim().isEmpty()) {
            error_phone = "Số điện thoại không được để trống!";
            hasError = true;
        } else if (!UserUtils.isPhoneValid(phoneNumber)) {
            error_phone = "Số điện thoại không hợp lệ! (Phải bắt đầu bằng 0 và có 10 chữ số)";
            hasError = true;
        }

        // Kiểm tra địa chỉ
        if (address == null || address.trim().isEmpty()) {
            error_address = "Địa chỉ không được để trống!";
            hasError = true;
        }

        // Nếu có lỗi, quay lại form đăng ký và hiển thị lỗi
        if (hasError) {
            request.setAttribute("error_name", error_name);
            request.setAttribute("error_email", error_email);
            request.setAttribute("error_phone", error_phone);
            request.setAttribute("error_address", error_address);

            request.getRequestDispatcher("checkout.jsp").forward(request, response);
            return;
        } else {

            // khai báo DAO customer
            CustomerDAO customerDAO = new CustomerDAO();

            // khai báo order Dao 
            OrderDAO orderDAO = new OrderDAO();

            // lấy giá tiền tổng đơn hàng
            int total = Integer.parseInt(String.format("%.0f", customerDAO.getTotalAmount(customer.getCustomerId())));

            System.out.println(total);
            // thanh toán bằng ATM
            if (paymentMethod.equals("ATM")) {

                // gọi paymentService
                String urlVNPay = VNPayService.createPaymentUrl(total, name, request.getLocalAddr());
                response.sendRedirect(urlVNPay);
            } else {
                // thanh toán khi nhận hàng

                // tạo đơn hàng mới 
                boolean checkOrder = orderDAO.createOrderWithPaymentAndDetails(customer, customerDAO.getCartsByCustomerId(customer.getCustomerId()), address, "Thanh toán khi nhận hàng");

                // khai báo order
                Order order = orderDAO.getLatestOrderByCustomerId(customer.getCustomerId());

                // kiểm tra update thành công và lấy đơn hàng mới nhất của người dùng đấy
                if (checkOrder && order != null) {
                    // update stock 
                    boolean checkUpdateStock = orderDAO.updateProductStockFromCart(customerDAO.getCartsByCustomerId(customer.getCustomerId()));

                    // update stock thành công 
                    if (checkUpdateStock) {
                        //  update stock thành công thì xóa cart
                        orderDAO.deleteCartBycustomerID(customer.getCustomerId());
                    }

                    response.sendRedirect("home?statusOrder=true");
                } else {
                    response.sendRedirect("home?statusOrder=false");
                }

            }

        }
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
