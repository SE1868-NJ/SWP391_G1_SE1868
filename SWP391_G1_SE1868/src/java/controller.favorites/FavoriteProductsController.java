package controller;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
import dbcontext.ProductDAO;
import entity.Customer;
import entity.Product;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import Utils.FormatUtils;
import dbcontext.FavoriteDAO;
import entity.Favorite;


/**
 *
 * @author Giang123
 */
public class FavoriteProductsController extends HttpServlet {

    private static final int PAGE_SIZE = 5; //Số sản phảm mỗi trang

    
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
        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("customer") == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        Customer customer = (Customer) session.getAttribute("customer");
        int customerId = customer.getCustomerId();

        int page = 1;
        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }

        FavoriteDAO favoriteDAO = new FavoriteDAO();
        List<Favorite> favoriteProducts = favoriteDAO.getFavoriteProducts(customerId, page, PAGE_SIZE);
        int totalFavorite = favoriteDAO.getTotalFavoriteProducts(customerId);
        int totalPages = (int) Math.ceil((double) totalFavorite / PAGE_SIZE);

        // 👉 Định dạng giá tiền trước khi gửi sang JSP
        for (Favorite favorite : favoriteProducts) {
            favorite.setFormattedPrice(FormatUtils.formatCurrency(favorite.getPrice()));
        }

        request.setAttribute("favoriteProducts", favoriteProducts);
        request.setAttribute("currentPage", page);
        request.setAttribute("totalPages", totalPages);

        RequestDispatcher dispatcher = request.getRequestDispatcher("favorites.jsp");
        dispatcher.forward(request, response);
    
    }
