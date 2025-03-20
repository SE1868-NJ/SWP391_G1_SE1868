/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import entity.Customer;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import models.CustomerDAO;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import Utils.ValidateUtils;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;




/**
 *
 * @author Giang123
 */
@WebServlet(name = "ProfileController", urlPatterns = {"/profile"})
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024,    // 1 MB
        maxFileSize = 1024 * 1024 * 5,      // 5 MB
        maxRequestSize = 1024 * 1024 * 10    // 10 MB
)
public class ProfileController extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(ProfileController.class.getName());
    private final CustomerDAO customerDAO = new CustomerDAO();
    private static final String UPLOAD_DIR = "SWP391_G1_SE1868" + File.separator + "web" + File.separator + "assets" + File.separator + "img" + File.separator + "profile";
    private static final String DB_IMAGE_PATH = "assets/img/profile";
    private final Gson gson = new Gson();


//public class ProfileController extends HttpServlet {
//
//    private static final long serialVersionUID = 1L;
//    CustomerDAO customerDAO = new CustomerDAO();

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
        HttpSession session = request.getSession();
        Customer customer = (Customer) session.getAttribute("user");

        if (customer == null) {
            LOGGER.log(Level.INFO, "Unauthorized access attempt to profile page");
            response.sendRedirect("login.jsp");
            return;
        }

        customer = customerDAO.getCustomerById(customer.getCustomerId());
        if (customer == null) {
            LOGGER.log(Level.SEVERE, "Failed to retrieve customer data from database");
            session.setAttribute("errorMessage", "Có lỗi xảy ra khi tải thông tin người dùng");
            response.sendRedirect("error.jsp");
            return;
        }

        request.setAttribute("customer", customer);
        request.getRequestDispatcher("profile.jsp").forward(request, response);
    }

//        // Lấy session
//        HttpSession session = request.getSession();
//        Customer customer = (Customer) session.getAttribute("user");
//
//        // Nếu chưa đăng nhập, chuyển hướng về trang login
//        if (customer == null) {
//            response.sendRedirect("login.jsp");
//            return;
//        }
//
//        // Lấy thông tin chi tiết của khách hàng từ database
//        customer = customerDAO.getCustomerById(customer.getCustomerId());
//        request.setAttribute("customer", customer);
//        request.getRequestDispatcher("profile.jsp").forward(request, response);
//    }

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
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        Customer customer = (Customer) session.getAttribute("user");
        Map<String, String> errors = new HashMap<>();

        try {
            // Handle profile image upload first
            Part filePart = request.getPart("profileImage");
            if (filePart != null && filePart.getSize() > 0) {
                LOGGER.log(Level.INFO, "Processing image upload for customer {0}", customer.getCustomerId());

                if (!ValidateUtils.isValidImageFile(filePart)) {
                    errors.put("profileImage", "File ảnh không hợp lệ (chỉ chấp nhận JPG, PNG dưới 5MB)");
                    LOGGER.log(Level.WARNING, "Invalid image file uploaded by customer {0}", customer.getCustomerId());
                } else {
                    try {
                        // Create unique filename with timestamp
                        String fileName = System.currentTimeMillis() + "_" + filePart.getSubmittedFileName();
                        LOGGER.log(Level.INFO, "Generated filename: {0}", fileName);

                        // Ensure upload directory exists
                        Path uploadPath = Paths.get(UPLOAD_DIR);
                        LOGGER.log(Level.INFO, "Upload directory path: {0}", uploadPath.toAbsolutePath());

                        if (!Files.exists(uploadPath)) {
                            Files.createDirectories(uploadPath);
                            LOGGER.log(Level.INFO, "Created upload directory at: {0}", uploadPath);
                        }

                        // Save the file
                        Path filePath = uploadPath.resolve(fileName);
                        LOGGER.log(Level.INFO, "Saving file to: {0}", filePath);
                        Files.copy(filePart.getInputStream(), filePath);

                        // Verify file was saved
                        if (Files.exists(filePath)) {
                            LOGGER.log(Level.INFO, "File successfully saved at: {0}", filePath);

                            // Set the URL path for database (relative to web context)
                            String dbImagePath = DB_IMAGE_PATH + "/" + fileName;
                            customer.setProfileImage(dbImagePath);
                            LOGGER.log(Level.INFO, "Updated database image path to: {0}", dbImagePath);
                        } else {
                            throw new IOException("File was not saved successfully");
                        }
                    } catch (IOException e) {
                        LOGGER.log(Level.SEVERE, "Failed to save uploaded file", e);
                        errors.put("profileImage", "Không thể lưu file ảnh. Vui lòng thử lại sau.");
                    }
                }
            }

            // Get and validate form data
            String fullName = request.getParameter("fullName");
            String phoneNumber = request.getParameter("phoneNumber");
            String address = request.getParameter("address");
            String birthDateStr = request.getParameter("birthDate");
            String gender = request.getParameter("gender");
            String password = request.getParameter("password");

            // Validate all fields
            if (!ValidateUtils.isValidName(fullName)) {
                errors.put("fullName", "Tên phải từ 2-50 ký tự");
            }
            if (!ValidateUtils.isValidPhone(phoneNumber)) {
                errors.put("phoneNumber", "Số điện thoại không hợp lệ (10-11 số)");
            }
            if (!ValidateUtils.isValidAddress(address)) {
                errors.put("address", "Địa chỉ không được để trống và tối đa 200 ký tự");
            }
            if (!ValidateUtils.isValidDate(birthDateStr)) {
                errors.put("birthDate", "Ngày sinh không hợp lệ (phải từ 10-100 tuổi)");
            }
            if (!ValidateUtils.isValidGender(gender)) {
                errors.put("gender", "Giới tính không hợp lệ");
            }
            if (password != null && !password.trim().isEmpty() && !ValidateUtils.isValidPassword(password)) {
                errors.put("password", "Mật khẩu phải có ít nhất 8 ký tự và chứa số");
            }

            // If there are validation errors
            if (!errors.isEmpty()) {
                LOGGER.log(Level.WARNING, "Validation errors: {0}", errors);
                request.setAttribute("errors", errors);
                request.setAttribute("customer", customer);
                request.getRequestDispatcher("profile.jsp").forward(request, response);
                return;
            }

            // Update customer object
            customer.setFullName(fullName);
            customer.setPhoneNumber(phoneNumber);
            customer.setAddress(address);
            customer.setBirthDate(LocalDate.parse(birthDateStr));
            customer.setGender(gender);
            if (password != null && !password.trim().isEmpty()) {
                customer.setPassword(password);
            }

            // Save to database
            if (customerDAO.updateCustomer(customer)) {
                LOGGER.log(Level.INFO, "Profile updated successfully for customer ID: {0}", customer.getCustomerId());
                session.setAttribute("user", customer);
                session.setAttribute("successMessage", "Cập nhật thông tin thành công!");
                response.sendRedirect("profile");
            } else {
                LOGGER.log(Level.SEVERE, "Failed to update profile for customer ID: {0}", customer.getCustomerId());
                errors.put("general", "Có lỗi xảy ra khi cập nhật thông tin");
                request.setAttribute("errors", errors);
                request.setAttribute("customer", customer);
                request.getRequestDispatcher("profile.jsp").forward(request, response);
            }

        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Error in profile update", e);
            errors.put("general", "Có lỗi xảy ra, vui lòng thử lại sau");
            request.setAttribute("errors", errors);
            request.setAttribute("customer", customer);
            request.getRequestDispatcher("profile.jsp").forward(request, response);
        }
    }

    private void sendJsonResponse(HttpServletResponse response, boolean success, String message, Map<String, String> errors) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        JsonObject jsonResponse = new JsonObject();
        jsonResponse.addProperty("success", success);
        jsonResponse.addProperty("message", message);
        if (errors != null) {
            JsonObject jsonErrors = new JsonObject();
            errors.forEach(jsonErrors::addProperty);
            jsonResponse.add("errors", jsonErrors);
        }

        PrintWriter out = response.getWriter();
        out.print(gson.toJson(jsonResponse));
        out.flush();
    }
    
