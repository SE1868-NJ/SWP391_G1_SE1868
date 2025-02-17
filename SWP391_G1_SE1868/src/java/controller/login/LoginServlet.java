/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.login;

import Utils.UserUtils;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.CustomerDAO;
import entity.Customer;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpSession;
import com.google.gson.Gson;

/**
 *
 * @author Đạt
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {

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
            out.println("<title>Servlet LoginServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet LoginServlet at " + request.getContextPath() + "</h1>");
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

        String email = request.getParameter("email");  // Email hoặc Username
        String password = request.getParameter("password");  // Mật khẩu

        boolean hasError = false; // Biến kiểm tra xem có lỗi không

        // Kiểm tra email/username
        if (email == null || email.trim().isEmpty() || !UserUtils.isEmailValid(email)) {
            request.setAttribute("error_email", email);
            hasError = true; // Đánh dấu có lỗi
        }

        // Kiểm tra mật khẩu
        if (password == null || password.trim().isEmpty()) {
            request.setAttribute("error_password", "Password invalid.");
            hasError = true; // Đánh dấu có lỗi
        }

        // Nếu có lỗi, chuyển hướng lại trang đăng nhập và hiển thị lỗi
        if (hasError) {
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }

        CustomerDAO customerDAO = new CustomerDAO();

        Customer customer = customerDAO.login(email, password);
        if (customer != null) {
//            // Tạo session và lưu thông tin người dùng nếu đăng nhập thành công
//            
//            HttpSession session = request.getSession();
//            session.setAttribute("user", customer);  // Lưu đối tượng Customer vào session
//            response.sendRedirect("getReviews");  // Trang chính sau khi đăng nhập thành công

                // Chuyển đối tượng Customer thành JSON
            Gson gson = new Gson();
            String customerJson = gson.toJson(customer);

            // Tạo cookie lưu trữ JSON của Customer
            Cookie userCookie = new Cookie("user", customerJson);

            // Thiết lập thời gian sống của cookie (ví dụ: 1 giờ)
            userCookie.setMaxAge(60 * 60 * 24 * 3);  // 1 giờ
            userCookie.setPath("/");  // Lưu trữ trên toàn bộ ứng dụng

            // Gửi cookie về phía client
            response.addCookie(userCookie);

                // Chuyển hướng người dùng đến trang getReviews (hoặc trang khác)
            response.sendRedirect("getReviews");
        } else {
            // Nếu đăng nhập thất bại, chuyển hướng về trang login với thông báo lỗi
            request.setAttribute("error", "Incorrect email or password ");
            request.getRequestDispatcher("login.jsp").forward(request, response);
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