//        HttpSession session = request.getSession(false);
//
//        if (session == null || session.getAttribute("customer") == null) {
//            response.sendRedirect("login.jsp");
//            return;
//        }
//
//        Customer customer = (Customer) session.getAttribute("customer");
//        int customerId = customer.getCustomerId();
//
//        ProductDAO productDAO = new ProductDAO();
//        List<Product> favoriteProducts = productDAO.getFavoriteProducts(customerId);
//
//        request.setAttribute("favoriteProducts", favoriteProducts);
//        RequestDispatcher dispatcher = request.getRequestDispatcher("/favorites/favorites.jsp");
//        dispatcher.forward(request, response);
//         // Lấy HttpSession từ request
//        HttpSession session = request.getSession();
//        
//        // Lấy đối tượng Customer từ session
//        Customer customer = (Customer) session.getAttribute("customer");
//        
//        // Kiểm tra xem đối tượng Customer có tồn tại trong session không
//        if (customer == null) {
//            // Nếu không có customer, có thể chuyển hướng về trang đăng nhập
//            response.sendRedirect("login.jsp");
//            return;
//        }
//        
//        // Lấy customerID từ đối tượng Customer
//        int customerId = customer.getCustomerId(); // Giả sử Customer có phương thức getCustomerID()
//        
//        // Tạo đối tượng DAO để truy xuất dữ liệu
//        ProductDAO favoriteProductDAO = new ProductDAO();
//        
//        // Lấy danh sách sản phẩm yêu thích từ DAO
//        List<Product> favoriteProducts = favoriteProductDAO.getFavoriteProducts(customerId);
//        
//        // Đưa danh sách sản phẩm yêu thích vào request attribute để JSP sử dụng
//        request.setAttribute("favoriteProducts", favoriteProducts);
//        
//        // Chuyển tiếp (forward) đến trang JSP
//        RequestDispatcher dispatcher = request.getRequestDispatcher("/favorites/favorites.jsp");
//        dispatcher.forward(request, response);
        /**
         * Handles the HTTP <code>POST</code> method.
         *
         * @param request servlet request
         * @param response servlet response
         * @throws ServletException if a servlet-specific error occurs
         * @throws IOException if an I/O error occurs
         */
        @Override
        protected void doPost
        (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        // Lấy HttpSession từ request
//        HttpSession session = request.getSession();
//        
//        // Lấy đối tượng Customer từ session
//        Customer customer = (Customer) session.getAttribute("customer");
//        
//        if (customer == null) {
//            // Nếu không có customer, chuyển hướng về trang đăng nhập
//            response.sendRedirect("login.jsp");
//            return;
//        }
//        
//        // Lấy customerID từ đối tượng Customer
//        int customerID = customer.getCustomerId();
//        
//        // Lấy thông tin sản phẩm từ request (có thể là ID sản phẩm và số lượng)
//        int productID = Integer.parseInt(request.getParameter("productID"));
//        int quantity = Integer.parseInt(request.getParameter("quantity"));
//
//        // Tạo đối tượng DAO để cập nhật thông tin giỏ hàng hoặc sản phẩm yêu thích
//        ProductDAO productDAO = new ProductDAO();
//
//        try {
//            // Cập nhật số lượng sản phẩm yêu thích trong cơ sở dữ liệu
//            boolean success = productDAO.updateFavoriteQuantity(customerID, productID, quantity);
//
//            if (success) {
//                // Lấy lại danh sách sản phẩm yêu thích sau khi cập nhật
//                List<Product> favoriteProducts = productDAO.getFavoriteProducts(customerID);
//
//                // Đưa danh sách sản phẩm yêu thích vào request attribute để JSP sử dụng
//                request.setAttribute("favoriteProducts", favoriteProducts);
//
//                // Chuyển tiếp (forward) đến trang JSP hiển thị giỏ hàng
//                RequestDispatcher dispatcher = request.getRequestDispatcher("/favorites/favorites.jsp");
//                dispatcher.forward(request, response);
//            } else {
//                // Nếu có lỗi khi cập nhật, chuyển hướng tới trang lỗi
//                request.setAttribute("errorMessage", "Cập nhật giỏ hàng thất bại.");
//                RequestDispatcher dispatcher = request.getRequestDispatcher("/error.jsp");
//                dispatcher.forward(request, response);
//            }
//
//        } catch (IOException e) {
//            // Xử lý lỗi và ghi log
//            log("Lỗi khi cập nhật giỏ hàng: " + e.getMessage(), e);
//            request.setAttribute("errorMessage", "Có lỗi khi xử lý yêu cầu. Vui lòng thử lại.");
//            RequestDispatcher dispatcher = request.getRequestDispatcher("/error.jsp");
//            dispatcher.forward(request, response);
//        } catch (SQLException ex) {
//            Logger.getLogger(FavoriteProductsController.class.getName()).log(Level.SEVERE, null, ex);
//        }

//        // Lấy session từ request
//        HttpSession session = request.getSession();
//
//        // Lấy đối tượng Customer từ session
//        Customer customer = (Customer) session.getAttribute("customer");
//
//        // Kiểm tra nếu khách hàng không đăng nhập hoặc session hết hạn
//        if (customer == null) {
//            response.sendRedirect("login.jsp");
//            return;
//        }
//
//        // Lấy thông tin chi tiết của khách hàng
//        String fullName = customer.getFullName();
//        String email = customer.getEmail();
//        String phoneNumber = customer.getPhoneNumber();
//        String address = customer.getAddress();
//        Date birthDate = customer.getBirthDate();
//        String gender = customer.getGender();
//
//        // Đưa thông tin khách hàng vào request
//        request.setAttribute("fullName", fullName);
//        request.setAttribute("email", email);
//        request.setAttribute("phoneNumber", phoneNumber);
//        request.setAttribute("address", address);
//        request.setAttribute("birthDate", birthDate);
//        request.setAttribute("gender", gender);
//
//        // Lấy danh sách sản phẩm yêu thích từ DAO
//        ProductDAO productDAO = new ProductDAO();
//        List<Product> favoriteProducts = productDAO.getFavoriteProducts(customer.getCustomerId());
//
//        // Đưa danh sách sản phẩm vào request
//        request.setAttribute("favoriteProducts", favoriteProducts);
//
//        // Chuyển hướng tới JSP để hiển thị thông tin
//        RequestDispatcher dispatcher = request.getRequestDispatcher("favoriteProducts.jsp");
//        dispatcher.forward(request, response);
        }

        @Override
        public String getServletInfo
        
            () {
        return "Short description";
        }// </editor-fold>

    }
