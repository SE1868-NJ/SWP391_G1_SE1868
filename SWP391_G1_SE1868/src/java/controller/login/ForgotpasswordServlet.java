/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.login;

import Utils.UserUtils;
import entity.Customer;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.CustomerDAO;
import services.EmailService;

/**
 *
 * @author Đạt
 */
@WebServlet(name = "ForgotpasswordServlet", urlPatterns = {"/forgotpassword"})
public class ForgotpasswordServlet extends HttpServlet {

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
            out.println("<title>Servlet ForgotpasswordServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ForgotpasswordServlet at " + request.getContextPath() + "</h1>");
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
        processRequest(request, response);
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

        // biến lỗi 
        String error_email = "";

        boolean hasError = false; // Biến kiểm tra xem có lỗi không

        // Kiểm tra email
        if (email == null || email.trim().isEmpty()) {
            error_email = "Email không được để trống!";
            hasError = true;
        } else if (!UserUtils.isEmailValid(email)) {
            error_email = "Email không đúng định dạng!";
            hasError = true;
        } else if (!UserUtils.checkEmailExists(email)) {
            error_email = "Email này chưa được sử dụng!";
            hasError = true;
        }

        // Nếu có lỗi, chuyển hướng lại trang đăng nhập và hiển thị lỗi
        if (hasError) {
            request.setAttribute("error_email", error_email);
            request.getRequestDispatcher("forgotpassword.jsp").forward(request, response);
            return;
        } else {
            // khai bao dao
            CustomerDAO customerDAO = new CustomerDAO();

            Customer customer = customerDAO.checkEmailExists(email);

            // tạo mk ngẫu nhiên
            String randomPassword = UserUtils.generateRandomPassword(8);  // Gọi hàm tạo mật khẩu ngẫu nhiên

            // Nội dung email 
            String emailContent = "<html><body>"
                    + "<h2>Cung cấp mật khẩu của bạn</h2>"
                    + "<p>Chào <strong>" + customer.getFullName() + "</strong>,</p>"
                    + "<p>Cảm ơn bạn đã đăng ký tài khoản. Dưới đây là mật khẩu tạm thời của bạn:</p>"
                    + "<p><strong>Mật khẩu của bạn là: " + randomPassword + "</strong></p>"
                    + "<p>Vui lòng đăng nhập bằng mật khẩu này và thay đổi mật khẩu của bạn sau khi đăng nhập.</p>"
                    + "<p>Nếu bạn không đăng ký tài khoản này, vui lòng bỏ qua email này.</p>"
                    + "<p>Trân trọng,</p>"
                    + "<p><strong>Đội ngũ hỗ trợ</strong></p>"
                    + "</body></html>";

            //gửi mail
            // khởi tạo EmailService
            EmailService emailService = new EmailService();

            // gửi mail thành công
            if (emailService.sendEmail(email, "Cung cấp mật khẩu", emailContent)) {

                // set lại mk của người dùng
                customer.setPassword(randomPassword);
                
                // set lại thời gian update
                customer.setUpdatedAt(null);
                
                //update mk người dùng

                if (customerDAO.updateCustomer(customer)) {
                    response.sendRedirect("login.jsp?passWord=true");
                } else {
                    response.sendRedirect("login.jsp?passWord=false");
                }

            } else {
                response.sendRedirect("login.jsp?passWord=false");
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
