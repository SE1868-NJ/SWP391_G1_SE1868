/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import models.CustomerDAO;
import entity.Customer;
import jakarta.servlet.annotation.WebServlet;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Giang123
 */
@WebServlet(name = "ProfileController", urlPatterns = {"/profile"})
public class ProfileController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    CustomerDAO customerDAO = new CustomerDAO();

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
        // Lấy session
        HttpSession session = request.getSession();
        Customer customer = (Customer) session.getAttribute("user");

        // Nếu chưa đăng nhập, chuyển hướng về trang login
        if (customer == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        // Lấy thông tin chi tiết của khách hàng từ database
        customer = customerDAO.getCustomerById(customer.getCustomerId());
        request.setAttribute("customer", customer);
        request.getRequestDispatcher("profile.jsp").forward(request, response);
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
        HttpSession session = request.getSession();
        Customer customer = (Customer) session.getAttribute("user");

        if (customer != null) {
            try {
                customer.setFullName(request.getParameter("fullName"));
                customer.setPassword(request.getParameter("password"));
                customer.setPhoneNumber(request.getParameter("phoneNumber"));
                customer.setAddress(request.getParameter("address"));
                customer.setBirthDate(LocalDate.parse(request.getParameter("birthdate")));
                customer.setGender(request.getParameter("gender"));
                customer.setProfileImage(request.getParameter("profileImage"));

                if (customerDAO.updateCustomer(customer)) {
                    session.setAttribute("customer", customer); // Cập nhật session
                    response.sendRedirect("/profile");
                } else {
                    response.sendRedirect("updateprofile.jsp?error=true");
                }
            } catch (IOException e) {
                response.sendRedirect("updateprofile.jsp?error=true");
            }
        } else {
            response.sendRedirect("login.jsp"); // Quay lại trang đăng nhập nếu chưa có session
        }
    }
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
