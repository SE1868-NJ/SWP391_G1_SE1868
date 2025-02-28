package controller;

import dbcontext.CategoryDAO;
import dbcontext.ProductDAO;
import dto.ProductItemDTO;
import entity.Category;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "ProductController", urlPatterns = {"/products"})
public class ProductController extends HttpServlet {
    private ProductDAO productDAO;
    private CategoryDAO categoryDAO;

    @Override
    public void init() {
        productDAO = new ProductDAO();
        categoryDAO = new CategoryDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        int page = Integer.parseInt(request.getParameter("page") != null ? request.getParameter("page") : "1");
        int pageSize = 10; // Số sản phẩm mỗi trang
        request.setAttribute("title", "Product List");
        
        
        // Lấy danh mục sản phẩm
        List<Category> categories = categoryDAO.getAllCategories();
        request.setAttribute("categories", categories);
        
        // Thêm các tham số tìm kiếm vào request
        String searchKeyword = request.getParameter("searchKeyword");
        
        request.setAttribute("searchKeyword", searchKeyword);
        
        if (action == null || action.isEmpty()) {
            action = "list";
        }
        
        switch (action) {
            case "list":
                listProducts(request, response, page, pageSize, searchKeyword);
                break;
            case "filterByCategory":
                filterByCategory(request, response, page, pageSize);
                break;
            default:
                listProducts(request, response, page, pageSize, searchKeyword);
                break;
        }
    }

    private void listProducts(HttpServletRequest request, HttpServletResponse response, int page, int pageSize,
                              String searchKeyword) throws ServletException, IOException {
        List<ProductItemDTO> products = productDAO.getAllProducts(page, pageSize, searchKeyword);
        
        // Lấy tổng số sản phẩm
        int totalProducts = productDAO.countProducts(searchKeyword);
        int totalPages = (int) Math.ceil((double) totalProducts / pageSize);
        
        request.setAttribute("products", products);
        request.setAttribute("totalProducts", totalProducts);
        request.setAttribute("page", page);
        request.setAttribute("totalPages", totalPages);
        
        request.getRequestDispatcher("productList.jsp").forward(request, response);
    }

    private void filterByCategory(HttpServletRequest request, HttpServletResponse response , int page , int size) throws ServletException, IOException {
        int categoryId = Integer.parseInt(request.getParameter("categoryId"));
        
        List<ProductItemDTO> products = productDAO.getProductsByCategory(categoryId, page, size);
        
        // Lấy tổng số sản phẩm theo danh mục
        int totalProducts = productDAO.countProductsByCategory(categoryId);
        int totalPages = (int) Math.ceil((double) totalProducts / size);
        
        request.setAttribute("products", products);
        request.setAttribute("totalProducts", totalProducts);
        request.setAttribute("page", page);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("categoryId", categoryId);
        
        request.getRequestDispatcher("productList.jsp").forward(request, response);
    }

}
