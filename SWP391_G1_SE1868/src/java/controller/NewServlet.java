/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;


import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import response.VNPayResponse;
import services.VNPayService;

/**
 *
 * @author Đạt
 */
@WebServlet(name = "NewServlet", urlPatterns = {"/NewServlet"})
public class NewServlet extends HttpServlet {

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
            out.println("<title>Servlet NewServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet NewServlet at " + request.getContextPath() + "</h1>");
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
      //  processRequest(request, response);
        
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
       Map<String, String> fields = new HashMap<>();
    for (Map.Entry<String, String[]> entry : request.getParameterMap().entrySet()) {
        fields.put(entry.getKey(), entry.getValue()[0]);
    }
   
    // Lấy chữ ký bảo mật từ VNPay
    String vnp_SecureHash = request.getParameter("vnp_SecureHash");

    // Xác thực phản hồi từ VNPay
    VNPayResponse paymentResponse = VNPayService.validateResponse(fields, vnp_SecureHash);

    if (paymentResponse != null) {
        // Phản hồi hợp lệ, xử lý giao dịch 
        String txnRef = paymentResponse.getVnp_TxnRef();
        String amount = paymentResponse.getVnp_Amount();
        String orderInfo = paymentResponse.getVnp_OrderInfo();
        String responseCode = paymentResponse.getVnp_ResponseCode();

        if ("00".equals(responseCode)) {
            // Giao dịch thành công
            response.getWriter().write("Thanh toán thành công! Mã giao dịch: " + txnRef);
        } else {
            // Giao dịch thất bại
            response.getWriter().write("Thanh toán thất bại! Mã phản hồi: " + responseCode);
        }
    } else {
        // Phản hồi không hợp lệ
        response.getWriter().write("Phản hồi không hợp lệ từ VNPay.");
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
