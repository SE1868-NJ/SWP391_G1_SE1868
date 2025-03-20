/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import com.google.gson.Gson;
import entity.Customer;
import entity.Favorite;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.FavoritesDAO;
import entity.SearchFavorite;

/**
 *
 * @author Giang123
 */
public class SearchFavoriteController extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(SearchFavoriteController.class.getName());
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
      response.setContentType("application/json;charset=UTF-8");

        try {
            // Verify user is logged in
            HttpSession session = request.getSession(false);
            if (session == null || session.getAttribute("customer") == null) {
                response.setStatus(401);
                response.getWriter().write("{\"error\": \"Unauthorized\"}");
                return;
            }

            Customer customer = (Customer) session.getAttribute("customer");
            String searchQuery = request.getParameter("query");

            if (searchQuery == null) {
                searchQuery = "";  // Empty search returns all favorites
            }

            int page = 1;
            try {
                page = Integer.parseInt(request.getParameter("page"));
                if (page < 1) page = 1;
            } catch (NumberFormatException e) {
                LOGGER.log(Level.WARNING, "Invalid page number, defaulting to 1", e);
            }

            int pageSize = 5;

            FavoritesDAO favoritesDAO = new FavoritesDAO();
            List<Favorite> searchResults = favoritesDAO.searchFavorites(
                customer.getCustomerId(), 
                searchQuery,
                page,
                pageSize
            );

            // Format prices before sending response
            for (Favorite favorite : searchResults) {
                favorite.setFormattedPrice(FormatUtils.formatCurrency(favorite.getPrice()));
            }

            int totalResults = favoritesDAO.getTotalSearchResults(customer.getCustomerId(), searchQuery);
            int totalPages = (int) Math.ceil((double) totalResults / pageSize);

            // Create response object
            SearchResponse searchResponse = new SearchResponse();
            searchResponse.setProducts(searchResults);
            searchResponse.setTotalPages(totalPages);
            searchResponse.setCurrentPage(page);

            // Convert to JSON and send response
            Gson gson = new Gson();
            String jsonResponse = gson.toJson(searchResponse);
            response.getWriter().write(jsonResponse);

            LOGGER.log(Level.INFO, "Search completed successfully. Query: {0}, Results: {1}", 
                    new Object[]{searchQuery, searchResults.size()});

        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Error during search operation", e);
            response.setStatus(500);
            response.getWriter().write("{\"error\": \"Internal server error\", \"message\": \"" + e.getMessage() + "\"}");
        }
    }

    private static class SearchResponse {
        private List<Favorite> products;
        private int totalPages;
        private int currentPage;

        public void setProducts(List<Favorite> products) {
            this.products = products;
        }

        public void setTotalPages(int totalPages) {
            this.totalPages = totalPages;
        }

        public void setCurrentPage(int currentPage) {
            this.currentPage = currentPage;
        }
    
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
