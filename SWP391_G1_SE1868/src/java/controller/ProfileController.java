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
import dao.CustomerDAO;
import entity.Customer;
import java.sql.Date;
import jakarta.servlet.RequestDispatcher;

/**
 *
 * @author Giang123
 */
public class ProfileController extends HttpServlet {

    private final CustomerDAO customerDAO = new CustomerDAO();  // DAO instance to interact with database

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
        Customer customer = (Customer) session.getAttribute("customer");

        if (customer != null) {
            // Get the customer's details
            String fullName = customer.getFullName();
            String email = customer.getEmail();
            String phoneNumber = customer.getPhoneNumber();
            String address = customer.getAddress();
            Date birthDate = customer.getBirthDate();
            String gender = customer.getGender();

            // Display the customer profile details
            response.getWriter().println("Full Name: " + fullName);
            response.getWriter().println("Email: " + email);
            response.getWriter().println("Phone Number: " + phoneNumber);
            response.getWriter().println("Address: " + address);
            response.getWriter().println("Birth Date: " + birthDate);
            response.getWriter().println("Gender: " + gender);
        } else {
            response.getWriter().println("No customer session found.");
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
