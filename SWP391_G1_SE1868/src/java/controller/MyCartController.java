package controller;

import dbcontext.ProductDAO;
import entity.Cart;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "MyCartController", urlPatterns = "/myCarts")
public class MyCartController extends HttpServlet {
    private ProductDAO productDAO;

    @Override
    public void init() throws ServletException {
        productDAO = new ProductDAO(); // Tạo đối tượng ProductDAO
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        Integer customerId = (Integer) request.getSession().getAttribute("customerId");

        if (customerId == null) {
            // Nếu không có customerId, trả về lỗi 404
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        // Kiểm tra nếu action là "viewCart"
        if ("viewCart".equals(action)) {
            handleViewCart(request, response , customerId);
        }
        // Các action khác như remove, clear, update sẽ được xử lý dưới đây.
        else if ("removeFromCart".equals(action)) {
            handleRemoveFromCart(request, response , customerId);
        } else if ("clearCart".equals(action)) {
            handleClearCart(request, response , customerId);
        } else if ("updateCartItemQuantity".equals(action)) {
            handleUpdateCartItemQuantity(request, response , customerId);
        } else if ("addToCart".equals(action)) {
            handleAddToCart(request, response);
        } else {
            response.sendRedirect("/myCarts?action=viewCart");
        }
    }

    private void handleViewCart(HttpServletRequest request, HttpServletResponse response , Integer customerId) throws ServletException, IOException {

        try {
            List<Cart> cartList = productDAO.getCartByCustomerId(customerId);

            // Set danh sách cart vào attribute của request
            request.setAttribute("cartList", cartList);
            // Forward đến trang giỏ hàng (cart.jsp)
            request.getRequestDispatcher("/cart.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    private void handleRemoveFromCart(HttpServletRequest request, HttpServletResponse response , Integer customerId) throws IOException {
        try {
            int productId = Integer.parseInt(request.getParameter("productId"));
            productDAO.removeFromCart(customerId , productId);
            response.sendRedirect("/myCarts?action=viewCart");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    private void handleClearCart(HttpServletRequest request, HttpServletResponse response , Integer customerId) throws IOException {
        try {

            productDAO.clearCart(customerId);
            response.sendRedirect("/myCarts?action=viewCart");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    private void handleUpdateCartItemQuantity(HttpServletRequest request, HttpServletResponse response , Integer customerId) throws IOException {
        try {
            int productId = Integer.parseInt(request.getParameter("productId"));
            int quantity = Integer.parseInt(request.getParameter("quantity"));
            productDAO.updateCartItemQuantity(customerId, productId, quantity);
            response.sendRedirect("/myCarts?action=viewCart");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    private void handleAddToCart(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            Integer customerId = (Integer) request.getSession().getAttribute("customerId");

            if (customerId == null) {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                return;
            }

            int productId = Integer.parseInt(request.getParameter("productId"));
            productDAO.addToCart(customerId, productId);
            response.sendRedirect("/myCarts?action=viewCart");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}