//        HttpSession session = request.getSession();
//        Customer customer = (Customer) session.getAttribute("user");
//
//        if (customer != null) {
//            try {
//                customer.setFullName(request.getParameter("fullName"));
//                customer.setPassword(request.getParameter("password"));
//                customer.setPhoneNumber(request.getParameter("phoneNumber"));
//                customer.setAddress(request.getParameter("address"));
//                customer.setBirthDate(LocalDate.parse(request.getParameter("birthdate")));
//                customer.setGender(request.getParameter("gender"));
//                customer.setProfileImage(request.getParameter("profileImage"));
//
//                if (customerDAO.updateCustomer(customer)) {
//                    session.setAttribute("customer", customer); // Cập nhật session
//                    response.sendRedirect("/profile");
//                } else {
//                    response.sendRedirect("updateprofile.jsp?error=true");
//                }
//            } catch (IOException e) {
//                response.sendRedirect("updateprofile.jsp?error=true");
//            }
//        } else {
//            response.sendRedirect("login.jsp"); // Quay lại trang đăng nhập nếu chưa có session
//        }
//    }
//        HttpSession session = request.getSession();
//        Customer customer = (Customer) session.getAttribute("customer");
//
//        if (customer != null) {
//            // Get the customer's details
//            String fullName = customer.getFullName();
//            String email = customer.getEmail();
//            String phoneNumber = customer.getPhoneNumber();
//            String address = customer.getAddress();
//            Date birthDate = customer.getBirthDate();
//            String gender = customer.getGender();
//
//            // Display the customer profile details
//            response.getWriter().println("Full Name: " + fullName);
//            response.getWriter().println("Email: " + email);
//            response.getWriter().println("Phone Number: " + phoneNumber);
//            response.getWriter().println("Address: " + address);
//            response.getWriter().println("Birth Date: " + birthDate);
//            response.getWriter().println("Gender: " + gender);
//        } else {
//            response.getWriter().println("No customer session found.");
//        }

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
