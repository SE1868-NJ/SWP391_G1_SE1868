/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package chatbot;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import 

/**
 *
 * @author Giang123
 */
public class ChatBotController extends HttpServlet {
   
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
        String customerMessage = request.getParameter("message");
        String botReply = getBotReply(customerMessage);  // Logic để bot phản hồi

        // Lưu cuộc trò chuyện vào cơ sở dữ liệu
        int customerId = Integer.parseInt(request.getParameter("customerId"));
        saveChatMessage(customerId, customerMessage, "Customer");
        saveChatMessage(customerId, botReply, "Bot");

        // Trả lời cho người dùng
        response.setContentType("application/json");
        response.getWriter().write("{\"response\": \"" + botReply + "\"}");
    }

    private void saveChatMessage(int customerId, String message, String sender) {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS)) {
            String sql = "INSERT INTO ChatMessages (CustomerID, Message, Sender) VALUES (?, ?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, customerId);
                stmt.setString(2, message);
                stmt.setString(3, sender);
                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
