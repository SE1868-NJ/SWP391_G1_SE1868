/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package wishlist;

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
public class AddToWishlistController extends HttpServlet {
   
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

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        int customerId = Integer.parseInt(request.getParameter("customerId"));
        int productId = Integer.parseInt(request.getParameter("productId"));

        // Lấy hoặc tạo một wishlist cho khách hàng
        int favoriteId = getFavoriteId(customerId);

        // Kiểm tra xem sản phẩm đã có trong wishlist chưa
        if (!isProductInWishlist(favoriteId, productId)) {
            addToWishlist(favoriteId, productId);
            response.sendRedirect("viewWishlist.jsp");
        } else {
            response.getWriter().println("Sản phẩm đã có trong Wishlist.");
        }
    }

    private int getFavoriteId(int customerId) {
        // Lấy favoriteId của khách hàng từ cơ sở dữ liệu
        // Nếu chưa có wishlist thì tạo mới và trả về
        return 1; // Ví dụ trả về id của wishlist
    }

    private boolean isProductInWishlist(int favoriteId, int productId) {
        // Kiểm tra sản phẩm có trong wishlist không
        return false; // Ví dụ: trả về false nếu sản phẩm chưa có trong wishlist
    }

    private void addToWishlist(int favoriteId, int productId) {
        // Thêm sản phẩm vào wishlist trong cơ sở dữ liệu
    
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
