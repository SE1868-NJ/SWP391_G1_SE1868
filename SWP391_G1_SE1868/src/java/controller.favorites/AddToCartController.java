/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Giang123
 */
@WebServlet(name="AddToCartController", urlPatterns={"/AddToCartController"})
public class AddToCartController extends HttpServlet {
        private static final Logger LOGGER = Logger.getLogger(AddToCartController.class.getName());

   
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
        response.setContentType("application/json;charset=UTF-8");

        try {
            HttpSession session = request.getSession(false);
            if (session == null || session.getAttribute("customer") == null) {
                response.setStatus(401);
                response.getWriter().write("{\"error\": \"Unauthorized\"}");
                return;
            }

            // Get cart from session or create new one
            Map<Integer, Integer> cart = (Map<Integer, Integer>) session.getAttribute("cart");
            if (cart == null) {
                cart = new HashMap<>();
            }

            // Get and validate product details
            int productId;
            int quantity;
            try {
                productId = Integer.parseInt(request.getParameter("productId"));
                quantity = Integer.parseInt(request.getParameter("quantity"));

                if (quantity < 1) {
                    throw new IllegalArgumentException("Quantity must be positive");
                }
            } catch (NumberFormatException e) {
                LOGGER.log(Level.WARNING, "Invalid product data: {0}", e.getMessage());
                response.setStatus(400);
                response.getWriter().write("{\"error\": \"Invalid product data\"}");
                return;
            }

            // Add/update cart
            cart.put(productId, cart.getOrDefault(productId, 0) + quantity);

            // Save updated cart to session
            session.setAttribute("cart", cart);

            // Create success response
            JsonObject jsonResponse = new JsonObject();
            jsonResponse.addProperty("status", "success");
            jsonResponse.addProperty("message", "Sản phẩm đã được thêm vào giỏ hàng");
            jsonResponse.addProperty("cartSize", cart.size());

            LOGGER.log(Level.INFO, "Product {0} added to cart. New quantity: {1}", 
                    new Object[]{productId, cart.get(productId)});

            response.getWriter().write(new Gson().toJson(jsonResponse));

        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error adding product to cart", e);
            response.setStatus(500);
            response.getWriter().write("{\"error\": \"Internal server error\", \"message\": \"" + e.getMessage() + "\"}");
        }
    
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
