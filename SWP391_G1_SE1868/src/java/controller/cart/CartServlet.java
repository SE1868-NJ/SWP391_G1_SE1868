/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.cart;

import entity.Cart;
import entity.Customer;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import models.CartDAO;

/**
 *
 * @author Đạt
 */
@WebServlet(name = "CartServlet", urlPatterns = {"/cart"})
public class CartServlet extends HttpServlet {

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
            out.println("<title>Servlet CartServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CartServlet at " + request.getContextPath() + "</h1>");
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
        // processRequest(request, response);

        // lấy sessiong customer
        HttpSession session = request.getSession();

        // lấy đố tương cusomret ở session
        Customer customer = (Customer) session.getAttribute("user");
        // check custoemer
        if (customer == null) {
            response.sendRedirect("login.jsp");
            return;
        }
        // khai báo CartDAo
        CartDAO cartDAO = new CartDAO();

        // lấy ra list Cart của customer đấu
        List<Cart> carts = cartDAO.getCartByCustomerId(customer.getCustomerId());

        // Lưu số lượng sản phẩm vào session
        session.setAttribute("cart", cartDAO.getTotalCartQuantity(customer.getCustomerId()));
        
        request.setAttribute("carts", carts);
        // truyền tổng giá tiền
        request.setAttribute("totalCart", cartDAO.getTotalAmount(customer.getCustomerId()));
        request.getRequestDispatcher("cart.jsp").forward(request, response);

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
        // processRequest(request, response);

        String productId = request.getParameter("productId");
        String quantity = request.getParameter("quantity");
        String action = request.getParameter("action");

        // lấy sessiong customer
        HttpSession session = request.getSession();

        // lấy đố tương cusomret ở session
        Customer customer = (Customer) session.getAttribute("user");
        // check custoemer
        if (customer == null) {
            response.sendRedirect("login.jsp");
            return;
        }
        // khai báo CartDAo
        CartDAO cartDAO = new CartDAO();

        // check action
        if (action == null) {
            action = "";
        }

        if (action.equals("add") && !productId.trim().isEmpty() && productId != null) {
            boolean check = cartDAO.addToCart(1, Integer.parseInt(productId), Integer.parseInt(quantity));
            if (check) {
                // thêm thành công
                request.setAttribute("add", true);
            }
        } else if (action.equals("update") && !productId.trim().isEmpty() && productId != null) {
            // update giỏ hàng
            boolean check = cartDAO.updateQuantity(customer.getCustomerId(), Integer.parseInt(productId), Integer.parseInt(quantity));
            if (check) {
                // thêm thành công
                request.setAttribute("update", true);
            }

        } else if (action.equals("remove") && !productId.trim().isEmpty() && productId != null) {
            // xoas  khỏi giỏ hàng
            boolean check = cartDAO.removeFromCart(customer.getCustomerId(), Integer.parseInt(productId));
            if (check) {
                // thêm thành công
                request.setAttribute("remove", true);
            }
        }

        // lấy ra list Cart của customer đấu
        List<Cart> carts = cartDAO.getCartByCustomerId(customer.getCustomerId());
        
        

        // Lưu số lượng sản phẩm vào session
        session.setAttribute("cart", cartDAO.getTotalCartQuantity(customer.getCustomerId()));



        request.setAttribute("carts", carts);
        // truyền tổng giá tiền
        request.setAttribute("totalCart", cartDAO.getTotalAmount(customer.getCustomerId()));
        request.getRequestDispatcher("cart.jsp").forward(request, response);
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
