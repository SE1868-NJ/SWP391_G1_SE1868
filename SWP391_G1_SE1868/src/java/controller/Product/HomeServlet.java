/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller.Product;

import entity.Customer;
import entity.Order;
import entity.Product;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import models.CustomerDAO;
import models.OrderDAO;
import models.ProductDAO;
import response.VNPayResponse;
import services.VNPayService;

/**
 *
 * @author Đạt
 */
@WebServlet(name="HomeServlet", urlPatterns={"/home"})
public class HomeServlet extends HttpServlet {
   
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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet HomeServlet</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet HomeServlet at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
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
//        processRequest(request, response);
        

// khái báo dao product

    ProductDAO productDAO = new ProductDAO();
    
    // list product có avg raing cao nhất
        List<Product> products  = productDAO.getTopRatedProducts(8);

        request.setAttribute("products", products);



///// xử lí checkOut thanh toán thành công  với vnpay
        // Lấy dữ liệu từ request.getParameterMap()
        Map<String, String> fields = new HashMap<>();

        // entrySet() trả về một Set<Map.Entry<String, String[]>
        for (Map.Entry<String, String[]> entry : request.getParameterMap().entrySet()) {
            //tập hợp các cặp entry (key, value)
            // puut voo fields
            fields.put(entry.getKey(), entry.getValue()[0]);

        }

        // khai báo DAO customer
        CustomerDAO customerDAO = new CustomerDAO();

        // khai báo order Dao 
        OrderDAO orderDAO = new OrderDAO();

        HttpSession session = request.getSession();

        // lấy đố tương cusomret ở session
        Customer customer = (Customer) session.getAttribute("user");

        // Lấy chữ ký bảo mật từ VNPay
        String vnp_SecureHash = request.getParameter("vnp_SecureHash");

        // Xác thực phản hồi từ VNPay
        VNPayResponse paymentResponse = VNPayService.validateResponse(fields, vnp_SecureHash);

        // check thanh toán (sờ fake bill) và kiểm tra trạng thái thanh toán thành công thì mới được đặt hàng
        if (paymentResponse != null && paymentResponse.getVnp_ResponseCode().equals("00")) {
            

            // tạo đơn hàng mới 
            boolean check = orderDAO.createOrderWithPaymentAndDetails(customer, customerDAO.getCartsByCustomerId(customer.getCustomerId()), customer.getAddress(), "Thanh toán ATM");

            // khai báo order
            Order order = orderDAO.getLatestOrderByCustomerId(customer.getCustomerId());

            // kiểm tra update thành công và lấy đơn hàng mới nhất của người dùng đấy
            if (check && order != null) {
                // update stock 
                boolean checkUpdateStock = orderDAO.updateProductStockFromCart(customerDAO.getCartsByCustomerId(customer.getCustomerId()));
                
                // check update kho thành công thì mới xóa cart
                if (checkUpdateStock) {
                    // tạo order thành công thì xóa cart
                    orderDAO.deleteCartBycustomerID(customer.getCustomerId());
                }
                request.setAttribute("statusOrder", "true");

            } else {
                request.setAttribute("statusOrder", "false");
            }

        }
        request.setAttribute("paymentResponse", paymentResponse);

        request.getRequestDispatcher("home.jsp").forward(request, response);

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
