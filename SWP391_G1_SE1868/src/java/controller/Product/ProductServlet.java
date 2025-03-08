/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.Product;

import entity.Category;
import entity.Product;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import models.CategoryDAO;
import models.ProductDAO;

/**
 *
 * @author Đạt
 */
@WebServlet(name = "ProductServlet", urlPatterns = {"/products"})
public class ProductServlet extends HttpServlet {

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
            out.println("<title>Servlet ProductServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ProductServlet at " + request.getContextPath() + "</h1>");
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

        // khái báo dao product
        ProductDAO productDAO = new ProductDAO();
        CategoryDAO categoryDAO = new CategoryDAO();
        

        // Lấy tham số từ request
        String keyword = request.getParameter("keyword");
        String minPriceStr = request.getParameter("minPrice");
        String maxPriceStr = request.getParameter("maxPrice");
        String categoryIdStr = request.getParameter("categoryId");
        String sortBy = request.getParameter("sortBy");
        String order = request.getParameter("order");
        String pageStr = request.getParameter("page");
        String pageSizeStr = request.getParameter("pageSize");

        // Chuyển đổi dữ liệu
        Double minPrice = (minPriceStr != null && !minPriceStr.isEmpty()) ? Double.parseDouble(minPriceStr) : null;
        Double maxPrice = (maxPriceStr != null && !maxPriceStr.isEmpty()) ? Double.parseDouble(maxPriceStr) : null;
        Integer categoryId = (categoryIdStr != null && !categoryIdStr.isEmpty()) ? Integer.parseInt(categoryIdStr) : null;
        int page = (pageStr != null && !pageStr.isEmpty()) ? Integer.parseInt(pageStr) : 1;
        int pageSize = (pageSizeStr != null && !pageSizeStr.isEmpty()) ? Integer.parseInt(pageSizeStr) : 6;

        // Lấy danh sách sản phẩm
        List<Product> products = productDAO.listProducts(keyword, minPrice, maxPrice, categoryId, sortBy, order, page, pageSize);
        
        // lấy danh sách thể loại
        
        List<Category> categorys = categoryDAO.getAllCategories();

        // Tính tổng số trang
        int totalPages = productDAO.getTotalPages(keyword, minPrice, maxPrice, categoryId, pageSize);

        // Gửi dữ liệu đến JSP
        request.setAttribute("products", products);
         request.setAttribute("categorys", categorys);
        request.setAttribute("currentPage", page);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("pageSize", pageSize);
        request.setAttribute("keyword", keyword);
        request.setAttribute("minPrice", minPrice);
        request.setAttribute("maxPrice", maxPrice);
        request.setAttribute("categoryId", categoryId);
        request.setAttribute("sortBy", sortBy);
        request.setAttribute("order", order);

        // Chuyển hướng đến trang JSP để hiển thị dữ liệu
        request.getRequestDispatcher("view-product.jsp").forward(request, response);
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
        processRequest(request, response);
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
