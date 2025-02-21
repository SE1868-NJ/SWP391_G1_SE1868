/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.login;

import Utils.UserUtils;
import Utils.VNPayUtils;
import config.ConfigVNPay;
import entity.Customer;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.time.LocalDate;
import models.CustomerDAO;
import services.EmailService;

/**
 *
 * @author Đạt
 */
@WebServlet(name = "RegisterServlet", urlPatterns = {"/register"})
public class RegisterServlet extends HttpServlet {

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
            out.println("<title>Servlet RegisterServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet RegisterServlet at " + request.getContextPath() + "</h1>");
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
//        processRequest(request, response);
        // Lấy dữ liệu từ form
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String phoneNumber = request.getParameter("phoneNumber");
        String address = request.getParameter("address");
        String birthDateStr = request.getParameter("birthDate");
        String gender = request.getParameter("gender");
        String password = request.getParameter("password");
        String repeatPassword = request.getParameter("repeatPassword");
        String checkTerms = request.getParameter("check"); // Trả về "on" nếu được chọn, null nếu không

        // Biến lỗi
        String error_name = "";
        String error_email = "";
        String error_phone = "";
        String error_address = "";
        String error_birthDate = "";
        String error_gender = "";
        String error_password = "";
        String error_repeatPassword = "";
        String error_check = "";

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
        } else if (UserUtils.checkEmailExists(email)) {
            error_email = "Email này đã được sử dụng!";
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

        // Kiểm tra ngày sinh 
        if (birthDateStr == null || birthDateStr.trim().isEmpty()) {
            error_birthDate = "Ngày sinh không được để trống!";
            hasError = true;
        } else {
            try {
                LocalDate birthDate = LocalDate.parse(birthDateStr);
                if (!UserUtils.isValidAge(birthDate, 15)) {
                    error_birthDate = "Bạn phải đủ ít nhất 15 tuổi để đăng ký!";
                    hasError = true;
                }
            } catch (Exception e) {
                error_birthDate = "Ngày sinh không đúng định dạng!";
                hasError = true;
            }
        }

        // Kiểm tra giới tính
        if (gender == null || gender.trim().isEmpty()) {
            error_gender = "Vui lòng chọn giới tính!";
            hasError = true;
        }

        // Kiểm tra mật khẩu
        if (password == null || password.trim().isEmpty()) {
            error_password = "Mật khẩu không được để trống!";
            hasError = true;
        } else if (!UserUtils.isPasswordValid(password)) {
            error_password = "Mật khẩu phải có ít nhất 8 ký tự, chứa ít nhất một số và một ký tự đặc biệt!";
            hasError = true;
        }

        // Kiểm tra nhập lại mật khẩu
        if (repeatPassword == null || repeatPassword.trim().isEmpty()) {
            error_repeatPassword = "Vui lòng nhập lại mật khẩu!";
            hasError = true;
        } else if (!UserUtils.isRepeatPasswordValid(password, repeatPassword)) {
            error_repeatPassword = "Mật khẩu nhập lại không khớp!";
            hasError = true;
        }

        // Kiểm tra đồng ý điều khoản
        if (checkTerms == null) {
            error_check = "Bạn phải đồng ý với điều khoản trước khi đăng ký!";
            hasError = true;
        }

        // Nếu có lỗi, quay lại form đăng ký và hiển thị lỗi
        if (hasError) {
            request.setAttribute("error_name", error_name);
            request.setAttribute("error_email", error_email);
            request.setAttribute("error_phone", error_phone);
            request.setAttribute("error_address", error_address);
            request.setAttribute("error_birthDate", error_birthDate);
            request.setAttribute("error_gender", error_gender);
            request.setAttribute("error_password", error_password);
            request.setAttribute("error_repeatPassword", error_repeatPassword);
            request.setAttribute("error_check", error_check);
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return;
        } else {

            // Nếu không có lỗi, xử lý đăng ký thành công
            // Khai báo CUsDAO
            CustomerDAO customerDAO = new CustomerDAO();

            // tao customer
            Customer customer = new Customer(0, name, email, password, phoneNumber, address,
                    LocalDate.parse(birthDateStr), gender, null, null, null, false);

            // khởi tạo session
            HttpSession session = request.getSession();

            //lưu mail lên session
            session.setAttribute("email", email);

            // check mail đã tồn tài chưa (với mail chưa verify)
            if (customerDAO.getByEmail(email) == null) {
                // thêm customer vô db với Isverfy = 0
                customerDAO.addCustomerSHA512(customer);
            }else{
                
                // set lại id vô cutomer để update
                customer.setCustomerId(customerDAO.getByEmail(email).getCustomerId());
                
                // update lại thông tin
                customerDAO.updateCustomer(customer);
            }

            // tạo mã token
            String token = VNPayUtils.hmacSHA512(ConfigVNPay.vnp_HashSecret, password);

            // link để comfirm
            String linkComfirm = "http://localhost:9999/verify?token=" + token;
            
            // Nội dung email HTML
            String emailContent = "<html><body>"
                    + "<h2>Xác nhận tài khoản của bạn</h2>"
                    + "<p>Chào <strong>" + name + "</strong>,</p>"
                    + "<p>Cảm ơn bạn đã đăng ký tài khoản. Vui lòng xác nhận email của bạn bằng cách nhấn vào nút bên dưới:</p>"
                    + "<a href='" + linkComfirm + "' style='display: inline-block; padding: 12px 20px; font-size: 18px; color: white; background-color: #28a745; text-decoration: none; border-radius: 5px; font-weight: bold;'>Xác nhận tài khoản</a>"
                    + "<p>Nếu bạn không đăng ký tài khoản này, vui lòng bỏ qua email này.</p>"
                    + "<p>Trân trọng,</p>"
                    + "<p><strong>Đội ngũ hỗ trợ</strong></p>"
                    + "</body></html>";

            //gửi mail
            
            // khởi tạo EmailService
            EmailService emailService = new EmailService();

            // gửi mail
            if (emailService.sendEmail(email, "Xác nhận tài khoản của bạn", emailContent)) {

                response.sendRedirect("login.jsp?success=true");

            } else {

                response.sendRedirect("login.jsp?success=false");
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
