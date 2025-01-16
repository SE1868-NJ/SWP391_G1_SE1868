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
        int pageSize = 10;
        request.setAttribute("title", "Product List");
        if(action != null && action.equals("topRated")){
            request.setAttribute("title", "Top Products");
        }
        if (action == null || action.isEmpty()) {
            action = "list";
        }
        List<Category> categories = categoryDAO.getAllCategories();
        request.setAttribute("categories", categories);

        switch (action) {
            case "list":
                listProducts(request, response, page, pageSize);
                break;
            case "filterByCategory":
                filterByCategory(request, response, page, pageSize);
                break;
            case "topRated":
                listTopRatedProducts(request, response, page, pageSize);
                break;
            default:
                listProducts(request, response, page, pageSize);
                break;
        }
    }

    private void listProducts(HttpServletRequest request, HttpServletResponse response, int page, int pageSize) throws ServletException, IOException {
        List<Product> products = productDAO.getAllProducts(page, pageSize);
        request.setAttribute("products", products);
        request.setAttribute("page", page);
        request.getRequestDispatcher("productList.jsp").forward(request, response);
    }

    private void filterByCategory(HttpServletRequest request, HttpServletResponse response, int page, int pageSize) throws ServletException, IOException {
        int categoryId = Integer.parseInt(request.getParameter("categoryId"));
        List<Product> products = productDAO.getProductsByCategory(categoryId, page, pageSize);
        request.setAttribute("products", products);
        request.setAttribute("page", page);
        request.setAttribute("categoryId", categoryId);
        request.getRequestDispatcher("productList.jsp").forward(request, response);
    }

    private void listTopRatedProducts(HttpServletRequest request, HttpServletResponse response, int page, int pageSize) throws ServletException, IOException {
        List<Product> products = productDAO.getTopRatedAndDiscountedProducts(page, pageSize);
        request.setAttribute("products", products);
        request.setAttribute("page", page);
        request.getRequestDispatcher("productList.jsp").forward(request, response);
    }
}
