/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package support.controller;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author Giang123
 */
public class ViewSupportRequestsController extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
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
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        int customerId = Integer.parseInt(request.getParameter("customerId"));

        // Lấy danh sách yêu cầu hỗ trợ của khách hàng từ cơ sở dữ liệu
        List<SupportRequest> requests = getSupportRequestsByCustomer(customerId);
        request.setAttribute("requests", requests);
        
        // Chuyển hướng đến trang JSP để hiển thị danh sách yêu cầu
        RequestDispatcher dispatcher = request.getRequestDispatcher("viewSupportRequests.jsp");
        dispatcher.forward(request, response);
    }

    private List<SupportRequest> getSupportRequestsByCustomer(int customerId) {
        List<SupportRequest> requests = new ArrayList<>();
        
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS)) {
            String sql = "SELECT * FROM SupportRequests WHERE CustomerID = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, customerId);
                try (ResultSet rs = stmt.executeQuery()) {
                    while (rs.next()) {
                        SupportRequest request = new SupportRequest();
                        request.setRequestID(rs.getInt("RequestID"));
                        request.setSubject(rs.getString("Subject"));
                        request.setMessage(rs.getString("Message"));
                        request.setStatus(rs.getString("Status"));
                        request.setCreatedAt(rs.getTimestamp("CreatedAt"));
                        request.setUpdatedAt(rs.getTimestamp("UpdatedAt"));
                        requests.add(request);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return requests;
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
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
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
