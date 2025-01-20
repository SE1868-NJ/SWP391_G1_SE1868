package controller;

import dbcontext.CategoryDAO;
import dbcontext.ProductDAO;
import entity.Category;
import entity.Product;
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
        
        if(action != null && action.equals("topRated")) {
            request.setAttribute("title", "Top Products");
        }
        
        // Lấy danh mục sản phẩm
        List<Category> categories = categoryDAO.getAllCategories();
        request.setAttribute("categories", categories);
        
        // Thêm các tham số tìm kiếm vào request
        String searchKeyword = request.getParameter("searchKeyword");
        Double minPrice = request.getParameter("minPrice") != null && !request.getParameter("minPrice").isEmpty() ? Double.parseDouble(request.getParameter("minPrice")) : null;
        Double maxPrice = request.getParameter("maxPrice") != null && !request.getParameter("maxPrice").isEmpty() ? Double.parseDouble(request.getParameter("maxPrice")) : null;
        Double minRating = request.getParameter("minRating") != null && !request.getParameter("minRating").isEmpty() ? Double.parseDouble(request.getParameter("minRating")) : null;
        Double maxRating = request.getParameter("maxRating") != null && !request.getParameter("maxRating").isEmpty() ? Double.parseDouble(request.getParameter("maxRating")) : null;
        String brandName = request.getParameter("brandName");
        String supplierName = request.getParameter("supplierName");
        
        // Validate các tham số
        if(minPrice != null && minPrice < 0) minPrice = null;
        if(maxPrice != null && maxPrice < 0) maxPrice = null;
        if(minRating != null && (minRating < 0 || minRating > 5)) minRating = null;
        if(maxRating != null && (maxRating < 0 || maxRating > 5)) maxRating = null;

        request.setAttribute("searchKeyword", searchKeyword);
        request.setAttribute("minPrice", minPrice);
        request.setAttribute("maxPrice", maxPrice);
        request.setAttribute("minRating", minRating);
        request.setAttribute("maxRating", maxRating);
        request.setAttribute("brandName", brandName);
        request.setAttribute("supplierName", supplierName);
        
        if (action == null || action.isEmpty()) {
            action = "list";
        }
        
        switch (action) {
            case "list":
                listProducts(request, response, page, pageSize, searchKeyword, minPrice, maxPrice, minRating, maxRating, brandName, supplierName);
                break;
            case "filterByCategory":
                filterByCategory(request, response, page, pageSize);
                break;
            case "topRated":
                listTopRatedProducts(request, response, page, pageSize, searchKeyword, minPrice, maxPrice, minRating, maxRating, brandName, supplierName);
                break;
            default:
                listProducts(request, response, page, pageSize, searchKeyword, minPrice, maxPrice, minRating, maxRating, brandName, supplierName);
                break;
        }
    }

    private void listProducts(HttpServletRequest request, HttpServletResponse response, int page, int pageSize,
                              String searchKeyword, Double minPrice, Double maxPrice, Double minRating,
                              Double maxRating, String brandName, String supplierName) throws ServletException, IOException {
        List<Product> products = productDAO.getAllProducts(page, pageSize, searchKeyword, minPrice, maxPrice, minRating, maxRating, brandName, supplierName);
        
        // Lấy tổng số sản phẩm
        int totalProducts = productDAO.countProducts(searchKeyword, minPrice, maxPrice, minRating, maxRating, brandName, supplierName);
        int totalPages = (int) Math.ceil((double) totalProducts / pageSize);
        
        request.setAttribute("products", products);
        request.setAttribute("totalProducts", totalProducts);
        request.setAttribute("page", page);
        request.setAttribute("totalPages", totalPages);
        
        request.getRequestDispatcher("productList.jsp").forward(request, response);
    }

    private void filterByCategory(HttpServletRequest request, HttpServletResponse response , int page , int size) throws ServletException, IOException {
        int categoryId = Integer.parseInt(request.getParameter("categoryId"));
        
        List<Product> products = productDAO.getProductsByCategory(categoryId, page, size);
        
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

    private void listTopRatedProducts(HttpServletRequest request, HttpServletResponse response, int page, int pageSize,
                                      String searchKeyword, Double minPrice, Double maxPrice, Double minRating,
                                      Double maxRating, String brandName, String supplierName) throws ServletException, IOException {
        List<Product> products = productDAO.getTopRatedAndDiscountedProducts(page, pageSize, searchKeyword, minPrice, maxPrice, minRating, maxRating, brandName, supplierName);
        
        // Lấy tổng số sản phẩm top-rated
        int totalProducts = productDAO.countProducts(searchKeyword, minPrice, maxPrice, minRating, maxRating, brandName, supplierName);
        int totalPages = (int) Math.ceil((double) totalProducts / pageSize);
        
        request.setAttribute("products", products);
        request.setAttribute("totalProducts", totalProducts);
        request.setAttribute("page", page);
        request.setAttribute("totalPages", totalPages);
        
        request.getRequestDispatcher("productList.jsp").forward(request, response);
    }
}
