/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package chatbot;

import com.sun.jdi.connect.spi.Connection;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Giang123
 */
public class ViewChatHistoryController extends HttpServlet {
   
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
        
        // Lấy lịch sử chat từ cơ sở dữ liệu
        List<ChatMessage> chatHistory = getChatHistory(customerId);
        request.setAttribute("chatHistory", chatHistory);
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("chatbot.jsp");
        dispatcher.forward(request, response);
    }

    private List<ChatMessage> getChatHistory(int customerId) {
        List<ChatMessage> chatHistory = new ArrayList<>();
        
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS)) {
            String sql = "SELECT * FROM ChatMessages WHERE CustomerID = ? ORDER BY Timestamp ASC";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, customerId);
                try (ResultSet rs = stmt.executeQuery()) {
                    while (rs.next()) {
                        ChatMessage message = new ChatMessage();
                        message.setSender(rs.getString("Sender"));
                        message.setMessage(rs.getString("Message"));
                        message.setTimestamp(rs.getTimestamp("Timestamp"));
                        chatHistory.add(message);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return chatHistory;
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
