package controller;

import dbcontext.CartDAO;
import dbcontext.ProductDAO;
import dto.CartItemDTO;
import dto.ProductItemDTO;
import entity.Customer;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "MyCartController", urlPatterns = "/myCarts")
public class MyCartController extends HttpServlet {
    private ProductDAO productDAO;
    private CartDAO cartDAO;

    @Override
    public void init() throws ServletException {
        productDAO = new ProductDAO();
        cartDAO = new CartDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        Customer customer = (Customer) request.getSession().getAttribute("user");
        customer = new Customer();
        customer.setCustomerId(1);
        if (customer == null) {
            // Nếu không có customerId, trả về lỗi 404
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        int customerId = customer.getCustomerId();

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
            handleAddToCart(request, response , customerId);
        } else {
            response.sendRedirect("/myCarts?action=viewCart");
        }
    }

    private void handleViewCart(HttpServletRequest request, HttpServletResponse response , Integer customerId) throws ServletException, IOException {

        try {
            List<CartItemDTO> cartList = cartDAO.getCartByCustomerId(customerId);

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
            cartDAO.removeFromCart(customerId , productId);
            response.sendRedirect("/myCarts?action=viewCart");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    private void handleClearCart(HttpServletRequest request, HttpServletResponse response , Integer customerId) throws IOException {
        try {

            cartDAO.clearCart(customerId);
            response.sendRedirect("/myCarts?action=viewCart");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    private void handleUpdateCartItemQuantity(HttpServletRequest request, HttpServletResponse response , Integer customerId) throws IOException {
        try {
            int productId = Integer.parseInt(request.getParameter("productId"));
            int updatedQuantity = Integer.parseInt(request.getParameter("quantity"));
            ProductItemDTO product = productDAO.getProductById(productId);
            CartItemDTO cart = cartDAO.getCartByCustomerIdAndProductId(customerId, productId);
            if(product == null || cart == null){
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
                return;
            }
            int quantityDiff = updatedQuantity - cart.getQuantity();
            if(quantityDiff > product.getStockQuantity()){
                response.getWriter().println("<p> Out of stock </p>");
                response.setStatus(404);
                return;
            }
            cartDAO.updateCartItemQuantity(customerId, productId, updatedQuantity);
            response.sendRedirect("/myCarts?action=viewCart");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    private void handleAddToCart(HttpServletRequest request, HttpServletResponse response , Integer customerId) throws IOException {
        try {
            int productId = Integer.parseInt(request.getParameter("productId"));
            ProductItemDTO product = productDAO.getProductById(productId);
            if(product == null || product.getStockQuantity() < 1){
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
                return;
            }
            cartDAO.addToCart(customerId, productId);
            response.sendRedirect("/myCarts?action=viewCart");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}
