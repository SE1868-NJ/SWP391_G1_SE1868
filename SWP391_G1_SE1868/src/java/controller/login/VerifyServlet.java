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
import jakarta.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.CustomerDAO;

/**
 *
 * @author Đạt
 */
@WebServlet(name = "VerifyServlet", urlPatterns = {"/verify"})
public class VerifyServlet extends HttpServlet {

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
            out.println("<title>Servlet VerifyServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet VerifyServlet at " + request.getContextPath() + "</h1>");
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
        String token = request.getParameter("token");

        // khởi tạo session
        HttpSession session = request.getSession();

        //lấy mail từ session
        String email = (session.getAttribute("email") != null) ? session.getAttribute("email").toString().trim() : "";


        // khởi tạo DaoCuss
        CustomerDAO customerDAO = new CustomerDAO();

        // lấy đối tượng theo mail
        Customer customer = customerDAO.getByEmail(email);
        

        if (!email.isEmpty() && customer != null) {
            // xóa email ở session
            session.removeAttribute("email");
            
            try {
                // check thời gian từ lúc gửi mail đến lúc comfirm <= 15 min
                if (UserUtils.isUpdatedAtValid(customer.getCustomerId(), 15)) {
                    
                    
                    // để IsVerify = true (người dùng đã xác minh)
                    customer.setIsVerify(true);
                    
                    // update người dùng đã xác thực mail
                    customerDAO.updateCustomerWithVerify(customer);
                    
                    response.sendRedirect("login.jsp?Isverify=true");
                }else{
                    response.sendRedirect("login.jsp?Isverify=false");
                }
                
            } catch (SQLException ex) {
                Logger.getLogger(VerifyServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }else{
            response.sendRedirect("home");
        }

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
