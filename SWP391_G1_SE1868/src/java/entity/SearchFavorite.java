/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.util.List;

/**
 *
 * @author Giang123
 */
 public class SearchFavorite {
        private List<Favorite> products;
        private int totalPages;
        private int currentPage;

    public SearchFavorite(List<Favorite> products, int totalPages, int currentPage) {
        this.products = products;
        this.totalPages = totalPages;
        this.currentPage = currentPage;
    }

    public SearchFavorite() {
    }

        
        
        
    public List<Favorite> getProducts() {
        return products;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public int getCurrentPage() {
        return currentPage;
    }

        
        
        
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